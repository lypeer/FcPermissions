package com.lypeer.fcpermission.impl;

import java.util.List;

/**
 * Created by lypeer on 16/9/1.
 */
public interface OnPermissionsGrantedListener {

    void onPermissionsGranted(int requestCode, List<String> perms);
}
