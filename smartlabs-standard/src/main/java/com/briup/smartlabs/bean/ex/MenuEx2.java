package com.briup.smartlabs.bean.ex;

import com.briup.smartlabs.bean.SysMenu;
import com.briup.smartlabs.common.constant.SmartLabsConstants;
import lombok.Data;

@Data
public class MenuEx2 extends SysMenu {
	private String parentName;
	private boolean hasChildren;
	
	public boolean getHasChildren() {
		return super.getType().intValue()!=SmartLabsConstants.MenuType.BUTTON.getType()?true:false;
	}
}
