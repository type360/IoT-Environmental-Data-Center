package com.briup.smartlabs.bean;

import java.util.Date;

public class SmartRentalInfos {
    private String id;

    private String labsId;

    private Date startDate;

    private Date endDate;

    private Byte approvalStatus;

    private Long approvalUser;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Byte getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Byte approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Long getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(Long approvalUser) {
        this.approvalUser = approvalUser;
    }
}