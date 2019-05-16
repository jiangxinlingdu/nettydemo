package io.netty.example.echo.test;


import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest {

    public static void main(String[] argv) {

        // tickDuration和unit 每格的时间间隔
        //ticksPerWheel 一圈下来有几格 如果传入的不是2的N次方，则会调整为大于等于该参数的第一个2的N次方，好处是可以优化hash值的计算
        final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 5, TimeUnit.SECONDS, 2);

        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 1 will run per 5 seconds ");
                //结束时候再次注册
                timer.newTimeout(this, 5, TimeUnit.SECONDS);
            }
        }, 5, TimeUnit.SECONDS);


        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 2 will run per 10 seconds");
                //结束时候再注册
                timer.newTimeout(this, 10, TimeUnit.SECONDS);
            }
        }, 10, TimeUnit.SECONDS);


        //该任务仅仅运行一次
        timer.newTimeout(timeout -> System.out.println("task 3 run only once ! "), 15, TimeUnit.SECONDS);

    }



}