package com.briup.smartlabs.mapper.ex;

import com.briup.smartlabs.bean.SysRole;

import java.util.List;

public interface SysRoleExMapper {
	List<SysRole> findByCondition(String key);
}
