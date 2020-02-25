package com.lxy.test.basic.java8;

import com.lxy.test.basic.java8.bean.Student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lxy
 * @date 2019-05-06
 *
 * stream 流
 */
public class StreamTest {

    public static void main(String[] args) {

        List<String> lists = Arrays.asList("a", "b", "n", "c", "d", "e");
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"lisi"));
        students.add(new Student(40,"lisi"));
        students.add(new Student(23,"lisi"));

        List<String> resultList = lists.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        List<Student> resultStudent = students.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList());

        System.out.println(resultList.toString());
        System.out.println(resultStudent.toString());

    }
}
