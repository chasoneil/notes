package com.chason.sort.heap;

/**
 * 前缀树
 */
public class Code04_TrieTree {

    static class Node {

        public int pass;
        public int end;

        public Node[] nexts;    // 这个我们可以理解为路经

        public Node () {
            pass = 0;
            end = 0;
            // next[i] == null i方向的路不存在 相反则存在
            nexts = new Node[26]; // 因为我们要存放字符串中的数据，那么我们默认只有小写字母，所以分支路经最多有26个
        }
    }

    public static class Trie1 {

        private Node root;      // 所有的前缀树都是从根节点开始

        public Trie1 () {
            root = new Node();
        }

        // 通过字符串构建前缀树
        public void insert(String string) {

            if (string == null) return;

            char[] chs = string.toCharArray();
            Node node = root;
            node.pass++;            // 只要经过， pass++

            int path = 0;          // 记录走哪条路
            for (int i=0; i<chs.length; i++) {
                path = chs[i] - 'a';       // 0 a | 1 b | 2 c ...
                if (node.nexts[path] == null) { // 这个节点从来没有被创建过
                    Node tmp = new Node();
                    node.nexts[path] = tmp;
                }
                // 如果被创建过，意味着这条路已经有了
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        /**
         *  string 之前被加入过几次
         * @return
         */
        public int search (String string) {

            if (string == null) return 0;
            char[] chs = string.toCharArray();

            Node node = root;
            int path = 0;
            for (int i=0; i<chs.length; i++) {
                path = chs[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        /**
         * prefix 这个前缀出现了几次
         * @param prefix
         * @return
         */
        public int prefixSearch (String prefix) {

            if (prefix == null) return 0;

            Node node = root;
            int path = 0;
            char[] chs = prefix.toCharArray();
            for (int i=0; i<chs.length; i++) {
                path = chs[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }

        /**
         * 从前缀树中删除
         * @param string
         */
        public void delete (String string) {

            // 首先要查一下是否加入过再删除
            if (search(string) != 0) {

                Node node  = root;
                char[] chs = string.toCharArray();
                node.pass--;

                int path = 0;
                for (int i=0; i<chs.length; i++) {
                    path = chs[i] - 'a';
                    node = node.nexts[path];
                    node.pass--;
                }

            }
        }

    }

}
