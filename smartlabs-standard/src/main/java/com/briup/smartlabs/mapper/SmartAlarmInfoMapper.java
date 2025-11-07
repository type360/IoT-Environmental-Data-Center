package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SmartAlarmInfo;

import java.util.List;

public interface SmartAlarmInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmartAlarmInfo record);

    SmartAlarmInfo selectByPrimaryKey(String id);

    List<SmartAlarmInfo> selectAll();

    int updateByPrimaryKey(SmartAlarmInfo record);
}