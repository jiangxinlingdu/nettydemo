package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test21 {

    public static boolean a = true;
    public static void main(String[] args) {
        String str = test();
        System.out.println(str);
    }

    private static String test() {
        if (a) {
            return "1";
        }

        //提示更加智能化了！
        //retr
        return "2";
    }
}
