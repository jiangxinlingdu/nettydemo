package io.netty.example.echo.test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelInboundHandlerAdapter5 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ChannelInboundHandlerAdapter5");

        //这段代码 怎么执行都是继续执行，不阻塞 最后，ractor线程里面都是同步的
        ChannelFuture future = ctx.channel().writeAndFlush("hello");
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("======");
                if (future.isSuccess()) {
                    Thread.sleep(5000);
                    System.out.println("======isSuccess");
                }
            }
        });

        System.out.println("继续执行，不阻塞");

    }

}