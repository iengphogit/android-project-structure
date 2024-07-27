package com.reansen.coreprojectstructure.protocol

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder

class ProtocolTcpServer {

    fun start(port: Int) {
        // Create EventLoopGroups
        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup()

        try {
            // Bootstrap
            val bootstrap = ServerBootstrap()
            bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(ch: SocketChannel) {
                        val p: ChannelPipeline = ch.pipeline()
                        // Add handlers
                        p.addLast(StringDecoder())
                        p.addLast(StringEncoder())
                        p.addLast(TcpServerHandler())
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)

            // Bind and start to accept incoming connections.
            val channelFuture = bootstrap.bind(port).sync()

            // Wait until the server socket is closed.
            channelFuture.channel().closeFuture().sync()
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }

    }

    class TcpServerHandler : ChannelInboundHandlerAdapter() {

        override fun channelActive(ctx: ChannelHandlerContext) {
            super.channelActive(ctx)
            println("channelActive: ")
        }

        override fun channelInactive(ctx: ChannelHandlerContext) {
            super.channelInactive(ctx)
            println("channelInactive: ")
        }

        override fun channelRegistered(ctx: ChannelHandlerContext?) {
            super.channelRegistered(ctx)
            println("channelRegistered: ")
        }

        override fun channelUnregistered(ctx: ChannelHandlerContext?) {
            super.channelUnregistered(ctx)
            println("channelUnregistered")
        }

        override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
            // Print received message
            println("Received message: $msg")

            ctx.writeAndFlush("Callback: $msg")
        }

        @Deprecated("Deprecated in Java")
        override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
            cause.printStackTrace()
            println("exceptionCaught: ${cause.message}")
            ctx.close()
        }
    }

}