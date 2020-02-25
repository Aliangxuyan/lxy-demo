package com.lxy.test.middle.faker;

import com.github.javafaker.Faker;
import org.junit.Test;

/**
 * @author lxy
 * @date 2019/7/31
 *  使用 Java Faker 生成随机数,前提：添加maven 依赖
 *
 * https://mp.weixin.qq.com/s/SE2PX4DBY-VnVIHi8N4p9Q
 */
public class FakerDemo {
    @Test
    public void person(){
        Faker faker = new Faker();
        System.out.println(faker.name().lastName());
        System.out.println(faker.name().firstName());

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(faker.food().measurement());

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(faker.book().author());

    }
}
