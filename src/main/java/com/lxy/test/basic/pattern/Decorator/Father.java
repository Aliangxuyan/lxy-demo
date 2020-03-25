package com.lxy.test.basic.pattern.Decorator;

import com.lxy.test.basic.pattern.Decorator.impl.SugarFouthGradeSchoolReport;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class Father {
    public static void main(String[] args) {
        //成绩单拿过来
        SchoolReport sr = new SugarFouthGradeSchoolReport(); //看成绩单
        sr.report();
        sr.sign("AAA");
    }
}
