<p> <img src=".github/assets/komoju_logo.svg" width="350" title="hover text" alt="Komoju Logo"></p>

[![License](https://img.shields.io/github/license/stripe/stripe-android)](https://github.com/stripe/stripe-android/blob/master/LICENSE)
![Android Version](https://img.shields.io/maven-central/v/com.komoju.mobile.sdk/android?style=flat&logo=android&label=Android&color=3CC239)
![Shared Version](https://img.shields.io/maven-central/v/com.komoju.mobile.sdk/shared?style=flat&logo=kotlin&label=Shared&color=3CC239)
![iOS Version](https://img.shields.io/maven-central/v/com.komoju.mobile.sdk/shared?style=flat&logo=apple&label=iOS&color=3CC239)

# KOMOJU Mobile SDK

This **Komoju Mobile SDK** allows you to seamlessly integrate secure and reliable payment experiences into your Naive Android and iOS app.

[API DOCS](https://cautious-adventure-g6zje9v.pages.github.io/)

## Get Started

### Android
#### Requirements
* Android API 24+
* Gradle 8.X.X+

The Android SDK utilize the latest version of jetpack compose.

#### Setup
```kotlin
dependencies {
    implementation("com.komoju.mobile.sdk:android:0.0.1")
}
```

### iOS
#### Requirements
* iOS 13+
The iOS SDK utilize the SwiftUI.

#### Setup
Add below to your Podfile
```shell
pod 'komoju.mobile.sdk.ios', '~> 0.0.1'
```

Once done, run `pod install` to fetch the komoju sdk

Find Platform specific guide below.
* [Android](https://doc.komoju.com/docs/android)
* [iOS](https://doc.komoju.com/docs/ios)

Or you can browse the [Android Sample](https://github.com/degica/komoju-mobile-sdk/tree/main/example-android) or [iOS Sample]() to know more :)
