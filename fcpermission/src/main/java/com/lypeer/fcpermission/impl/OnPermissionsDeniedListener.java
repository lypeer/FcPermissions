package com.lypeer.fcpermission.impl;

import java.util.List;

/**
 * Created by lypeer on 16/9/1.
 */
public interface OnPermissionsDeniedListener {

    void onPermissionsDenied(int requestCode, List<String> perms);
}
