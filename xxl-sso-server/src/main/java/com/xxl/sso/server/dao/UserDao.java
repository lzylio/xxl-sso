package com.xxl.sso.server.dao;

import com.xxl.sso.server.core.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User getUserById(String id);

    User getUserByUserName(String userName);
}
