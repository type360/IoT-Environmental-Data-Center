package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.SysUser;
import com.briup.smartlabs.bean.SysUserRole;
import com.briup.smartlabs.bean.ex.SysUserEx;
import com.briup.smartlabs.common.constant.SmartLabsConstants;
import com.briup.smartlabs.common.exceptions.DataValidateException;
import com.briup.smartlabs.common.utils.AuthenticationHolder;
import com.briup.smartlabs.mapper.SysUserMapper;
import com.briup.smartlabs.mapper.SysUserRoleMapper;
import com.briup.smartlabs.mapper.ex.LabsAdminRExMapper;
import com.briup.smartlabs.mapper.ex.SysUserExMapper;
import com.briup.smartlabs.mapper.ex.UserRoleRExMapper;
import com.briup.smartlabs.service.SysUserService;
import com.briup.smartlabs.web.vm.UserParamVM;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysUserExMapper userExMapper;
	@Autowired
	private LabsAdminRExMapper labsAdminRExMapper;
	@Autowired
	private UserRoleRExMapper userRoleRExMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	
	@Transactional(readOnly = true)
	@Override
	public PageInfo<SysUserEx> findAllByPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Page page = PageHelper.startPage(pageNum, pageSize, true);
		List<SysUser> list = userMapper.selectAll();
		System.out.println("原始数据list:"+list);
		System.out.println("startPage返回的page："+page);
		List<SysUserEx> vms = new ArrayList<>(list.size());
		for(SysUser user:list) {
			//按照用户，查找用户角色。
			Set<Long> roles = userRoleRExMapper.findRoleIdByUser(user.getUserId());
			SysUserEx ex = new SysUserEx();
			BeanUtils.copyProperties(user, ex);
			ex.setPassword(""); //不返回密码
			ex.setRoles(roles); //设置角色信息
			vms.add(ex);
		}
		page.clear();
		page.addAll(vms);
		System.out.println(page);
		return new PageInfo<>(page);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	@Override
	public boolean deleteById(Long id) {
		// 如果要删除超级管理员，id==1|name==Admin  直接抛出异常
		if(id.longValue() == SmartLabsConstants.SUPER_ADMIN_ID.longValue()) {
			throw new DataValidateException("不能删除超级管理员！");
		}

		SysUser user = userMapper.selectByPrimaryKey(id);
		if(user==null) {
			throw new DataValidateException("该用户不存在！");
		}

		// 如果是实验室管理员，解除和实验室的关联关系。
		labsAdminRExMapper.deleteByUserId(id);
		// 每一个用户，可以拥有多个角色。 删除用户和角色之间的关系。
		userRoleRExMapper.deleteByUser(id);
		
		userMapper.deleteByPrimaryKey(id); //删除用户
		return true;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveOrUpdate(UserParamVM user) {
		System.out.println("userVm:"+user);
		// TODO Auto-generated method stub
		//除了主键外，是否有唯一值限定。
		/*
		 * 1. 判断参数中的用户名（username）是否在数据库中存在。
		 * 		1.1 在数据库中不存在。	向下继续执行。
		 * 		1.2 判断查找到的用户id和参数中的用户id是否相同。
		 * 				如果相同，继续更新行为，如果不同，代表操作失败。
		 * 2. 如果参数中包含id，更新
		 * 		delete用户角色。
		 * 3. 如果参数中不包含id，插入。
		 * 		设置默认密码，默认状态，创建时间，创建人。
		 * 4. 判断用户参数中是否包含角色。插入用户角色
		 * 		
		 */
		SysUser u = userExMapper.loadByName(user.getUsername());
		System.out.println("数据库中检索等到的结果u:"+u);
		if(u!=null && !u.getUserId().equals(user.getUserId())) {
			//提示出错，用户名已经存在。
			throw new DataValidateException("用户名已经存在！");
		}
		Long userId = null; //保存当前更新或者插入的用户的id。方便插入角色是使用
		System.out.println("页面传递的请求参数：");
		if(user.getUserId()==null) {
			SysUser record = new SysUser();
			BeanUtils.copyProperties(user, record);
			System.out.println("record:"+record);
			record.setPassword("123123");
			record.setCreateUserId(AuthenticationHolder.getCurrentUserId());
			record.setStatus((byte)1);
			record.setCreateTime(new Date());
			userMapper.insert(record);
			userId = record.getUserId();
		}else {
			userId = user.getUserId(); //页面传递的用户的id。
			if(u==null) {
				u = userMapper.selectByPrimaryKey(userId);
				if(u==null) {
					throw new DataValidateException("用户不存在！");
				}
			}
			BeanUtils.copyProperties(user, u,"userId");
			System.out.println("拷贝之后的结果："+u);
			userMapper.updateByPrimaryKey(u);
			//按照用户id删除用户拥有的角色。
			userRoleRExMapper.deleteByUser(user.getUserId());
		}
		long[] roles = user.getRoles();
		if(ObjectUtils.isNotEmpty(roles)) {
			for(long roleId:roles) {
				SysUserRole ur = new SysUserRole();
				ur.setRoleId(roleId);
				ur.setUserId(userId);
				userRoleMapper.insert(ur);
			}
		}
	}
	
	@Transactional
	@Override
	public int deleteByBatch(Set<Long> ids) {
		//超级管理员不能删除。
		ids.remove(SmartLabsConstants.SUPER_ADMIN_ID.longValue());
		userRoleRExMapper.batchDeleteByUser(ids);
		labsAdminRExMapper.batchDeleteByUser(ids);
		return userExMapper.batchDeleteById(ids);
	}

}



