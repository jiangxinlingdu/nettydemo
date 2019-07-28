package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test18 {
    public static void main(String[] args) {
    }

    //多个return重构单个return
    private String test(int a) {
        if (a < 0) {
            return "1";
        } else if (a == 0) {
            return "2";
        } else if (a > 100) {
            return "3";
        } else {
            return "4";
        }
    }
}
