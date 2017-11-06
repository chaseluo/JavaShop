package com.enation.cms.plugin;

import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.SiteMapUrl;
import com.enation.app.base.core.plugin.IRecreateMapEvent;
import com.enation.app.base.core.service.ISitemapManager;
import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.plugin.IDataDeleteEvent;
import com.enation.cms.core.plugin.IDataSaveEvent;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.framework.plugin.AutoRegisterPlugin;

/**
 * CMS的sitemap生成插件
 * @author kingapex
 *
 */
public class CmsSitemapPlugin extends AutoRegisterPlugin implements
		IDataSaveEvent, IDataDeleteEvent, IRecreateMapEvent {
	
	private ISitemapManager sitemapManager;
	private IDataCatManager dataCatManager;
	private IDataManager dataManager;

	@Override
	public void register() {

	}

	@Override
	public void onSave(Map data) {
		DataCat cat = this.dataCatManager.get(Integer.valueOf(data.get("cat_id").toString()));
		if(cat.getTositemap()==1){ //要加入到Sitemap
			SiteMapUrl url = new SiteMapUrl();
			url.setLoc("/" + cat.getDetail_url() + "-" + cat.getCat_id() + "-" + data.get("id") + ".html");
			url.setLastmod(System.currentTimeMillis());
			this.sitemapManager.addUrl(url);
		}
	}
	

	@Override
	public void onDelete(Integer catid, Integer articleid) {
		DataCat cat = this.dataCatManager.get(catid);
		this.sitemapManager.delete( "/" + cat.getDetail_url() + "-" + cat.getCat_id() + "-" + articleid + ".html");
		
	}
	
	@Override
	public void onRecreateMap() {
		List<DataCat> listCat = this.dataCatManager.listAllChildren(0);
		for(DataCat cat:listCat){
			if(cat.getTositemap()==1){
				List<Map> list = this.dataManager.list(cat.getCat_id());
				for(Map map:list){
					SiteMapUrl url = new SiteMapUrl();
					url.setLoc("/" + cat.getDetail_url() + "-" + cat.getCat_id() + "-" + map.get("id") + ".html");
					url.setLastmod(System.currentTimeMillis());
					this.sitemapManager.addUrl(url);
				}
			}
		}
	}

	@Override
	public String getAuthor() {
		return "lzf";
	}

	@Override
	public String getId() {
		return "cms_sitemap";
	}

	@Override
	public String getName() {
		return "cms数据sitemap插件";
	}

	@Override
	public String getType() {
		return "cmssitemap";
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

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}

	public IDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IDataManager dataManager) {
		this.dataManager = dataManager;
	}

}
