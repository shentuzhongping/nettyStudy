package com.shentu.nio;

import com.shentu.nio.channelHanndel.MyServerChannelHandeler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyServer {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup chiledGroup = new NioEventLoopGroup(2);

        ServerBootstrap  bootstrap = new ServerBootstrap();
        try {
            ChannelFuture future = bootstrap.group(bossGroup, chiledGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("socketChannel is init");
                            socketChannel.pipeline().addLast(new MyServerChannelHandeler());
                            channelGroup.add(socketChannel);
                            System.out.println("当前socketChannel的数量"+channelGroup.size());
                        }
                    })
                    .bind(8888)
                    .sync();
            System.out.println("服务器已经启动");
            System.out.println(future);

            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            bossGroup.shutdownGracefully();
            chiledGroup.shutdownGracefully();
        }
    }
}
