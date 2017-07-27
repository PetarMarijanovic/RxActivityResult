[ ![Download](https://api.bintray.com/packages/petarmarijanovic/maven/rx-activity-result/images/download.svg) ](https://bintray.com/petarmarijanovic/maven/rx-activity-result/_latestVersion)
[![CircleCI](https://circleci.com/gh/PetarMarijanovic/RxActivityResult/tree/master.svg?style=svg)](https://circleci.com/gh/PetarMarijanovic/RxActivityResult/tree/master)

# RxActivityResult

TODO Description

![RxActivityResult](https://media.giphy.com/media/mK5qCqlKPF8ac/giphy.gif)

Usage
-----

TODO Create instance
```kotlin
  RxActivityResult(this)
```

TODO Usage
```kotlin
  rxActivityResult.start(intent)
        .subscribe({ Log.d("Result", it.isOk()) }, { it.printStackTrace() })       
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
