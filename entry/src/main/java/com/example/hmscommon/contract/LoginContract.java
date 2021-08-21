package com.example.hmscommon.contract;

import com.example.common.base.BaseContract;
import com.example.common.base.BasePresenter;
import com.example.hmscommon.model.api.user.UserRspModel;

public interface LoginContract {
    interface Presenter extends BaseContract.Presenter {
        void login(String account, String password);
    }

    interface View extends BaseContract.View<Presenter> {
        void showLoginSuccess(UserRspModel userRspModel);
        void showLoginFailure(String message);
    }
}
