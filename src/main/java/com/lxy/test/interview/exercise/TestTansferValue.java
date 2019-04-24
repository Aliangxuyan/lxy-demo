package com.lxy.test.interview.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lxy
 * @date 2019/4/21
 */
public class TestTansferValue {
    public void changeValue2(Person person) {
        person.setPersonName("xxx");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    public void changeValue1(int number) {
        number = 20;
    }

    public static void main(String[] args) {
        TestTansferValue test = new TestTansferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age = " + age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("person.get = " + person.getPersonName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("str = " + str);
    }
}

@AllArgsConstructor
@Data
class Person {
    String personName;
}