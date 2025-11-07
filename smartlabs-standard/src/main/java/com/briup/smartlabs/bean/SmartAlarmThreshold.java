package com.briup.smartlabs.bean;

public class SmartAlarmThreshold {
    private String id;

    private String labsId;

    private Double pm25Limit;

    private Double co2Limit;

    private Double ch2oLimit;

    private Double tvocLimit;

    private Double temperatureLimit;

    private Double humidityLimit;

    private Double airLimit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLabsId() {
        return labsId;
    }

    public void setLabsId(String labsId) {
        this.labsId = labsId == null ? null : labsId.trim();
    }

    public Double getPm25Limit() {
        return pm25Limit;
    }

    public void setPm25Limit(Double pm25Limit) {
        this.pm25Limit = pm25Limit;
    }

    public Double getCo2Limit() {
        return co2Limit;
    }

    public void setCo2Limit(Double co2Limit) {
        this.co2Limit = co2Limit;
    }

    public Double getCh2oLimit() {
        return ch2oLimit;
    }

    public void setCh2oLimit(Double ch2oLimit) {
        this.ch2oLimit = ch2oLimit;
    }

    public Double getTvocLimit() {
        return tvocLimit;
    }

    public void setTvocLimit(Double tvocLimit) {
        this.tvocLimit = tvocLimit;
    }

    public Double getTemperatureLimit() {
        return temperatureLimit;
    }

    public void setTemperatureLimit(Double temperatureLimit) {
        this.temperatureLimit = temperatureLimit;
    }

    public Double getHumidityLimit() {
        return humidityLimit;
    }

    public void setHumidityLimit(Double humidityLimit) {
        this.humidityLimit = humidityLimit;
    }

    public Double getAirLimit() {
        return airLimit;
    }

    public void setAirLimit(Double airLimit) {
        this.airLimit = airLimit;
    }
}