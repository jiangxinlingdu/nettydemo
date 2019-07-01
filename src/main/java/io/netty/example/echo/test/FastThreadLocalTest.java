package io.netty.example.echo.test;


import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.io.IOException;

public class FastThreadLocalTest {
    private static FastThreadLocal<Integer> fastThreadLocal = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal1 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal2 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal3 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal4 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal5 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal6 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal7 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal8 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal9 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal19 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal29 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal28 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal27 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal26 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal25 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal24 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal23 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal22 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal21 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal20 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal39 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal79 = new FastThreadLocal<Integer>();
    private static FastThreadLocal<Integer> fastThreadLocal99 = new FastThreadLocal<Integer>();

    public static void main(String[] args) throws IOException {

        //if (thread instanceof FastThreadLocalThread) 所以 这里是FastThreadLocalThread而不是普通线程
        new FastThreadLocalThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    fastThreadLocal99.set(i);
                    System.out.println(Thread.currentThread().getName() + "====" + fastThreadLocal99.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "fastThreadLocal1").start();


        new FastThreadLocalThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "====" + fastThreadLocal99.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "fastThreadLocal2").start();

        //为了查看引用怎么运行的 Reference Handler，如果pending有值，就会调用enqueue存入queue
        // 这里有个例外，堆外内存申请时的Cleaner对象，只会执行它的clean方法，并不会放到queue中。
        System.gc();
        System.in.read();
    }
}