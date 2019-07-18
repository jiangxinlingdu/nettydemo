package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test2 {
    /**
     * 【强制】Object 的 equals 方法容易抛空指针异常，应使用常量或确定有值的对象来调用equals。
     * 正例："test".equals(object);
     * 反例：object.equals("test");
     */
    public static void main(String[] args) {

        String str = null;

        if (str.equals("test")) {

        }
    }
}
