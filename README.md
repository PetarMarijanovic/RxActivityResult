[ ![Download](https://api.bintray.com/packages/petarmarijanovic/maven/rx-activity-result/images/download.svg) ](https://bintray.com/petarmarijanovic/maven/rx-activity-result/_latestVersion)
[![CircleCI](https://circleci.com/gh/PetarMarijanovic/RxActivityResult/tree/master.svg?style=svg)](https://circleci.com/gh/PetarMarijanovic/RxActivityResult/tree/master)

# RxActivityResult

With this simple library use your OnActivityResult results in any class (not just an Activity or Fragment) and use it in a reactive way within your observable chains. 

The library attaches one fragment to the desired activity and listens to OnActivityResult. The source code is easy to read and understand.
**Library code is written in Java to avoid unnecessary dependencies, and Sample code is written in Kotlin to avoid unnecessary boilerplate.** :)

![RxActivityResult](https://media.giphy.com/media/mK5qCqlKPF8ac/giphy.gif)

Usage
-----

Simply instantiate it with an Activity object and use it anywhere, or if you are using Dagger, create it in your Activity scope and provide to all other classes that need to use it.
```kotlin
  RxActivityResult(this) // this -> Activity
```

To start an activity for result call the `start` method and send it in the `intent` you wish to start. The method returns a `Single` and **when you subscribe** to it the intent is sent and you get the result for it.
```kotlin
  rxActivityResult.start(intent) // intent -> bluetooth, gallery, other activity...
        .subscribe({ Log.d("Result", it) }, { it.printStackTrace() })       
```

The result class consists of:
```java
  // Fields
  int getResultCode();

  Intent getData();

  // Convenience methods
  boolean isOk();

  boolean isCanceled();

  boolean isFirstUser();
```
Download
--------

#### Gradle:
```groovy
repositories {
    jcenter()
}
    
dependencies {
    compile 'com.petarmarijanovic:rx-activity-result:1.1.0'
}
```

#### Maven:
```xml
<dependency>
  <groupId>com.petarmarijanovic</groupId>
  <artifactId>rx-activity-result</artifactId>
  <version>1.1.0</version>
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
