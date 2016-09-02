# FcPermissions

FcPermissions is a library to simplify basic system permissions logic when targeting Android M or higher. Chinese README is **[here](https://github.com/lypeer/FcPermissions/blob/master/README-CN.md)**

## Installation

You can install FcPermissions by adding the following dependency to your `build.gradle`:

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

## Usage

The library applies three way to request permissions : **implementing an interface** , **extending an abstract class** and **creating a builder** .

### Implementing an interface

Firstly , let your `Activity` / `Fragment` implement `FcPermissionsCallbacks` interface . There are two methods in the interface :

 - `void onPermissionsGranted(int requestCode, List<String> perms)` : when user grants the permissions we have requested , this method will be invoked . `Perms` is the list of granted permissions .
 - `void onPermissionsDenied(int requestCode, List<String> perms)` : when user denies the permissions we have requested , this method will be invoked . `Perms` is the list of denied permissions .

What you should remember is that you should invoke `FcPermissions.checkDeniedPermissionsNeverAskAgain()` method in the `onPermissionsDenied()` to handler the solution user clicks "Never Ask Again" ( in the requesting dialog ) :
```java
@Override
public void onPermissionsDenied(int requestCode, List<String> perms) {
    Toast.makeText(this, R.string.prompt_been_denied, Toast.LENGTH_LONG).show();
    FcPermissions.checkDeniedPermissionsNeverAskAgain(this,
            getString(R.string.prompt_we_need_camera),
            R.string.setting, R.string.cancel, null, perms);
}
```

Otherwise , you must override the `onRequestPermissionsResult` method and transport its parameters to `FcPermissions.onRequestPermissionsResult()` :
```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    FcPermissions.onRequestPermissionsResult(requestCode , permissions , grantResults , this);
}
```

At last , when you want to request permissions , you can invoke the `FcPermissions.requestPermissions()` method . You can find the detail about this method in the source code .

A demo using this way to request permissions is **[MainActivity.java](https://github.com/lypeer/FcPermissions/blob/master/app/src/main/java/com/lypeer/fcpermissions/MainActivity.java)** .

### Extending an abstract class

FcPermissions provides three abstract classes :

 - FcPermissionsActivity ：its parent is AppCompatActivity .
 - FcPermissionsFragment : its parent is android.support.v4.app.fragment .
 - FcPermissionsAppFragment : its parent is android.app.fragment .

You can choose a suitable class to extend . They are abstract class , so you must override some methods after extending them :

 - `void onPermissionsGranted(int requestCode, List<String> perms)`：when user grants the permissions we have requested , this method will be invoked . `Perms` is the list of granted permissions .
 - `void onPermissionDenied(int requestCode, List<String> perms)`：when user denies the permissions we have requested , this method will be invoked . `Perms` is the list of denied permissions .
 - String getRationale4NeverAskAgain() ：The content of the dialog which shows after user click " Never Ask Again" . **Returned value must be a legal String .**

when you want to request permissions , you can invoke the `requestPermissions()` method ( **NOT**`FcPermissions.requestPermissions()` ) . 

A demo using this way to request permissions is **[MainBaseActivity.java](https://github.com/lypeer/FcPermissions/blob/master/app/src/main/java/com/lypeer/fcpermissions/MainBaseActivity.java)** .

### Creating a builder

The last but not least , creating a builder to request permissions . The basic class working for this way is `FcPermissionB.java` ( **NOT** FcPermissions.java above ) . 

Firstly , just like what we do in the fist way , invoke the `FcPermissionsB#checkDeniedPermissionsNeverAskAgain()` method in the `onPermissionsDenied()` to handler the solution user clicks "Never Ask Again" ( in the requesting dialog ) :
```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    mFcPermissionsB.onRequestPermissionsResult(requestCode , permissions , grantResults , this);
}
```

Then you can create a builder and begin to request permissions :
```java
private void requestCameraPermission() {
    mFcPermissionsB = new FcPermissionsB.Builder(this)
            .onGrantedListener(new OnPermissionsGrantedListener() {
                @Override
                public void onPermissionsGranted(int requestCode, List<String> perms) {

                }
            })
            .onDeniedListener(new OnPermissionsDeniedListener() {
                @Override
                public void onPermissionsDenied(int requestCode, List<String> perms) {

                }
            })
            .positiveBtn4ReqPer(android.R.string.ok)
            .negativeBtn4ReqPer(R.string.cancel)
            .positiveBtn4NeverAskAgain(R.string.setting)
            .negativeBtn4NeverAskAgain(R.string.cancel)
            .rationale4ReqPer(getString(R.string.prompt_request_camara))//必需
            .rationale4NeverAskAgain(getString(R.string.prompt_we_need_camera))//必需
            .requestCode(RC_CAMERA)//必需
            .build();
    mFcPermissionsB.requestPermissions(Manifest.permission.CAMERA);//request permissions
}
```

There are three parameters must be added to builder :

 - rationale4ReqPer() ：The content in the dialog asking whether to request permissions .
 - rationale4NeverAskAgain() ：The content of the dialog which shows after user click " Never Ask Again" . 
 - requestCode() ：Request code of the request .

You can find other parameters's usage in the source code .

A demo using this way to request permissions is **[MainBuilderActivity.java](https://github.com/lypeer/FcPermissions/blob/master/app/src/main/java/com/lypeer/fcpermissions/MainBuilderActivity.java)** .

## Why this library ?

This library is an extension of [easypermissions](https://github.com/googlesamples/easypermissions) , but I change some code in it and create two other ways to make the work easier . So it may be a better choose for you to handler permissions .
