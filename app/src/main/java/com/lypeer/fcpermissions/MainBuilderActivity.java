package com.lypeer.fcpermissions;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lypeer.fcpermission.FcPermissionsB;
import com.lypeer.fcpermission.impl.OnPermissionsDeniedListener;

import java.util.List;

/**
 * Created by lypeer on 16/9/1.
 */
public class MainBuilderActivity extends AppCompatActivity {
    private static final int RC_CAMERA = 2333;
    private FcPermissionsB mFcPermissionsB;

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
        mFcPermissionsB = new FcPermissionsB.Builder(this)
                .rationale4ReqPer(getString(R.string.prompt_request_camara))
                .onDeniedListener(new OnPermissionsDeniedListener() {
                    @Override
                    public void onPermissionsDenied(int requestCode, List<String> perms) {
                        Toast.makeText(MainBuilderActivity.this , getString(R.string.prompt_been_denied) , Toast.LENGTH_SHORT).show();
                    }
                })
                .rationale4NeverAskAgain(getString(R.string.prompt_we_need_camera))
                .requestCode(RC_CAMERA)
                .build();
        mFcPermissionsB.requestPermissions(Manifest.permission.CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mFcPermissionsB.onRequestPermissionsResult(requestCode , permissions , grantResults , this);
    }
}
