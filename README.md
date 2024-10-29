<p> <img src=".github/assets/komoju_logo.svg" width="350" title="hover text" alt="Komoju Logo"></p>

[![License](https://img.shields.io/github/license/stripe/stripe-android)](https://github.com/stripe/stripe-android/blob/master/LICENSE)
![Android Version](https://img.shields.io/maven-central/v/com.komoju.mobile.sdk/android?style=flat&logo=android&label=Android&color=3CC239)
![Shared Version](https://img.shields.io/maven-central/v/com.komoju.mobile.sdk/shared?style=flat&logo=kotlin&label=Shared&color=3CC239)
![iOS Version](https://img.shields.io/maven-central/v/com.komoju.mobile.sdk/shared?style=flat&logo=apple&label=iOS&color=3CC239)

# KOMOJU Mobile SDK

This **Komoju Mobile SDK** allows you to seamlessly integrate secure and reliable payment experiences into your Naive Android and iOS app.

[API DOCS](https://tech.degica.com/komoju-mobile-sdk//)

## Get Started

### Android
#### Requirements
* Android API 24+
* Gradle 8.X.X+

The Android SDK utilize the latest version of jetpack compose.

#### Setup
```kotlin
dependencies {
    implementation("com.komoju.mobile.sdk:android:<latest-version-here>")
}
```

Find Platform specific guide below.
* [Android](https://doc.komoju.com/docs/android)

Or you can browse the [Android Sample](https://github.com/degica/komoju-mobile-sdk/tree/main/example-android) :)

### Contribute
#### Android

* Need Android Studio
* Make sure your server provides key and session or setup your own test server as per https://github.com/degica/komoju-mobile-sdk/tree/main/server
* create `local.properties` file and put server urls inside it, e.g. `TEST_SERVER_URL=<your_server_url>`
* You should be able to run the demo app now.
* Raise Your PR.
* We will review, discuss and merge as per requirements

## License

MIT License

Copyright (c) 2024 Degica

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
