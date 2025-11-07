package com.briup.smartlabs.mapper.ex;

import com.briup.smartlabs.bean.SmartLabs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmartLabsExMapper {
	//按照实验室标识查找
	SmartLabs findByLabsNum(@Param("labsNum")String labsNum,
			@Param("labsId")String labsId);

	//按照实验室网络地址查找
	SmartLabs findByNetAdres(@Param("netAdres")String netAdres,
			@Param("labsId")String labsId);
	
	//判断指定实验室id是否在数据库中存在
	List<String> existByIds(List<String> ids);

	List<SmartLabs> selectByCondition(String key);

	int deleteByBatch(List<String> existId);
}







