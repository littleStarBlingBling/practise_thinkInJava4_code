package com.practise.concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class NIOBlocked implements Runnable{
    private final SocketChannel socketChannel;

    public NIOBlocked(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try{
            System.out.println("Waiting for read() in " + this);
            socketChannel.read(ByteBuffer.allocate(1));
        }catch (ClosedByInterruptException e){
            System.out.println("ClosedByInterruptedException");
        }catch (AsynchronousCloseException e){
            System.out.println("AsynchronousClosedException");
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        System.out.println("Exiting NIOBlocked.run() " + this);
    }
}

public class NIOInterruption {
    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);

        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);

        Future<?> future = exec.submit(new NIOBlocked(sc1));
        exec.execute(new NIOBlocked(sc2));

        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);
        future.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        sc2.close();
    }
}
