package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test20 {

    public static boolean a = true;
    public static void main(String[] args) {
        String str = test();
        System.out.println(str);
    }

    //Constant conditions & exceptions
    private static String test() {
        if (a) {
            return "1";
        }

        if(!a){
            return "2";
        }

        return "0";
    }
}
