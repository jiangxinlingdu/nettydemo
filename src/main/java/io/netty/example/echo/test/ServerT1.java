package io.netty.example.echo.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class ServerT1 {
    public static void main(String[] args) {

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup woker = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(boss,woker)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.SO_RCVBUF,5*1024)
                    .childOption(ChannelOption.SO_SNDBUF,5*1024)
                  //  .handler(new BizHander())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                           // pipeline.addLast(new BizHander());
                            pipeline.addLast(new ChannelInboundHandlerAdapter1());
                            pipeline.addLast(new ChannelInboundHandlerAdapter2());
                            pipeline.addLast(new ChannelInboundHandlerAdapter3());
                            pipeline.addLast(new ChannelOutboundHandlerAdapter1());
                            pipeline.addLast(new ChannelOutboundHandlerAdapter2());
                            pipeline.addLast(new ChannelOutboundHandlerAdapter3());
                        }
                    });


            ChannelFuture future = serverBootstrap.bind(8888).sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            woker.shutdownGracefully();
        }

    }
}