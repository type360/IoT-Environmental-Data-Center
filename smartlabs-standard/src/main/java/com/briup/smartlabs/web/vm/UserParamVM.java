package com.briup.smartlabs.web.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ApiModel("保存或更新用户信息的参数模型")
public class UserParamVM {
	@ApiModelProperty(value = "用户编号，如果有值代表更新，如果没有代表保存")
	private Long userId;
	@ApiModelProperty(value = "用户名，登录名",required = true)
	private String username;
	@ApiModelProperty(value = "真实姓名",required = true)
	private String realname;
	@ApiModelProperty(value = "邮箱地址")
	private String email;
	@ApiModelProperty(value = "电话")
	private String mobile;
	@ApiModelProperty(value = "用户角色")
	private long[] roles;
}
