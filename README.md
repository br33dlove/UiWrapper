# UiWrapper

Library to provide wrapping of Android Fragments to smooth out lifecycles and provide view framework for MVP architectures on Android platform

# Implementing UiWrapper
//Work through example

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
