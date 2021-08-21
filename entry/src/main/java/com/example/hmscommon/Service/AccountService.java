package com.example.hmscommon.Service;

import annotation.mapping.RequestMapping;
import annotation.method.Post;
import annotation.param.RequestBody;
import com.example.hmscommon.model.api.Response;
import com.example.hmscommon.model.api.user.LoginModel;
import com.example.hmscommon.model.api.user.UserRspModel;
import proxy.NettyRequest;

@RequestMapping("/user")
public interface AccountService {
    @Post("/login")
    NettyRequest<Response<UserRspModel>> login(@RequestBody LoginModel loginModel);
}
