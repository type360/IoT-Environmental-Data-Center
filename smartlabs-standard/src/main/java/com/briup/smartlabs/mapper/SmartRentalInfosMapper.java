package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SmartRentalInfos;

import java.util.List;

public interface SmartRentalInfosMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmartRentalInfos record);

    SmartRentalInfos selectByPrimaryKey(String id);

    List<SmartRentalInfos> selectAll();

    int updateByPrimaryKey(SmartRentalInfos record);
}