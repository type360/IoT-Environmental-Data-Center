package com.briup.smartlabs.bean.ex;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "设备类型参数模型",description = "设备类型参数详细描述")
public class DeviceType {
	@ApiModelProperty(value = "设备类型编号")
	private String id;
	@ApiModelProperty(value = "设备类型名称",required = true)
	private String name;
}
