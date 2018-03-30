# EncryptedGsonConverter
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/beigirad/EncryptedGsonConverter.svg)](https://jitpack.io/#beigirad/EncryptedGsonConverter)

A `Retrofit Converter` which based on [Gson-Converter][0] also can encrypt/decrypt JSON.

Used [Encryption][1] library for encryption output and input.

Uses instead of default gson-convertor if you don't need to encryption.

# How Works?
### Receive (ResponseBody)
#### #1. Plain text received from server:
```
PRpqRXhYkTxmAK0Ndh/Fg2IAV+FSiaMrQx0IgXTLdmojfsF70SMPLyUYE2XLGCWZgTKYISe+RSDFDqEwZ35N+g==
```
##### #2. Decrypt
```
{
	"first-name":"Farhad",
	"last-name":"Beigirad"
}
```
#### #3. Deserialize
```
Person class
```
### Send (RequestBody)
#### #1. Java Object
```
Person person = new Person( "Farhad" , "Beigirad" );
```
#### #2. Serialize
```
{
	"first-name":"Farhad",
	"last-name":"Beigirad"
}
```
#### #3. Output text for send to server:
```
PRpqRXhYkTxmAK0Ndh/Fg2IAV+FSiaMrQx0IgXTLdmojfsF70SMPLyUYE2XLGCWZgTKYISe+RSDFDqEwZ35N+g==
```




# Setup
#### Step #1. Add the JitPack repository to root build.gradle file:

```gradle
allprojects {
    repositories {
	...
	maven { url "https://jitpack.io" }
    }
}
```

#### Step #2. Add the dependency

```groovy
dependencies {

    implementation 'com.github.beigirad:EncryptedGsonConverter:1.0.0'
    
}
```
# Implementation

```java
Encryption encryption = Encryption.getDefault("MyKey", "MySalt", new byte[16]);

retrofit = new Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        
        // for encryption
        .addConverterFactory(GsonEncryptConverterFactory.create(encryption))
        // for non encryption
        .addConverterFactory(GsonEncryptConverterFactory.create())

        .build();
```

# License
Copyright 2018 Farhad Beigirad

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[0]: https://github.com/square/retrofit/tree/master/retrofit-converters/gson
[1]: https://github.com/simbiose/Encryption

