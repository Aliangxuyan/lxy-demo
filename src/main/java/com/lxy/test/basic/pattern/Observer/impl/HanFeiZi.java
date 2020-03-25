package com.lxy.test.basic.pattern.Observer.impl;

import com.lxy.test.basic.pattern.Observer.IHanFeiZi;
import lombok.Data;

/**
 * @author lxy
 * @date 2020-03-25
 */
@Data
public class HanFeiZi implements IHanFeiZi {
    //韩非子是否在吃饭，作为监控的判断标准
    private boolean isHaveBreakfast = false; //韩非子是否在娱乐
    private boolean isHaveFun = false;

    //韩非子要吃饭了
    public void haveBreakfast() {
        System.out.println("韩非子:开始吃饭了...");
        this.isHaveBreakfast = true;
    }

    //韩非子开始娱乐了,古代人没啥娱乐，你能想到的就那么多
    public void haveFun() {
        System.out.println("韩非子:开始娱乐了...");
        this.isHaveFun = true;
    }
}
