package com.enation.app.base.widget.im;

import com.enation.app.base.core.plugin.IOnSettingInputShow;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.plugin.page.JspPageTabs;

/**
 * 在线客服务设置插件
 * @author kingapex
 * 2010-9-15下午02:47:58
 */
public class IMSettingPlugin extends AutoRegisterPlugin  implements IOnSettingInputShow {

	@Override
	public String getSettingGroupName() {
		
		return "im";
	}

	@Override
	public String onShow() {
		
		return "setting";
	}

	
	@Override
	public void register() {
		
		 JspPageTabs.addTab("setting",1, "在线客服");
	}

	
	@Override
	public String getAuthor() {
		
		return "kingapex";
	}

	
	@Override
	public String getId() {
		
		return "imSettingPlugin";
	}

	
	@Override
	public String getName() {
		
		return "imSettingPlugin";
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
