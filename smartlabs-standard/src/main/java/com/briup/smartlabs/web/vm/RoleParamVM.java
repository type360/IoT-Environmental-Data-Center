package com.briup.smartlabs.web.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(value = "角色模型数据")
@Data
public class RoleParamVM {
	@ApiModelProperty(value = "角色编号")
	private Long roleId;

	private String roleName;
	private String remark;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	private List<Long> menus;
}
