# UiWrapper

Library to provide wrapping of Android Fragments to smooth out lifecycles and provide framework for MVP architectures on the Android platform.

# Implementing UiWrapper

The UiWrapper library provides the means to structure an application's UI in terms of a UI abstraction (the Ui interface), a UI implementation (an Android fragment), a UiModel holding the UI's state, and a Presenter handling the UI logic in response to events from the UI implementation and interacting with back-end services (UiWrapper).

Here I am going to take you through an example of a screen where a list of data is downloaded from a server and shown if the download is successful, or have an error state shown if unsuccessful. While downloading, a loading state is shown, and when downloaded a user can tap on a list item to be navigated to a screen displaying the details of that item.

<b>Ui</b>

The UiWrapper library provides the Ui interface in which to draw up contracts for each screen. By extending the Ui interface, the UI can be built as collection of states and events.

```java
public interface DataListUi extends Ui {
    //States
    void showLoading();
    void animateToLoadingFromFailureToGetData();
    
    void showData(List<Data> data);
    void animateToDataFromLoading(List<Data> data);
    
    void showFailureToGetData();
    void animateToFailureToGetDataFromLoading();
    
    void navigateToDataDetailsScreen(Data data);
    
    public interface Listener extends Ui.Listener {
        //Events
        void onClickData(DataListUi ui, Data data);
        void onClickRetry(DataListUi ui);
    }
}
```

This interface should be able to describe exactly what the screen does, whilst not being bogged down in implementation detail.

<b>UiFragment</b>

Fragments extending the UiFragment class then implement the Ui derivatives and implement the UI around those states and events. UiFragment takes two type paramters - a Ui.Listener derivative type, as well as a UiWrapperRepository type which will be discussed later. The UiFragment handles initialising the binding and unbinding of the fragment to the UiWrapper and provides a Ui.Listener reference (here DataListUi.Listener) to the subclass via the listener() method.

```java
public class DataListFragment extends UiFragment<UiWrapperRepository, DataListUi.Listener> implements DataListUi {
    ...
    //Fragment setup
    ...

    @Override
    public void showLoading() {
        progressBar.show();
        retryButton.hide();
    }
    
    @Override
    public void animateToLoadingFromFailureToGetData() {
      progressBar.animateIn();
      retryButton.animateOut();
    }
    
    @Override
    public void showData(final List<Data> data) {
        recyclerView.showData(data, dataListListener)
        progressBar.hide();
        retryButton.hide();
    }
    
    @Override
    public void animateToDataFromLoading(final List<Data> data) {
        recyclerView.animateInData(data, dataListListener);
        progressBar.animateOut();
    }
    
    private final DataList.Listener dataListListener = new DataList.OnClickListener() {
        @Override
        public void onClick(Data data) {
            if (hasListener()) {
                listener.onClickData(DataListFragment.this, data);
            }
        }
    };
    
    @Override 
    public void showFailureToGetData() {
        progressBar.hide();
        retryButton.show();
    }
    
    @Override 
    public void animateToFailureToGetDataFromLoading() {
        progressBar.animateOut();
        retryButton.animateIn();
    }
    
    private final OnClickListener retryClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (hasListener()) {
                listener().onClickRetry(DataListFragment.this);
            }
        }
    }
    
    @Override
    public void navigateToDataDetailsScreen(final Data data) {
        navigator.toDataDetails(data);
    }
    
    ...
}
```

The events shown here are click events, but are by no means limited to this. Any event that comes from the Android API via activities, fragments, views, services, broadcast receivers, etc, should be put in the Listener interface to be dealt with on a higher abstraction level, and methods then called on the fragment (via the Ui derivative interface) to update the UI or interact with the Android API further as determined by the UI and application logic.

In the fragment example above, the Retry event is called like so:

```java
if (hasListener()) {
    listener().onClickRetry(DataListFragment.this);
}
```

The listener reference should always be checked first by hasListener(). The listener reference is obtained during binding with the UiWrapper in onViewCreated(View) or onStart() and removed during unbinding in onSaveInstanceState(Bundle) or onStop().

The binding and unbinding of the Ui objects occurs in the library's UiWrapper class. It holds a final reference to the screen's UiModel that can be obtained in the UiWrapper subclasses through uiModel(), and a nullable reference to the Ui object that is similarly obtained through ui(). During binding, the UiWrapper class calls the method onto(Ui) on the UiModel in order to set the Ui object up for the current state.

<b>UiModel</b>

```java
public class DataListUiModel implements UiModel<DataListUi> {
    enum State {LOADING, DATA, FAILURE_TO_LOAD_DATA}
    private State state;
    private List<Data> data;

    DataListUiModel(final State state, final List<Data> data) {
        this.state = state;
        this.data = data;
    }
    
    private DataListUiModel(final Parcel parcel) {
        state = parcel.readSerializable();
    }
    
    @Override
    public void onto(final DataListUi ui) {
        switch (state) {
            case LOADING: {
                ui.showLoading();
                break;
            }
            case DATA: {
                ui.showData(data);
                break;
            }
            case FAILURE_TO_LOAD_DATA: {
                ui.showFailureToLoadData();
                break;
            }
        }
    }
    
    @Override
    public void writeToParcel(final Parcel parcel) {
        parcel.writeSerializable(state == State.DATA ? State.LOADING : state);
    }
    ...
}
```

The UiModel interface extends Parcelable, and the UiModel subclass has it's state saved by the UiWrapper when unbinding and restored when binding if it was previously stored. In this example List<Data> is non-parcelable and so if the state variable is State.DATA, then we store State.LOADING instead an let the list get garbage collected. 

Note that we stored LOADING, but did not change the State variable in case the UiModel persists and therefore the data list also persists. An example of the UiModel instance persisting after being parceled is if the user leaves the screen/app, but the activity does not get killed, or if the activity undergoes a configuration change (where the activity actually is killed!). Therefore, this trick in writeToParcel() is to handle the Android system requiring extra memory and allowing us to come back from that, we go and re-fetch the data, but otherwise ignoring it.

As the UiModel holds the state of the UI, we need methods to update the state. These will roughly match some of the methods in the DataListUi interface and will be called by the DataListUiWrapper class:

```java
public class DataListUiModel implements UiModel<DataListUi> {
    ...
    
    public void showLoading(final DataListUi ui) {
        if (ui != null) {
            ui.animateToLoadingFromFailureToGetData();
        }
        state = State.LOADING;
    }
    
    public void showData(final DataListUi ui, final List<Data> data) {
        if (ui != null) {
            ui.animateToDataFromLoading(data);
        }
        ui = State.DATA;
        this.data = data;
    }
    
    public void showFailureToGetData(final DataListUi ui) {
        if (ui != null) {
            ui.animateToFailureToGetDataFromLoading();
        }
        ui = State.FAILURE_TO_GET_DATA;
    }
    
    public boolean isLoading() {
        return state == State.LOADING;
    }
    ...
}
```

Note that I haven't added a method for navigating to the data details screen, as that doesn't impact the state of the UI and I can call the method on the DataListUi object directly in the onClickData(DataListUi, Data) event method.

If the DataListUi object is null, then when the new object is bound and onto(DataListUi) is called the new state is set on it. If it is not null, then the changes in UI states can be animated on screen. In the example I have assumed that the animations will go from a specific state to the next, e.g. to 'loading' from 'failure to get new data', as there is a specific flow that the UI follows. However, if a UI state can be reached from multiple previous states, then one can simply check for each and animate to the new state accordingly.

```java
public class ExampleUiModel implements UiModel<ExampleUi> {
    ...
    
    public void showC(final ExampleUi ui) {
        if (ui != null) {
            switch (state): {
                case A: {
                    ui.animateToCFromA();
                    break;
                }
                case B: {
                    ui.animateToCFromB();
                    break;
                }
            }
        }
        state = State.C;
    }
}
```

<b>UiWrapper</b>

The UiWrapper superclass and its derivatives are the only objects to interact with the UiModels. The superclass sets the UiModel onto the Ui object with onto(Ui) as we have seen, and the subclasses call the other methods to update the UI state. The superclass handles the saving of the UiModel and opens the static method savedUiModel(Bundle) to the subclasses to get the stored UiModel out.

The DataListUiWrapper class must implement uiListener() and return an instance of the DataListUi.Listener interface for the DataListFragment to use.

Two methods can also be overriden by DataListUiWrapper - registerResources() and unregisterResources() - which signal when to register to or request data from back-end services, and when to unregister or cancel those requests. In unregisterResources() it is vital that all references to the UiWrapper are removed from objects held at the Application level to ensure that memory is not leaked. These 

```java
public class DataListUiWrapper extends UiWrapper<DataListUi, DataListUi.Listener, DataListUiModel> {
    private final Service service;

    private DataListUiWrapper(DataListUiModel uiModel, Service service) {
        super(uiModel);
        this.service = service;
    }

    public static DataListUiWrapper newInstance(final DataListUiModelFactory uiModelFactory, final Service service) {
        return new DataListUiWrapper(uiModelFactory.create(), service);
    }

    public static DataListUiWrapper savedElseNewInstance(
            final DataListUiModelFactory uiModelFactory,
            final Service service,
            final Bundle savedInstanceState
    ) {
        final DataListUiModel uiModel = UiWrapper.savedUiModel(savedInstanceState);
        return uiModel == null ? newInstance(uiModelFactory, service) : new DataListUiWrapper(uiModel, service);
    }

    @Override
    protected DataListUi.Listener uiListener() {
        return new DataListUi.Listener() {
            @Override
            public void onClickData(DataListUi ui, Data data) {
                ui.navigateToDataDetailsScreen(data);
            }
            
            @Override
            public void onClickRetry(DataListUi ui) {
                uiModel().showLoading(ui);
                service.getData(serviceCallback);
            }
        };
    }
    
    @Override
    protected void registerResources() {
        if (uiModel().isLoading()) {
            service.getData(serviceCallback);
        }
    }
    
    @Override
    protected void unregisterResources() {
        service.cancelAnyRequests()
    }
    
    private final Service.Callback serviceCallback = new Service.Callback() {
        @Override
        public void onSuccess(final Data data) {
            uiModel().showData(ui(), data);
        }
        
        @Override void onFailure() {
            uiModel().showFailureToGetData(ui());
        }
    };
}
```

# More on the UiWrapper library

<b>Aim</b>

The aim of this library is to provide a framework which supports a more modular design of Android applications. It lends a particular structure to an application, where interactions with back-end services are kept away from UI implementations by using UiWrapper derivatives, and defines set responsibilities to fragments and activities. 


<b>Fragments and Activities responsibilities</b>

Fragments must extend the UiFragment abstract class and are responsible for the UI implementation. Activities must extend UiWrapperRepositoryActivity (or it's derivative SingleContentContainerWithAppBarActivity) and are responsible for providing the app bar and view containers in which to attach the fragments, as well as implementing app-wide navigation (adding/removing fragments and starting new activities). Consequently, the UI and business logic is moved away from the Android API.


<b>The UiWrapper class</b>

The UiWrapper derivatives are the Presenters in the architecture and handle the communication with back-end services/the Model component and controlling the high-level UI logic.

UiWrapper provides a layer of abstraction above UI-implementations (fragments, activities) and also acts to smooth out fragment and activity lifecycles by persisting across configuration changes. As such, Ui objects are bound-to and unbound-from the UiWrapper and the UiWrapper holds the Ui state in UiModel derivatives which configures the Ui object to the current state in every bind event. Also, as they UiWrapper derivatives are created by the developer, dependencies can be injected into the UiWrappers via the constructor. This means that your service objects can be injected where necessary and need not exist as globally-accessible singletons. 

Injected service objects can then start requests in the registerResources() method, and stop any ongoing requests in the unregisterResources() method, which are both called by the UiWrapper superclass when binding and unbinding Ui objects respectively. 

In the case of MVP, these service-objects would instead be injected into the presenter, and the register and unregister methods would simply be passed on.


<b>Samples</b>

A sample application implementing UiWrapper can be found in the <b>sample</b> module of this project.

Other examples can be found throughout my repositories, including the following:

https://github.com/davidcryer/TrumpOrGump
https://github.com/davidcryer/BenchmarkTemplate
https://github.com/davidcryer/NightFilter
