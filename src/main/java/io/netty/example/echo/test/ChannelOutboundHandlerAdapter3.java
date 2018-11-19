package io.netty.example.echo.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.TimeUnit;


public class ChannelOutboundHandlerAdapter3 extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("ChannelOutboundHandlerAdapter33   ");
        super.write(ctx, msg, promise);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ChannelOutboundHandlerAdapter33  exceptionCaught");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(final ChannelHandlerContext ctx) throws Exception {

        ctx.executor().schedule(new Runnable() {
            public void run() {
                ctx.channel().write("hello");
            }
        },3,TimeUnit.SECONDS);

        super.handlerAdded(ctx);
    }
}