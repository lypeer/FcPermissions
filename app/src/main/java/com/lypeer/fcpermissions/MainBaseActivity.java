package com.lypeer.fcpermissions;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.lypeer.fcpermission.ui.FcPermissionsActivity;

import java.util.List;

/**
 * Created by lypeer on 16/9/1.
 */
public class MainBaseActivity extends FcPermissionsActivity {

    private static final int RC_CAMERA = 2333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_request_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestCameraPermission();
            }
        });
    }

    private void requestCameraPermission() {
        requestPermissions(this , getString(R.string.prompt_request_camara) , RC_CAMERA , Manifest.permission.CAMERA);
    }

    @NonNull
    @Override
    protected String getRationale4NeverAskAgain() {
        return getString(R.string.prompt_we_need_camera);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d(getLocalClassName() , getString(R.string.prompt_already_get_permission));
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Log.d(getLocalClassName() , getString(R.string.prompt_been_denied));
    }
}
