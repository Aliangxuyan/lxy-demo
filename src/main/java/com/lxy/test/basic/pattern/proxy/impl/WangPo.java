package com.lxy.test.basic.pattern.proxy.impl;

import com.lxy.test.basic.pattern.proxy.KindWomen;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class WangPo implements KindWomen {
    private KindWomen kindWomen;

    public WangPo(){
        this.kindWomen = new PanJinLian();
    }

    @Override
    public void makeEyesWithMan() {
        kindWomen.makeEyesWithMan();
    }

    @Override
    public void happyWithMan() {
        kindWomen.happyWithMan();
    }
}
