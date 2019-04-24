package com.lxy.test.interview.enums;

import lombok.Getter;

/**
 * @author lxy
 * @date 2019/4/21
 */
public enum CountTryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");
    @Getter
    private int id;
    @Getter
    private String countTryName;

    CountTryEnum(int id, String countTryName) {
        this.id = id;
        this.countTryName = countTryName;
    }

    public static CountTryEnum forEach_CountryEnum(int index) {
        CountTryEnum[] myArray = CountTryEnum.values();
        for (CountTryEnum element : myArray) {
            if (index == element.getId()) {
                return element;
            }
        }
        return null;
    }

}
