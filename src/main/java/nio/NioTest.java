package nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * java nio 三个核心组件
 * Channels: FileChannel、DataGramChannel、SocketChannel、ServerSocketChannel
 * Buffers: ByteBuffer、CharBuffer、DoubleBuffer、FloatBuffer、IntBuffer、ShortBuffer、LongBuffer
 * Selectors:
 * 其他组件
 * pi
 */

public class NioTest {
    public  static void method1(){
        RandomAccessFile rfile=null;
        try {
            rfile=new RandomAccessFile("D:\\IDEA_SPACE\\files\\interview.txt","rw");
            FileChannel channel=rfile.getChannel();
            ByteBuffer buffer= ByteBuffer.allocate(4);//分配空间
            int byteRead=channel.read(buffer);//写入数据到Buffer
            System.out.println(byteRead);
            while (byteRead!=-1){
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.println((char)buffer.get());//
                }
                buffer.compact();
                byteRead=channel.read(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (rfile!=null){
                try {
                    rfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void client(){
        ByteBuffer buf=ByteBuffer.allocate(1024);
        SocketChannel channel=null;
        try{
            channel=SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress("127.0.0.1",8080));
            if (channel.finishConnect()){
                int i=0;
                while (true){
                    TimeUnit.SECONDS.sleep(1);
                    String info="i am "+i+++" -th information from client.";
                    buf.clear();
                    buf.put(info.getBytes());
                    buf.flip();
                    while (buf.hasRemaining()){
                        System.out.println(buf);
                        channel.write(buf);
                    }
                }
            }
        }catch (IOException e){

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            if(channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void server(){
        ServerSocket socket=null;
        InputStream is=null;
        try {
            socket=new ServerSocket(8080);
            int recvMsgSize=0;
            byte[] recvBuffer=new byte[1024];
            byte[] temp;
            while (true){
                Socket clntSocket=socket.accept();
                SocketAddress addr=clntSocket.getRemoteSocketAddress();
                System.out.println("Handling client  at "+addr);
                is=clntSocket.getInputStream();
                while ((recvMsgSize=is.read(recvBuffer))!=-1){
                    temp=new byte[recvMsgSize];
                    System.arraycopy(recvBuffer,0,temp,0,recvMsgSize);
                    System.out.println(new String(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (socket!=null){
                   socket.close();
                }
                if (is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String [] args){
        method1();
    }
}
