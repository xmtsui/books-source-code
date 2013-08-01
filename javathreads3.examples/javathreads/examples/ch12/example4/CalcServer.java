package javathreads.examples.ch12.example4;

import java.util.concurrent.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import javathreads.examples.ch12.*;

public class CalcServer extends TCPNIOServer {

    static ThreadPoolExecutor pool;

    class FibClass implements Runnable {
        long n;
        SocketChannel clientChannel;
        ByteBuffer buffer = ByteBuffer.allocateDirect(8);

        FibClass(long n, SocketChannel sc) {
            this.n = n;
            clientChannel = sc;
        }

        private long fib(long n) {
            if (n == 0)
                return 0L;
            if (n == 1)
                return 1L;
            return fib(n - 1) + fib(n - 2);
        }

        public void run() {
            try {
                long answer = fib(n);
                buffer.putLong(answer);
                buffer.flip();
                clientChannel.write(buffer);
                if (buffer.remaining() > 0) {
                    Selector s = Selector.open();
                    clientChannel.register(s, SelectionKey.OP_WRITE);
                    while (buffer.remaining() > 0) {
                        s.select();
                        clientChannel.write(buffer);
                    }
                    s.close();
                }
            } catch (IOException ioe) {
                System.out.println("Client error " + ioe);
            }
        }
    }

    protected void handleClient(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(8);
        sc.read(buffer);
        buffer.flip();
        long n = buffer.getLong();
        FibClass fc = new FibClass(n, sc);
        pool.execute(fc);
    }

    protected void registeredClient(SocketChannel sc) {
    }

    public static void main(String[] args) throws Exception {
        CalcServer cs = new CalcServer();
        cs.port = Integer.parseInt(args[0]);
        int tpSize = Integer.parseInt(args[1]);
        pool = new ThreadPoolExecutor(
                        tpSize, tpSize, 50000L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());
        cs.run();
        System.out.println("Calc server waiting for requests...");
    }
}
