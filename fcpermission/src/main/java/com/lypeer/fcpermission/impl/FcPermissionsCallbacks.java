package com.lypeer.fcpermission.impl;

import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by lypeer on 16/9/1.
 */
public interface FcPermissionsCallbacks extends
        ActivityCompat.OnRequestPermissionsResultCallback {

    void onPermissionsGranted(int requestCode, List<String> perms);
    void onPermissionsDenied(int requestCode, List<String> perms);
}