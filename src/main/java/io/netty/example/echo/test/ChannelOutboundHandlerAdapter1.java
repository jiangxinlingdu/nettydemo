package io.netty.example.echo.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;


public class ChannelOutboundHandlerAdapter1 extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("ChannelOutboundHandlerAdapter11   ");
        super.write(ctx, msg, promise);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ChannelOutboundHandlerAdapter11  exceptionCaught");
        super.exceptionCaught(ctx, cause);
    }
}