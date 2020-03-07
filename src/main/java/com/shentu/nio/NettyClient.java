package com.shentu.nio;

import com.shentu.nio.channelHanndel.MyChannelHandel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup  group = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();
        try {
            ChannelFuture future = b.group(group)
                    .channel(NioSocketChannel.class)
                    //监听channel是否初始化成功
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("channel初始化完成");
                            socketChannel.pipeline().addLast(new MyChannelHandel());
                        }
                    })
                    //初始化channel,监听初始化注册是否成功,如果成功，就尝试与服务器连接
                    .connect("localhost", 8888)
                 .sync();//一个阻塞方法，直到future注册失败或连接异常或连接成功才会结束阻塞

            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            group.shutdownGracefully();
        }

    }
}

