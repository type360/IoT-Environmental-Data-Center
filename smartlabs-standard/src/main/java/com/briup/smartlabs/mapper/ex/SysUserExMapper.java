package com.briup.smartlabs.mapper.ex;

import com.briup.smartlabs.bean.SysUser;
import com.briup.smartlabs.bean.ex.SysUserEx;

import java.util.List;
import java.util.Set;

public interface SysUserExMapper {
	SysUser loadByName(String username);
	
	List<SysUserEx> findAllWithRole();
	
	int batchDeleteById(Set<Long> ids);
}
