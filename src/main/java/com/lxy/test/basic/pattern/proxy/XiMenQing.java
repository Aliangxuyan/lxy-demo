package com.lxy.test.basic.pattern.proxy;

import com.lxy.test.basic.pattern.proxy.impl.WangPo;

/**
 * @author lxy
 * @date 2020-03-25
 * <p>
 * 看到没，虽然表面上时王婆，实际上潘金莲
 */
public class XiMenQing {
    public static void main(String[] args) {
        WangPo wangPo = new WangPo();
        wangPo.makeEyesWithMan();
        wangPo.happyWithMan();
    }
}
