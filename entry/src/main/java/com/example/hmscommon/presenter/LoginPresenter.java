package com.example.hmscommon.presenter;

import com.example.common.model.DataSource;
import com.example.common.thread.Run;
import com.example.hmscommon.contract.LoginContract;
import com.example.hmscommon.helper.AccountHelper;
import com.example.hmscommon.model.api.user.LoginModel;
import com.example.hmscommon.model.api.user.UserRspModel;
import com.example.hmscommon.model.entity.User;

public class LoginPresenter implements LoginContract.Presenter, DataSource.DataCallback<UserRspModel> {
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
    }
    @Override
    public void login(String account, String password) {
        LoginModel loginModel = new LoginModel(account, password);
        AccountHelper.login(loginModel, LoginPresenter.this);
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        if (mView != null) {
            mView.setPresenter(null);
        }
        mView = null;
    }

    @Override
    public void onSuccess(UserRspModel userRspModel) {
        Run.runOnUIThread(() -> {
            mView.showLoginSuccess(userRspModel);
        });
    }

    @Override
    public void onDataNotAvailable(String message) {
        Run.runOnUIThread(()->{
            mView.showLoginFailure(message);
        });
    }
}
