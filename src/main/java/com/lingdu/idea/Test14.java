package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test14 {
    public static void main(String[] args) {

        //字符串相关操作


        /**
         * 【推荐】 循环体内，字符串的连接方式，使用 StringBuilder 的 append 方法进行扩展。
         * 说明：下例中，反编译出的字节码文件显示每次循环都会 new 出一个 StringBuilder 对象，然后进行
         * append 操作，最后通过 toString 方法返回 String 对象，造成内存资源浪费。
         *
         * 反例:
            String str = "start";
            for (int i = 0; i < 100; i++) {
                str = str + "hello";
            }
         */

        String str = "start";
        for (int i = 0; i < 100; i++) {
            str = str + "hello";
        }


        /**
         * 1 把str那句话下移
         * 万能键  变成StringBuilder语句
         * 之后在万能键 变成顺序的语句
         *
         * 之后再移动  即可
         */

        //合并
        String testb = "test" + "hello";
    }
}
