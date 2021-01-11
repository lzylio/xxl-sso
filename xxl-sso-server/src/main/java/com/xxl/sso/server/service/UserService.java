package com.xxl.sso.server.service;

import com.xxl.sso.server.core.model.User;
import com.xxl.sso.server.core.result.ReturnT;

public interface UserService {

    public ReturnT<User> findUser(String username, String password);

}
