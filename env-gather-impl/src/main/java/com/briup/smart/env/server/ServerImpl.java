package com.briup.smart.env.server;

import com.briup.smart.env.entity.Environment;
import org.apache.log4j.Logger;

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
    Logger logger = Logger.getRootLogger();
    @Override
    public void reciver() throws Exception {
        try (ServerSocket ss = new ServerSocket(9999);){
            logger.info("服务器正在启动" + ss.getLocalPort()+"等待客户端连接");
            while (true) {//while循环实现一直接受客户端数据
                Socket s = ss.accept();
                new Thread(new Runnable() {//一个Socket绑定一个线程
                    @Override
                    public void run() {
                        try (ObjectInputStream ois = new ObjectInputStream(s.getInputStream());){
                            logger.debug("服务端接收到连接" + s);
                            Collection<Environment> c = (Collection<Environment>) ois.readObject();
                            logger.debug("服务器接收到数据" + c.size());
                            //将十万个对象存到数据库中
                            DBStore dbStore = new DBStoreImpl();
                            dbStore.saveDB(c);
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
