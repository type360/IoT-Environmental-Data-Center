package com.briup.smartlabs.mapper;

import com.briup.smartlabs.bean.SmartEnvDetail;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Date;
import java.util.List;

public interface SmartEnvDetailMapper {
    List<SmartEnvDetail> selectAll();

    public List<SmartEnvDetail> findAllByCondition(
    		@Param("start")Date start,
    		@Param("end")Date end,
    		@Param("type")String type);
}