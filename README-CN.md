# FcPermissions

##效果展示

![效果展示](http://ac-cnyv47la.clouddn.com/d86cf7546b46ad5c.gif)

##引入框架

```java
 allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
   }
```
```java
dependencies {
        compile 'com.github.lypeer:FcPermissions:v0.0.1'
   }
```

##用法简介

FcPermissions提供了三种方法来进行权限的请求，分别是实现接口，继承抽象类，构建builder。具体的实现细节可去这里看：[FcPermissions：也许是目前最好的动态权限请求库](http://blog.csdn.net/luoyanglizi/article/details/52412883)

或者大家可以直接去看库里面的demo。

##开源协议

```
Copyright 2014-2016 lypeer.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
