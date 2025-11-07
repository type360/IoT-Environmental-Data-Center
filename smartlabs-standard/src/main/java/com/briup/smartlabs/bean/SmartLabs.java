package com.briup.smartlabs.bean;

import lombok.ToString;

@ToString
public class SmartLabs {
    private String id;

    private String labsName;

    private String infos;

    private String netAdres;

    private Integer port;

    private String labsNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLabsName() {
        return labsName;
    }

    public void setLabsName(String labsName) {
        this.labsName = labsName == null ? null : labsName.trim();
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos == null ? null : infos.trim();
    }

    public String getNetAdres() {
        return netAdres;
    }

    public void setNetAdres(String netAdres) {
        this.netAdres = netAdres == null ? null : netAdres.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getLabsNum() {
        return labsNum;
    }

    public void setLabsNum(String labsNum) {
        this.labsNum = labsNum == null ? null : labsNum.trim();
    }
}