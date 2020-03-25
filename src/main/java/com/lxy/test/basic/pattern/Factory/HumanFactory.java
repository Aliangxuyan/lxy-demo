package com.lxy.test.basic.pattern.Factory;

import java.util.HashMap;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class HumanFactory {
    private static HashMap<String,Human> humans = new HashMap<String,Human>();
    public static Human createHuman(Class c) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Human human = null;

        human = (Human) Class.forName(c.getName()).newInstance();

        return human;
    }
}
