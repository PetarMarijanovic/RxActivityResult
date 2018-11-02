[ ![Download](https://api.bintray.com/packages/petarmarijanovic/maven/rx-activity-result/images/download.svg) ](https://bintray.com/petarmarijanovic/maven/rx-activity-result/_latestVersion)
[![CircleCI](https://circleci.com/gh/PetarMarijanovic/RxActivityResult/tree/master.svg?style=svg)](https://circleci.com/gh/PetarMarijanovic/RxActivityResult/tree/master)

# RxActivityResult

With this simple library use your OnActivityResult results in any class (not just in Activity or Fragment) and use it in a reactive way within your observable chains. 

The library attaches one Fragment to the Activity, sends desired intents and listens to OnActivityResult. You can read more about it in this [Medium article][1].

The source code is easy to read and understand.
**Library code is written in Java to avoid unnecessary dependencies, and Sample code is written in Kotlin to avoid unnecessary boilerplate.** :)

![RxActivityResult](https://media.giphy.com/media/l41JQiEb7pJOqAknu/giphy.gif)

Usage
-----

Simply instantiate it with an Activity object and use it anywhere, or if you are using Dagger, create it in your Activity scope and provide to all other classes that need to use it.
```kotlin
  RxActivityResult(this) // this -> Activity
```

To start an activity for result, call the `start` method and send in the `intent` you wish to start. The method returns a `Single` which returns the `ActivityResult` object.
```kotlin
  rxActivityResult.start(intent) // intent -> bluetooth, gallery, other activity...
        .subscribe({ Log.d("Result", it) }, { it.printStackTrace() })       
```

The `ActivityResult` class consists of:
```kotlin
  // Fields
  val resultCode: Int
  val data: Intent?

  // Convenience methods
  fun isOk(): Boolean
  fun isCanceled(): Boolean
  fun isFirstUser(): Boolean
```

The library also supports sending PendingIntents for Resolutions. See Location button in Sample.

Download
--------

#### Gradle:
```groovy
repositories {
    jcenter()
}
    
dependencies {
    implementation 'com.petarmarijanovic:rx-activity-result:2.2.0'
}
```

#### Maven:
```xml
<dependency>
  <groupId>com.petarmarijanovic</groupId>
  <artifactId>rx-activity-result</artifactId>
  <version>2.2.0</version>
  <type>pom</type>
</dependency>
```

License
-------

    Copyright 2017 Petar Marijanović

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://medium.com/@petar.marijanovic/rxactivityresult-fc2976d497b9
