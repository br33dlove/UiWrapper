# UiWrapper

Library to provide wrapping of Android Fragments to smooth out lifecycles and provide view framework for MVP architectures on Android platform

UiWrapper provides a framework with which to structure the UI your application, aiding a more modular approach to building the UI and hence one that is more easily testable.

# Implementing UiWrapper

The UiWrapper library provides the Ui interface in which to draw up contracts for each screen. By extending the Ui interface, the UI can be built as collection of states and events.

```java
public interface DataListUi extends Ui {
    //States
    void animateLoadingFromFailureToGetData();
    void showLoading();
    
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


Fragments then implement the Ui derivatives and implement the UI around those states and events.

```java
public class DataListFragment extends UiFragment<UiWrapperRepository, DataListUi.Listener> implements DataListUi {
    ...
    //Fragment setup
    ...
    
    @Override
    public void animateLoadingFromFailureToGetData() {
      progressBar.animateIn();
      retryButton.animateOut();
    }

    @Override
    public void showLoading() {
        progressBar.show();
        retryButton.hide();
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

The events shown here are click events, but are by no means limited to this. Any event that comes from the Android API via activities, fragments, views, etc, should be put in the Listener interface to be dealt with on a higher abstraction level, and methods then called on the fragment (via the Ui derivative interface) to update the UI or interact with the Android API further as determined by the UI and application logic.

In the fragment example above, the Retry event is called like so:

```java
if (hasListener()) {
    listener().onClickRetry(DataListFragment.this);
}
```

The listener is a DataListUi.Listener object reference. The type has been specified in the class declaration when extending UiFragment:

```java
public class DataListFragment extends UiFragment<UiWrapperRepository, DataListUi.Listener> implements DataListUi {
```

It can be seen that UiFragment takes two types. The first is an implementation of BaseUiWrapperRepository, which will be discussed later, and the second is the DataListUi.Listener interface. Both UiFragment and BaseUiWrapperRepository belong to the UiWrapper library.

The listener reference should always be checked first by hasListener(). The listener reference is obtained during binding with the UiWrapper in onViewCreated(View) or onStart() and removed during unbinding in onSaveInstanceState(Bundle) or onStop().

In this example, DataListUi is bound to the DataListUiWrapper:

```java
public class DataListUiWrapper extends UiWrapper<ExampleUi, ExampleUi.Listener, ExampleUiModel> {
    private final Service service;

    private DataListUiWrapper(ExampleUiModel uiModel, Service service) {
        super(uiModel);
        this.service = service;
    }

    public static DataListUiWrapper newInstance(final DataListUiModelFactory uiModelFactory, final Service service) {
        return new ExampleUiWrapper(resource, uiModelFactory.create());
    }

    public static DataListUiWrapper savedElseNewInstance(
            final DataListUiModelFactory uiModelFactory,
            final Service service,
            final Bundle savedInstanceState
    ) {
        final ExampleUiModel uiModel = UiWrapper.savedUiModel(savedInstanceState);
        return uiModel == null ? newInstance(uiModelFactory, service) : new ExampleUiWrapper(uiModel, service);
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
                ui.animateToLoadingFromFailureToGetData();
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

Ideally, the UiWrapper derivatives are used to implement the View interface in an MVP architecture, where the Presenter is created in the UiWrapper's constructor and handles the communication with back-end services/the Model component and controlling the high-level UI logic, in order to complete the separation of the Android API from the UI and business logic. An example can be found here: https://github.com/davidcryer/TrumpOrGump.

UiWrapper provides a layer of abstraction above UI-implementations (fragments, activities) and also acts to smooth out fragment and activity lifecycles by persisting across configuration changes. As such, Ui objects are bound-to and unbound-from the UiWrapper and the UiWrapper holds the Ui state in UiModel derivatives which configures the Ui object to the current state in every bind event. Also, as they UiWrapper derivatives are created by the developer, dependencies can be injected into the UiWrappers via the constructor. This means that your service objects can be injected where necessary and need not exist as globally-accessible singletons. 

Injected service objects can then start requests in the registerResources() method, and stop any ongoing requests in the unregisterResources() method, which are both called by the UiWrapper superclass when binding and unbinding Ui objects respectively. 

In the case of MVP, these service-objects would instead be injected into the presenter, and the register and unregister methods would simply be passed on.


<b>Samples</b>

A sample application implementing UiWrapper, but not MVP, can be found in the <b>sample</b> module of this project.

Other examples can be found throughout my repositories, including the following:

https://github.com/davidcryer/TrumpOrGump
https://github.com/davidcryer/BenchmarkTemplate
https://github.com/davidcryer/NightFilter
