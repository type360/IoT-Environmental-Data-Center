package com.briup.smartlabs.bean;

import java.util.Date;

public class SmartAlarmInfo {
    private String id;

    private String alarmType;

    private String alarmCause;

    private Date alarmDate;

    private Byte alarmLevel;

    private String alarmStatus;

    private String deviceAdres;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }

    public String getAlarmCause() {
        return alarmCause;
    }

    public void setAlarmCause(String alarmCause) {
        this.alarmCause = alarmCause == null ? null : alarmCause.trim();
    }

    public Date getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
    }

    public Byte getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Byte alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus == null ? null : alarmStatus.trim();
    }

    public String getDeviceAdres() {
        return deviceAdres;
    }

    public void setDeviceAdres(String deviceAdres) {
        this.deviceAdres = deviceAdres == null ? null : deviceAdres.trim();
    }
}