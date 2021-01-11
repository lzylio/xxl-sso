package com.xxl.sso.server.service.impl;

import com.xxl.sso.server.core.model.User;
import com.xxl.sso.server.core.result.ReturnT;
import com.xxl.sso.server.dao.UserDao;
import com.xxl.sso.server.service.UserService;
import com.xxl.sso.server.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ReturnT<User> findUser(String username, String password) {

        if (username==null || username.trim().length()==0) {
            return new ReturnT<User>(ReturnT.FAIL_CODE, "Please input username.");
        }
        if (password==null || password.trim().length()==0) {
            return new ReturnT<User>(ReturnT.FAIL_CODE, "Please input password.");
        }

        // 校验
        User user = userDao.getUserByUserName(username);
        if (user == null) return new ReturnT<User>(ReturnT.FAIL_CODE, "username or password is invalid.");
        if (Md5Utils.valiPassword(password,user.getUserPsw(),user.getSalt())) return new ReturnT<User>(user);
        return new ReturnT<User>(ReturnT.FAIL_CODE, "username or password is invalid.");
    }


}
