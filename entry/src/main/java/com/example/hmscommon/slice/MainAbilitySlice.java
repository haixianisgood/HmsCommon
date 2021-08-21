package com.example.hmscommon.slice;

import com.example.common.base.BaseAbilitySlice;
import com.example.common.component.Toast;
import com.example.hmscommon.ResourceTable;
import com.example.hmscommon.fraction.LoginFraction;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.ability.fraction.FractionManager;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.TableLayout;
import ohos.agp.window.dialog.ToastDialog;
import ohos.bundle.IBundleManager;
import ohos.security.SystemPermission;

public class MainAbilitySlice extends BaseAbilitySlice {
    private FractionManager mFractionManager;
    private LoginFraction loginFraction;

    @Override
    protected int layoutId() {
        return ResourceTable.Layout_ability_main;
    }

    @Override
    protected void beforeShow() {

    }

    @Override
    protected void show() {
        loginFraction = new LoginFraction();
        mFractionManager = ((FractionAbility)getAbility()).getFractionManager();
        mFractionManager.startFractionScheduler()
                .add(ResourceTable.Id_window_login_register, loginFraction, "Login Fraction")
                .submit();

        requestPermission();
    }

    private void requestPermission() {
        int REQUEST_CODE = 111111;
        if (verifyCallingOrSelfPermission(SystemPermission.READ_USER_STORAGE) != IBundleManager.PERMISSION_GRANTED ||
                verifyCallingOrSelfPermission(SystemPermission.WRITE_USER_STORAGE) != IBundleManager.PERMISSION_GRANTED ||
                verifyCallingOrSelfPermission(SystemPermission.ACCESS_MISSIONS) != IBundleManager.PERMISSION_GRANTED) {
            requestPermissionsFromUser(new String[]{
                    SystemPermission.READ_USER_STORAGE,
                    SystemPermission.WRITE_USER_STORAGE,
                    SystemPermission.ACCESS_MISSIONS}, REQUEST_CODE);
        } else {
            Toast.show(this, "already requested");
        }
    }
}
