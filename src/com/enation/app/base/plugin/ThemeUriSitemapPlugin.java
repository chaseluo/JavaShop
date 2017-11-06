package com.enation.app.base.plugin;

import java.util.List;

import com.enation.app.base.core.model.SiteMapUrl;
import com.enation.app.base.core.plugin.IRecreateMapEvent;
import com.enation.app.base.core.service.ISitemapManager;
import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.framework.plugin.AutoRegisterPlugin;

public class ThemeUriSitemapPlugin extends AutoRegisterPlugin implements
		IRecreateMapEvent {
	
	private ISitemapManager sitemapManager;
	private IThemeUriManager themeUriManager;

	@Override
	public void register() {

	}

	@Override
	public void onRecreateMap() {
		List<ThemeUri> list = this.themeUriManager.list();
		for(ThemeUri uri:list){
			if(uri.getSitemaptype()==1){
				SiteMapUrl url = new SiteMapUrl();
				url.setLoc(uri.getUri());
				url.setLastmod(System.currentTimeMillis());
				this.sitemapManager.addUrl(url);
			}
		}

	}

	@Override
	public String getAuthor() {
		return "lzf";
	}

	@Override
	public String getId() {
		return "themeUriSitemap";
	}

	@Override
	public String getName() {
		return "地址转向定义重建站点地图";
	}

	@Override
	public String getType() {
		return "themeUriSitemap";
	}

	@Override
	public String getVersion() {
		return "v2.1.5";
	}

	@Override
	public void perform(Object... params) {

	}

	public ISitemapManager getSitemapManager() {
		return sitemapManager;
	}

	public void setSitemapManager(ISitemapManager sitemapManager) {
		this.sitemapManager = sitemapManager;
	}

	public IThemeUriManager getThemeUriManager() {
		return themeUriManager;
	}

	public void setThemeUriManager(IThemeUriManager themeUriManager) {
		this.themeUriManager = themeUriManager;
	}

}
