package com.briup.smartlabs.bean.ex;
/**
 *  Env数据统计结果映射
 * @author guomiao
 */

import lombok.Data;

import java.util.List;

@Data
public class EnvStatisticData {
	private String gatherDate;
	private List<EnvTypeDetail> list;
}
