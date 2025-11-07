package com.briup.smartlabs.mapper.ex;

import com.briup.smartlabs.bean.ex.EnvStatisticData;

import java.util.List;

public interface EnvStatisticMapper {
	
	public List<EnvStatisticData> byMonth(String date);

	public List<EnvStatisticData> byYear(String year);
	
}
