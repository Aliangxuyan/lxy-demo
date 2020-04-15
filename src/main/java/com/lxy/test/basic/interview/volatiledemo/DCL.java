package com.lxy.test.basic.interview.volatiledemo;

/**
 * @author lxy
 * @date 2020-04-12
 */
public class DCL {
    private static DCL instance = null;

    private DCL() {
    }

    public static DCL getInstance() {
        if (instance == null) {
            synchronized (DCL.class) {
                if (instance == null) {
                    instance = new DCL();
                }
            }
        }
        return instance;
    }
}
