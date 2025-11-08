package com.briup.environment.entity;

import java.sql.Timestamp;

/**
 * @Auther: if
 * @Date: 2025/9/12-09-12-14:17
 * @Description：环境类
 * // 浅拷贝|浅克隆 实现Cloneable接口
 */
public class Environment implements Cloneable{
    // 发送端id
    private String srcId;
    // 树莓派系统id
    private String desId;
    // 实验箱区域模块id(1-8)
    private String devId;
    // 模块上传感器地址 16 | 256 | 1280
    private String sersorAddress;
    private String name; // 温湿度 | 光照 | 二氧化碳
    // 传感器个数
    private int count;
    // 发送指令标号 3表示接收数据 16表示发送命令
    private String cmd;
    // 状态 默认1表示成功
    private int status;
    // 环境值 具体环境值 和传感器地址有关系
    private float data;
    // 采集时间
    private Timestamp gather_date;
    // 浅拷贝：拷贝的是对象的非类类型
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getDesId() {
        return desId;
    }

    public void setDesId(String desId) {
        this.desId = desId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getSersorAddress() {
        return sersorAddress;
    }

    public void setSersorAddress(String sersorAddress) {
        this.sersorAddress = sersorAddress;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }

    public Timestamp getGather_date() {
        return gather_date;
    }

    public void setGather_date(Timestamp gather_date) {
        this.gather_date = gather_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Environment{");
        sb.append("srcId='").append(srcId).append('\'');
        sb.append(", desId='").append(desId).append('\'');
        sb.append(", devId='").append(devId).append('\'');
        sb.append(", sersorAddress='").append(sersorAddress).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", count=").append(count);
        sb.append(", cmd='").append(cmd).append('\'');
        sb.append(", status=").append(status);
        sb.append(", data=").append(data);
        sb.append(", gather_date=").append(gather_date);
        sb.append('}');
        return sb.toString();
    }
}
