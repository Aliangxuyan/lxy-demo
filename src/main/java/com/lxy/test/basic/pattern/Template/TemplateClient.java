package com.lxy.test.basic.pattern.Template;

import com.lxy.test.basic.pattern.Template.impl.HummerH1Model;
import com.lxy.test.basic.pattern.Template.impl.HummerH2Model;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class TemplateClient {
    public static void main(String[] args) {
        //客户开着H1型号，出去遛弯了
        HummerModel h1 = new HummerH1Model();
        h1.run(); //汽车跑起来了;
        //客户开H2型号，出去玩耍了
        HummerModel h2 = new HummerH2Model();
        h2.run();
    }
}
