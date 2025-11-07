package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SmartLabs;

import java.util.List;

public interface SmartLabsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmartLabs record);

    SmartLabs selectByPrimaryKey(String id);

    List<SmartLabs> selectAll();

    int updateByPrimaryKey(SmartLabs record);
}