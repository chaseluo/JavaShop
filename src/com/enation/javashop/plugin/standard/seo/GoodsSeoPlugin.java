package com.enation.javashop.plugin.standard.seo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;


/**
 * 商品SEO优化插件
 * @author enation
 *
 */
public class GoodsSeoPlugin extends AbstractGoodsPlugin{
	
 
	
	@Override
	public void addTabs(){
		 this.addTags(4, "搜索优化");
	}
	
	public void registerPages(){	 
		//this.registerPage("goods_add.tabcontent", "/plugin/seo/seo.jsp?ajax=yes");
	}

 

	@Override
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {

	}
 
	
	@Override
	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		freeMarkerPaser.setPageName("seo");
		return freeMarkerPaser.proessPageContent();
	}

	@Override
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser = new FreeMarkerPaser(getClass());
		freeMarkerPaser.setPageName("seo");
		freeMarkerPaser.putData("goods", goods);
		return freeMarkerPaser.proessPageContent();
	}

	public void onAfterGoodsAdd(Goods goods, HttpServletRequest request) throws RuntimeException {
		
		
	}

	
	@Override
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)  {
		
		
	}

	@Override
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)  {
		
		
	}
	
	

	@Override
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)  {
		
		
	}

	@Override
	public String getAuthor() {
		
		return "kingapex";
	}

	@Override
	public String getId() {
		
		return "goodsseo";
	}

	@Override
	public String getName() {
		 
		return "商品SEO优化插件";
	}

	@Override
	public String getType() {
		
		return "";
	}

	@Override
	public String getVersion() {
		
		return "1.0";
	}

	@Override
	public void perform(Object... params) {
		
		
	}
	
}
