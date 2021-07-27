package com.lxy.test.basic.datatructuresalgorithms.algorithms.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author lxy
 * @date 2020-04-23
 */
public class AlgoTest {
    private int minDist = Integer.MAX_VALUE;
    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;


    public static void main(String[] args) {
        String s = "口味:微辣;作法:爆炒;";
        String[] split = s.split(";");
        System.out.println(Arrays.asList(split));

        HashSet<String> set = new HashSet();
        set.add("口味");
        set.add("微辣");
        set.add("作法");

        System.out.println(new ArrayList(set) {
        });
    }

}
