package io.netty.example.echo.test;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


//Mode.Throughput 吞吐量纬度  例如“1秒内可以执行多少次调用”，单位是操作数/时间   越大性能越好。
// Mode.AverageTime 平均时间 例如“每次调用平均耗时xxx毫秒”，单位是时间/操作数    越小性能越好。
// Mode.SampleTime 抽样检测   例如“99%的调用在xxx毫秒以内，99.99%的调用在xxx毫秒以内”
// Mode.SingleShotTime 检测一次调用
// Mode.All 运用所有的检测模式 在方法级别指定@BenchmarkMode的时候可以指定多个纬度，
// 例如： @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})，代表同时在多个纬度对目标方法进行测量。
@BenchmarkMode(Mode.Throughput) // 吞吐量

@OutputTimeUnit(TimeUnit.MILLISECONDS) // 结果所使用的时间单位

//Scope.Thread：默认的 State，每个测试线程分配一个实例；  不存在线程安全问题
// Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能 也可以测试线程安全问题；
// Scope.Group：每个线程组共享一个实例；
@State(Scope.Thread) // 每个测试线程分配一个实例

//测试线程的数量，可以配置在方法或者类上，代表执行测试的线程数量。
@Threads(1)

@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"}) // Fork进行的数目,2表示用两个进程来进行测试，以及jvm参数

//iterations：预热的次数。 time：每次预热的时间。 timeUnit：时间单位，默认是s。 batchSize：批处理大小，每次操作调用几次方法。
// 先预热5轮 每隔1秒进行一次预热操作
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)

@Measurement(iterations = 10) // 进行10轮测试 配置的选项和warmup一样。

public class JMHTest {
    @Param({"10", "40", "70", "100"}) // 定义四个参数，之后会分别对这四个参数进行测试
    private int n;

    private List<Integer> array;
    private List<Integer> list;

    //Level.Trial 只会在个基础测试的前后执行。包括Warmup和Measurement阶段，一共只会执行一次。
    // Level.Iteration 每次执行基准测试方法的时候都会执行，如果Warmup和Measurement都配置了2次执行的话，那么@Setup和@TearDown配置的方法的执行次数就4次。
    // Level.Invocation 每个方法执行的前后执行（一般不推荐这么用）

    @Setup(Level.Trial) // 初始化方法，在全部Benchmark运行之前进行
    public void init() {
        array = new ArrayList<>(0);
        list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            array.add(i);
            list.add(i);
        }
    }

    @Benchmark
    public void arrayTraverse() {
        for (int i = 0; i < n; i++) {
            array.get(i);
        }
    }

    @Benchmark
    public void listTraverse() {
        for (int i = 0; i < n; i++) {
            list.get(i);
        }
    }


    @TearDown(Level.Trial) // 结束方法，在全部Benchmark运行之后进行
    public void arrayRemove() {
        for (int i = 0; i < n; i++) {
            array.remove(0);
            list.remove(0);
        }
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(JMHTest.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}

