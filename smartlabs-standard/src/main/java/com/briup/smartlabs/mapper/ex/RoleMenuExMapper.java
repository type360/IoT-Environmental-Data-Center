package com.briup.smartlabs.mapper.ex;

import java.util.List;

public interface RoleMenuExMapper {
	int deleteByRole(Long roleId);

	List<Long> findMenusByRole(Long roleId);
}
