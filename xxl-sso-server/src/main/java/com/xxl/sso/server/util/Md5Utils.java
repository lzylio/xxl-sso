package com.xxl.sso.server.util;

import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

/**
 * @author: lizhiyong
 * @createDate: 2021/1/4 14:30
 * @usage:MD5盐值处理加密，使用shiro包实现，用于cas的用户登录验证，部分配置需要和cas-server中的application.properties保持一致，否则无法校验通过
 *
 *
 * 此工具类来自
 * https://blog.csdn.net/qq_34021712/article/details/80956047
 *
 * 需要引入依赖
 *         <dependency>
 *             <groupId>org.apache.shiro</groupId>
 *             <artifactId>shiro-core</artifactId>
 *             <version>1.3.2</version>
 *         </dependency>
 *
 * 官方文档：http://shiro.apache.org/static/1.2.2/apidocs/org/apache/shiro/crypto/hash/ConfigurableHashService.html
 **/
public class Md5Utils {

    //内部盐（和cas-server中的application.properties保持一致）
    private final static String PRIVATESALT = ".";

    //加密算法（和cas-server中的application.properties保持一致）
    private final static String HASHALGORITHMNAME = "MD5";

    //加密次数（和cas-server中的application.properties保持一致）
    private final static int HASHITERATIONS = 2;


    /**
     * 生成登录密码
     * @param password 需要加密字符串
     * @return
     */
    public static String createPassword(String password) {
        //生成动态盐 可以是uuid 可以是时间戳
        String dynaSalt = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        System.out.println("本次动态盐：" + dynaSalt);

        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(PRIVATESALT));//设置内部盐
        hashService.setHashAlgorithmName(HASHALGORITHMNAME);//设置加密算法
        hashService.setHashIterations(HASHITERATIONS);//设置加密次数
        HashRequest request = new HashRequest.Builder()
                .setSalt(dynaSalt)//设置动态盐
                .setSource(password)//设置需要加密字符串
                .build();
        return hashService.computeHash(request).toHex();
    }

    /**
     * 验证登录密码
     * @param targetPassword 目标密码
     * @param dynaSalt 对应的盐
     * @return
     */
    public static boolean valiPassword(String postPassword, String targetPassword, String dynaSalt) {
        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(PRIVATESALT));//设置内部盐
        hashService.setHashAlgorithmName(HASHALGORITHMNAME);//设置加密算法
        hashService.setHashIterations(HASHITERATIONS);//设置加密次数
        HashRequest request = new HashRequest.Builder()
                .setSalt(dynaSalt)//设置动态盐
                .setSource(postPassword)//设置需要加密字符串
                .build();
        String password = hashService.computeHash(request).toHex();
        return password.equals(targetPassword);
    }

    public static void main(String[] args) {
//        System.out.println(createPassword("0408"));
        /*
        * 本次动态盐：A320A901CE6B40C09F31EBF9AC8B2F3B
50305a20508355ec9ccf1642f2bdc171*/
        System.out.println(valiPassword("0408","50305a20508355ec9ccf1642f2bdc171","A320A901CE6B40C09F31EBF9AC8B2F3B"));

    }
}
