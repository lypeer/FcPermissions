package com.lypeer.fcpermission.ui;

import android.app.Fragment;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.lypeer.fcpermission.FcPermissions;
import com.lypeer.fcpermission.R;
import com.lypeer.fcpermission.impl.FcPermissionsCallbacks;

import java.util.List;

/**
 * Created by lypeer on 16/9/1.
 */
public abstract class FcPermissionsAppFragment extends Fragment implements FcPermissionsCallbacks {
    private DialogInterface.OnClickListener mOnClickListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FcPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    protected void requestPermissions(final Object object, String rationale,
                                      final int requestCode, final String... perms) {
        requestPermissions(object, rationale,
                android.R.string.ok,
                android.R.string.cancel,
                requestCode, perms);
    }

    protected void requestPermissions(final Object object, String rationale,
                                      @StringRes int positiveButton,
                                      @StringRes int negativeButton,
                                      final int requestCode, final String... perms) {
        FcPermissions.requestPermissions(object, rationale, positiveButton, negativeButton, requestCode, perms);
    }

    @NonNull
    protected abstract String getRationale4NeverAskAgain();

    public void getNegativeButtonOnClickListener(DialogInterface.OnClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if(TextUtils.isEmpty(getRationale4NeverAskAgain())){
            throw new IllegalArgumentException("Rationale can not be empty .");
        }
        FcPermissions.checkDeniedPermissionsNeverAskAgain(this, getRationale4NeverAskAgain(),
                R.string.setting, android.R.string.cancel, mOnClickListener, perms);
        onPermissionDenied(requestCode , perms);
    }

    public abstract void onPermissionDenied(int requestCode, List<String> perms);
}
