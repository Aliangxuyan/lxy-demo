package com.lxy.test.middle.zookeeper;

/**
 * @author lxy
 * @date 2019-09-09
 */
public class LockException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LockException(String e) {
        super(e);
    }

    public LockException(Exception e) {
        super(e);
    }
}