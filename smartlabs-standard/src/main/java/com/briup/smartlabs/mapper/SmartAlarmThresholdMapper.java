package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SmartAlarmThreshold;

import java.util.List;

public interface SmartAlarmThresholdMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmartAlarmThreshold record);

    SmartAlarmThreshold selectByPrimaryKey(String id);

    List<SmartAlarmThreshold> selectAll();

    int updateByPrimaryKey(SmartAlarmThreshold record);
}