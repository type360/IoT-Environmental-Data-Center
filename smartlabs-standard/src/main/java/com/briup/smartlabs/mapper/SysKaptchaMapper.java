package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SysKaptcha;

import java.util.List;

public interface SysKaptchaMapper {
    int deleteByPrimaryKey(String formId);

    int insert(SysKaptcha record);

    SysKaptcha selectByPrimaryKey(String formId);

    List<SysKaptcha> selectAll();

    int updateByPrimaryKey(SysKaptcha record);
}