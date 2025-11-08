package com.briup.environment.client;
import java.sql.Timestamp;


import com.briup.environment.entity.Environment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86151
 * @program: IoT-Environmental-Data-Center
 * @description
 * @create 2025/11/07 11:36
 **/
public class Gather {
    public void gather() {
        //1.读取文件data-file

        try (InputStream in = Gather.class.getClassLoader()
                .getResourceAsStream("data-file-simple");//类加载器加载这个文件，这里读到的是字节
             //缓冲流包装基本字符流
             BufferedReader br = new BufferedReader(new InputStreamReader(in));){
//2.解析每一行数据 ->环境对象
  /*          String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }*/
            List<Environment> list = new ArrayList<>();
            br.lines().forEach(line -> {//io流转换成Stream流
                //一行就是一个对象 一行中的每列就是对象的每个属性
                String[] arr = line.split("\\|");//正则表达式
                Environment env = new Environment();

                env.setSrcId(arr[0]);
                env.setDesId(arr[1]);
                env.setDevId(arr[2]);
                env.setCount(Integer.parseInt(arr[4]));
                env.setCmd(arr[5]);
                env.setStatus(Integer.parseInt(arr[7]));
                env.setGather_date(new Timestamp(Long.parseLong(arr[8])));
                env.setSersorAddress(arr[3]);
                switch (env.getSersorAddress()){
                    case "16":// 5d60 6f78 02
                        env.setName("温度");
                        int wenInt = Integer.parseInt(arr[6].substring(0,4),16);
                        env.setData((wenInt*(0.00268127F)-46.85F));
                        Environment env2 =(Environment) env.clone();
                        env2.setName("湿度");
                        int shiInt = Integer.parseInt(arr[6].substring(4,8),16);
                        env2.setData((shiInt*(0.00190735F)-6));
                        list.add(env2);
                        break;
                    case "256 ": // 001c 03 一个字节是两个16进制
                        //1个字节 8位 0000 0001
                        env.setName("光照强度");
                        env.setData(Integer.parseInt(arr[6].substring(0,4),16));
                        break;
                    case "1280":
                        env.setName("二氧化碳");
                        env.setData(Integer.parseInt(arr[6].substring(0,4),16));
                        break;

                }
                env.setName("");
                env.setData(Float.parseFloat(arr[6]));
                list.add(env);

            });
            System.out.println("总数据量："+list.size());
            //温湿度条数
            long wenshidu = list.stream()
                    .filter(e -> e.getSersorAddress().equals("16"))
                    .count();
            System.out.println("wenshidu = "+ wenshidu);
            //二氧化碳条数
            long  eryanghuatan = list.stream()
                    .filter(e -> e.getSersorAddress().equals("256"))
                    .count();
            System.out.println("eryanghuatan = "+ eryanghuatan);
            //温湿度条数
            long guangzhao = list.stream()
                    .filter(e -> e.getSersorAddress().equals("1280"))
                    .count();
            System.out.println("guangzhao = "+ guangzhao);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //3.将环境对象存储到集合中
        //输出
    }
}
