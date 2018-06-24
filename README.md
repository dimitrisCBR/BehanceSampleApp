
# Android Behance Sample Browser
`Powered By Behance`

![alt text](/files/capture2.gif "Gif")

Example Android project, using Behance's API. I try to maintain this repo as a project of best practices in app architectures and UI.

### Running the project

To run the project locally, you need an API Key from https://www.behance.net/dev/apps. When you have that, replace the placeholder with your key in the `app/build.gradle` file.

```
buildTypes {
   debug {
      buildConfigField("String", "API_KEY", <YOUR_KEY_GOES_HERE>)
   }
   ...
}
```

### Android Practices

The code is WIP, so you might find unfinished stuff.
 
##### Architecture

MVP clean, minimal architecture with:
- Kotlin
- Dagger 2
- Retrofit
- Rx

##### UI
- Infinite Loading RecyclerView
- Dynamic Grid RecyclerView
- Collapsing Toolbar
- Bottom Navigation
- Image Blur
- App Themes & Styles