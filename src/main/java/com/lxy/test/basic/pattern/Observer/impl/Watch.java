package com.lxy.test.basic.pattern.Observer.impl;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class Watch extends Thread {
    private HanFeiZi hanFeiZi;
    private LiSi liSi;
    private String type;

    //通过构造函数传递参数，我要监控的是谁,谁来监控,要监控什么
    public Watch(HanFeiZi _hanFeiZi, LiSi _liSi, String _type) {
        this.hanFeiZi = _hanFeiZi;
        this.liSi = _liSi;
        this.type = _type;
    }

    @Override
    public void run() {
        while (true) {
            if (this.type.equals("breakfast")) { //监控是否在吃早餐 \
                //如果发现韩非子在吃饭，就通知李斯
                if (this.hanFeiZi.isHaveBreakfast()) {
                    this.liSi.update("韩非子在吃饭"); //重置状态，继续监控
                    this.hanFeiZi.setHaveBreakfast(false);
                }
            } else {//监控是否在娱乐
                if (this.hanFeiZi.isHaveFun()) {
                    this.liSi.update("韩非子在娱乐");
                    this.hanFeiZi.setHaveFun(false);
                }
            }
        }
    }


}
