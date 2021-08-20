# Hacking News

# Video Demo

![GIF](./gif/demo.gif)

# Configuration

- Android Studio Arctic Fox | 2020.3.1 Patch 1
- Compile SDK 30
- Build Tools Version 30.0.3
- Minimum SDK 23 (Android 6.0 M)
- Java 8

# Installation
- Clone this repository and import into Android Studio
    git clone https://github.com/seedunk6/HackerNews.git
- Select a device o virtual device (Android 6.0 or higher)
- Run 'App'

# Used dependencies

* [AndroidX Libraries](https://developer.android.com/jetpack/androidx/explorer?case=all): 
   - CardView - Material design for the recyclerView
   - ViewModel and LiveData - Store and manage user interface related data
   - ConstraintLayout - Simplify view design
   - SwipeRefreshLayout - Pull to refresh
   - Navigation - Transactions between fragments
   - Room - Local database
* [Dagger Hilt](https://github.com/google/dagger): Dependency injector
* [Retrofit 2](https://square.github.io/retrofit/): HTTP client
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Simplifies code that runs asynchronously
* [Swipe Decorator](https://github.com/xabaras/RecyclerViewSwipeDecorator) - Swipe to delete
