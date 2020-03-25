package com.lxy.test.basic.pattern.Adapter;

import com.lxy.test.basic.pattern.Adapter.impl.UserInfo;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class App {
    public static void main(String[] args) {
        //没有与外系统连接的时候，是这样写的
        IUserInfo youngGirl = new UserInfo(); //从数据库中查到101个
        for (int i = 0; i < 101; i++) {
            youngGirl.getMobileNumber();
        }

    }
}
