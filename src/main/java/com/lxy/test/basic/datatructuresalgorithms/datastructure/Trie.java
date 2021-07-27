package com.lxy.test.basic.datatructuresalgorithms.datastructure;

/**
 * @author lxy
 * @date 2020-03-19
 * <p>
 * 搜索引擎的搜索关键字提示功能底层_trie
 *
 * https://time.geekbang.org/column/article/72414
 */

public class Trie {

    public static void main(String[] args) {

    }

    private TrieNode root = new TrieNode('/'); // 存储无意义字符

    // 往Trie树中插入一个字符串
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i) {
            //  text[i] 挨个的获取搜索字符串的字符
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    // 在Trie树中查找一个字符串
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            // 获取对应 children 数组的下标
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false; // 不存在pattern
            }
            p = p.children[index];
        }
        if (p.isEndingChar == false) {
            return false; // 不能完全匹配，只是前缀
        } else {
            return true; // 找到pattern
        }
    }

    public class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }
}