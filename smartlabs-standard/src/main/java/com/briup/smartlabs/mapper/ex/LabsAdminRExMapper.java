package com.briup.smartlabs.mapper.ex;

import java.util.Set;

public interface LabsAdminRExMapper {
	int deleteByUserId(Long id);
	
	int batchDeleteByUser(Set<Long> userIds);
}
