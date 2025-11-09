package com.briup.smart.env.server;

import com.briup.smart.env.entity.Environment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

/**
 * @author 86151
 * @program: IoT-Environmental-Data-Center
 * @description
 * @create 2025/11/09 15:21
 **/
public class ServerImpl implements Server {
    @Override
    public void reciver() throws Exception {
        try (ServerSocket ss = new ServerSocket(9999);){
            System.out.println("服务器正在启动" + ss.getLocalPort()+"等待客户端连接");
            while (true) {//while循环实现一直接受客户端数据
                Socket s = ss.accept();
                new Thread(new Runnable() {//一个Socket绑定一个线程
                    @Override
                    public void run() {
                        try (ObjectInputStream ois = new ObjectInputStream(s.getInputStream());){
                            System.out.println("服务端接收到连接" + s);
                            Collection<Environment> c = (Collection<Environment>) ois.readObject();
                            System.out.println("服务器接收到数据" + c.size());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //用于关闭服务器【服务端一直开启，如果需要关闭服务】
    @Override
    public void shutdown() throws Exception {

    }
}
