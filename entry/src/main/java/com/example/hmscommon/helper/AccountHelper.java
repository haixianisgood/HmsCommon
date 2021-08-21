package com.example.hmscommon.helper;

import callback.HttpCallback;
import com.example.common.model.DataSource;
import com.example.hmscommon.Service.AccountService;
import com.example.hmscommon.model.api.Response;
import com.example.hmscommon.model.api.user.LoginModel;
import com.example.hmscommon.model.api.user.UserRspModel;
import com.example.hmscommon.model.entity.User;
import proxy.NettyProxy;
import proxy.NettyRequest;

public class AccountHelper {
    private static AccountService accountService;
    static {
        NettyProxy nettyProxy = new NettyProxy();
        nettyProxy.baseUrl("http://192.168.3.4:8080");
        accountService = (AccountService) nettyProxy.bind(AccountService.class);
    }

    public static void login(LoginModel loginModel, DataSource.DataCallback<UserRspModel> callback) {
        NettyRequest<Response<UserRspModel>> nettyRequest = accountService.login(loginModel);
        nettyRequest.requestAsync(new HttpCallback<Response<UserRspModel>>() {
            @Override
            public void onSuccess(Response<UserRspModel> userRspModelResponse) {
                callback.onSuccess(userRspModelResponse.getEntity());
            }

            @Override
            public void onFailed(int i, String s, Exception e) {
                callback.onDataNotAvailable(s);
                e.printStackTrace();
            }
        });
    }
}
