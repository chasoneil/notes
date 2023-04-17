package com.chason.leetcode.string;

public class _13_整数转罗马数字_ {

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }

    public static String intToRoman(int num) {

        String res = "";
        if (num == 0) return res;

        int thou = num / 1000;  // 千

        if (thou > 0) {
            for (int i=0; i<thou; i++) {
                res += "M";
            }
        }

        num = num - (thou * 1000);

        // 百位
        int hun = num / 100;  // 百位数一定在 0-9 之间

        int thun = 0;
        if (hun >= 5) {
            if (hun == 9) {
                res += "CM";
            } else {
                res += "D";
                thun = hun - 5;
                for (int i=0; i<thun; i++) {
                    res += "C";
                }
            }
        } else {
            if (hun == 4) {
                res += "CD";
            } else {
                thun = hun;
                for (int i=0; i<thun; i++) {
                    res += "C";
                }
            }
        }

        num = num - (hun * 100);

        // 十位
        int ten = num / 10;  // 百位数一定在 0-9 之间

        int tten = 0;
        if (ten >= 5) {
            if (ten == 9) {
                res += "XC";
            } else {
                res += "L";
                tten = ten - 5;
                for (int i=0; i<tten; i++) {
                    res += "X";
                }
            }
        } else {
            if (ten == 4) {
                res += "XL";
            } else {
                tten = ten;
                for (int i=0; i<tten; i++) {
                    res += "X";
                }
            }

        }

        num = num - (ten * 10);

        // 处理个位
        if (num >= 5) {
            if (num == 9) {
                res += "IX";
            } else {
                res += "V";
                num -= 5;
                for (int i=0; i<num; i++) {
                    res += "I";
                }
            }
        } else {
            if (num == 4) {
                res += "IV";
            } else {
                for (int i=0; i<num; i++) {
                    res += "I";
                }
            }
        }
        return res;
    }

}
