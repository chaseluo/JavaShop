package com.enation.javashop.plugin.standard.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.javashop.core.model.Tag;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;
import com.enation.javashop.core.service.ITagManager;

/**
 * 商品标签插件
 * @author kingapex
 * 2010-1-18下午02:56:43
 */
public class GoodsTagPlugin extends AbstractGoodsPlugin {
	private ITagManager tagManager;
	
	
	@Override
	public void addTabs() {
 
	}

	
 

	
	
	/*为添加商品和修改商品页面填充必要的数据*/
	
	
	@Override
	public String onFillGoodsAddInput(HttpServletRequest request) {
		List<Tag> taglist  = this.tagManager.list();
		FreeMarkerPaser freeMarkerPaser = new FreeMarkerPaser(getClass());
		freeMarkerPaser.setPageName("tag");
		freeMarkerPaser.putData("tagList", taglist);
		return freeMarkerPaser.proessPageContent();
	}
	
	
	
	@Override
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		List<Tag> taglist  = this.tagManager.list();
	 
		Integer goods_id =  Integer.valueOf(goods.get("goods_id").toString());
		List<Integer> tagIds=this.tagManager.list(goods_id);
	 
		
		FreeMarkerPaser freeMarkerPaser = new FreeMarkerPaser(getClass());
		freeMarkerPaser.setPageName("tag");
		freeMarkerPaser.putData("tagList", taglist);	
		freeMarkerPaser.putData("tagRelList", tagIds);
		return freeMarkerPaser.proessPageContent();
	}

	
	
	
	/*在保存添加和保存更新的时候，将tagid的数组和goodsid对应起来保存在库里*/
	
	
	
	@Override
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException {
		this.save(goods, request);

	}

	
	@Override
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)
			 {
		this.save(goods, request);

	}

	private void save(Map goods, HttpServletRequest request){
		Integer goods_id =  Integer.valueOf(goods.get("goods_id").toString());
		
		String[] tagstr=  request.getParameterValues("tag_id");
		Integer[] tagids = null;
		if(tagstr!=null){
			tagids = new Integer[tagstr.length];
			for(int i=0;i<tagstr.length;i++){
				tagids[i]=	Integer.valueOf(tagstr[i]) ;
			}
		}
		this.tagManager.saveRels(goods_id, tagids);
	}
	
	
	
	
	
	
	

	
	@Override
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)
 {
		 

	}

	
	@Override
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request)
			 {
		 
		 
	}

	
	
	@Override
	public String getAuthor() {
		return "kingapex";
	}

	
	@Override
	public String getId() {
		return "goodstag";
	}

	
	@Override
	public String getName() {
		return "商品标签";
	}

	
	@Override
	public String getType() {
		return null;
	}

	
	@Override
	public String getVersion() {
		return "1.0";
	}

	
	@Override
	public void perform(Object... params) {

	}


	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}

}
