package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SmartDevice;

import java.util.List;

public interface SmartDeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmartDevice record);

    SmartDevice selectByPrimaryKey(String id);

    List<SmartDevice> selectAll();

    int updateByPrimaryKey(SmartDevice record);
}