package com.briup.smartlabs.bean;

import java.util.Date;

//验证码类
public class SysKaptcha {
    private String formId;

    private String kaptcha;

    private Date expire;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha == null ? null : kaptcha.trim();
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }
}