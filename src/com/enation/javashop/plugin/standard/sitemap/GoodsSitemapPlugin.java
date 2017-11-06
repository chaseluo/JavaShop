package com.enation.javashop.plugin.standard.sitemap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.SiteMapUrl;
import com.enation.app.base.core.plugin.IRecreateMapEvent;
import com.enation.app.base.core.service.ISitemapManager;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;
import com.enation.javashop.core.plugin.goods.IGoodsDeleteEvent;
import com.enation.javashop.core.service.IGoodsManager;

/**
 * 商品对应Sitemap的插件
 * @author lzf<br/>
 * 2010-12-1 下午01:44:16<br/>
 * version 2.1.5
 */
public class GoodsSitemapPlugin extends AbstractGoodsPlugin implements IGoodsDeleteEvent, IRecreateMapEvent {
	
	private ISitemapManager sitemapManager;
	private IGoodsManager goodsManager;

	@Override
	public void addTabs() {

	}

	@Override
	public String onFillGoodsAddInput(HttpServletRequest request) {
		return null;
	}

	@Override
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {

	}

	@Override
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		return null;
	}

	@Override
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException {
		SiteMapUrl url = new SiteMapUrl();
		url.setLoc("/goods-"+ goods.get("goods_id").toString()+".html");
		url.setLastmod(System.currentTimeMillis());
		//url.setChangefreq("weekly");
		//url.setPriority("0.8");
		this.sitemapManager.addUrl(url);

	}
	
	@Override
	public void onRecreateMap() {
		List<Map> list = this.goodsManager.list();
		for(Map map:list){
			SiteMapUrl url = new SiteMapUrl();
			url.setLoc("/goods-" + map.get("goods_id") + ".html");
			url.setLastmod(System.currentTimeMillis());
			this.sitemapManager.addUrl(url);
		}
	}
	

	@Override
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request) {
		this.sitemapManager.editUrl("/goods-"+ goods.get("goods_id").toString()+".html", System.currentTimeMillis());
	}

	@Override
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request) {

	}
	

	@Override
	public void onGoodsDelete(Integer[] goodsid) {
		for(int i:goodsid){
			this.sitemapManager.delete("/goods-" + i +".html");
		}
		
	}

	@Override
	public String getAuthor() {
		return "lzf";
	}

	@Override
	public String getId() {
		return "goods-sitemap";
	}

	@Override
	public String getName() {
		return "商品数据sitemap记录插件";
	}

	@Override
	public String getType() {
		return "sitemap";
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

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

}
