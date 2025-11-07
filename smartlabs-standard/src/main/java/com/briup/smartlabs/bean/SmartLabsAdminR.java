package com.briup.smartlabs.bean;

public class SmartLabsAdminR {
    private String id;

    private String labsId;

    private String adminId;

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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }
}