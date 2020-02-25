package com.lxy.test.basic.datatructuresalgorithms.datastructure;

/**
 * @author lxy
 * @date 2019/6/19
 */
public class BitMap { // Java 中 char 类型占 16bit，也即是 2 个字节

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(10000);
        bitMap.set(100);
    }
    private char[] bytes;
    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits / 16 + 1];
    }

    public void set(int k) {
        if (k > nbits) return;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        System.out.println(byteIndex+ "****" + bitIndex);
        System.out.println("&&&&&&&&" + (1 << bitIndex));
        System.out.println("6^^^^^^^^^"+bytes[byteIndex]);
        System.out.println("(((((("+(bytes[byteIndex] |= (1 << bitIndex)));
    }

    public boolean get(int k) {
        if (k > nbits) return false;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }
}

