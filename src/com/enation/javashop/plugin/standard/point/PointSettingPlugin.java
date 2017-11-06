package com.enation.javashop.plugin.standard.point;

import com.enation.app.base.core.plugin.IOnSettingInputShow;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.plugin.page.JspPageTabs;

public class PointSettingPlugin extends AutoRegisterPlugin implements
		IOnSettingInputShow {

	
	@Override
	public void register() {
		JspPageTabs.addTab("setting",3, "积分设置");
	}

	
	@Override
	public String getSettingGroupName() {
	
		return "point";
	}

	
	@Override
	public String onShow() {
	
		return "setting";
	}

	
	@Override
	public String getAuthor() {
	
		return "kingapex";
	}

	
	@Override
	public String getId() {
	
		return "point_setting";
	}

	
	@Override
	public String getName() {
	
		return "积分设置";
	}

	
	@Override
	public String getType() {
	
		return "setting";
	}

	
	@Override
	public String getVersion() {
	
		return "1.0";
	}

	
	@Override
	public void perform(Object... params) {
		
	}

}
