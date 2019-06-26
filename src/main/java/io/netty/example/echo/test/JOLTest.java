package io.netty.example.echo.test;


import io.netty.util.internal.InternalThreadLocalMap;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

public class JOLTest {
    public static void main(String[] args) throws Exception {
        out.println(ClassLayout.parseClass(InternalThreadLocalMap.class).toPrintable());
        out.println(ClassLayout.parseClass(A.class).toPrintable());
    }


    class  A{
        public volatile long value = 0L;
        public long p1, p2, p3, p4, p5; // comment out

    }

}