package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SmartLabsAdminR;

import java.util.List;

public interface SmartLabsAdminRMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmartLabsAdminR record);

    SmartLabsAdminR selectByPrimaryKey(String id);

    List<SmartLabsAdminR> selectAll();

    int updateByPrimaryKey(SmartLabsAdminR record);
}