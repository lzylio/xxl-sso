package com.xxl.sso.server.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lizhiyong
 * @createDate: 2021/1/4 16:06
 * @usage:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String userName;
    private String userPsw;
    private String salt;
    private String passwordDecode;

}
