package com.briup.smartlabs.bean.ex;

import com.briup.smartlabs.bean.SysUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
public class SysUserEx extends SysUser{
	Set<Long> roles;
}
