package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SmartDeviceType;

import java.util.List;

public interface SmartDeviceTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmartDeviceType record);

    SmartDeviceType selectByPrimaryKey(String id);

    List<SmartDeviceType> selectAll();

    int updateByPrimaryKey(SmartDeviceType record);
}