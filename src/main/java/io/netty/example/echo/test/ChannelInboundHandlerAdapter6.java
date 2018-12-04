package io.netty.example.echo.test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelInboundHandlerAdapter6 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ChannelInboundHandlerAdapter6");

        new Thread(new ThreadTep(ctx.channel())).start();
    }

    class ThreadTep implements Runnable {
        private Channel channel;

        public ThreadTep() {
        }

        public ThreadTep(Channel channel) {
            this.channel = channel;
        }

        public void run() {
            ChannelFuture future = channel.writeAndFlush("hello5");
            future.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("======isDone");
                    if (future.isSuccess()) {
                        Thread.sleep(5000);
                        System.out.println("======isSuccess");
                    }
                }
            });

            System.out.println("继续执行，不阻塞");
        }
    }
}