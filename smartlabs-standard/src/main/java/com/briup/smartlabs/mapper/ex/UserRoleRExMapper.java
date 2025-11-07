package com.briup.smartlabs.mapper.ex;

import java.util.Set;

public interface UserRoleRExMapper {
	int deleteByUser(Long id);
	//根据用户id查找用户角色。
	Set<Long> findRoleIdByUser(Long userId);
	
	int batchDeleteByUser(Set<Long> userIds);
}
