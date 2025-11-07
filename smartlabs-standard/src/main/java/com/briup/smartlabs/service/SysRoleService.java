package com.briup.smartlabs.service;

import com.briup.smartlabs.bean.SysRole;
import com.briup.smartlabs.web.vm.RoleParamVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysRoleService {
	
	public List<SysRole> findAll();

	public void saveOrUpdateRole(RoleParamVM vm);

	public PageInfo<RoleParamVM> findByCondition(int pageNum, int pageSize, String key);
}
