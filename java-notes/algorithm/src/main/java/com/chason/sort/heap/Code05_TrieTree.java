package com.chason.sort.heap;

import java.util.HashMap;

/**
 * 使用 hash 表实现 前缀树
 *
 * 这种方式实现前缀树 可以解决路不够的问题
 */
public class Code05_TrieTree {


    static class Node1 {

        int pass;
        int end;

        /*
            当你都是小写字符的时候，你当然可以 用大小为26的数组来表示路
            但是
            如果你的要求不仅仅是小写字母，还有大写字母，特殊符号，你没法确定需要多少个元素的存储空间的时候
            很明显 数组这种方式就不合适了
         */

        /*
            key： 字符的值
            value : 下级节点
            因为java中所有的字符都可以转换成整形，以asc码的形式参与计算，那么用Integer就可以了
         */
        HashMap<Integer, Node1> nexts;

        public Node1 () {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    static class Trie2 {

        private Node1 root;

        public Trie2() {
            root = new Node1();
        }

        public void insert(String string) {

            if (string == null) return;

            Node1 node = root;
            node.pass++;
            int path = 0;
            char[] chars = string.toCharArray();

            for (int i=0; i<chars.length; i++) {

                path = (int) chars[i];
                if (!node.nexts.containsKey(path)) {    // 不存在创建hash表
                    node.nexts.put(path, new Node1());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        public void delete () {

        }

        public int search () {
            return 0;
        }

        public int prefixSearch () {
            return 0;
        }

    }


}
