package com.briup.smartlabs.service;

import com.briup.smartlabs.bean.ex.SysUserEx;
import com.briup.smartlabs.web.vm.UserParamVM;
import com.github.pagehelper.PageInfo;

import java.util.Set;

public interface SysUserService {
	
	public PageInfo<SysUserEx> findAllByPage(int pageNum,int pageSize);
	public boolean deleteById(Long id);
	public void saveOrUpdate(UserParamVM user);
	public int deleteByBatch(Set<Long> ids);
}
