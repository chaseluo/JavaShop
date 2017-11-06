package com.enation.javashop.plugin.search;

import java.util.ArrayList;
import java.util.List;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Brand;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.plugin.search.IGoodsSearchFilter;
import com.enation.javashop.core.plugin.search.SearchSelector;
import com.enation.javashop.core.service.IBrandManager;
import com.enation.javashop.core.utils.UrlUtils;

/**
 * 品牌过滤器
 * @author kingapex
 *
 */
public class BrandSearchFilter extends AutoRegisterPlugin implements
		IGoodsSearchFilter {

	private IBrandManager brandManager;
	@Override
	public void filter(StringBuffer sql,Cat cat, String urlFragment) {
		if (!StringUtil.isEmpty(urlFragment) && !"0".equals(urlFragment)) {
			sql.append(" and g.brand_id=" + urlFragment);
		}
	}

	@Override
	public String getFilterId() {

		return "brand";
	}

	
	@Override
	public List<SearchSelector> createSelectorList(Cat cat,String url, String urlFragment) {

		List<SearchSelector> selectorList = new ArrayList<SearchSelector>();

		//此分类的品牌列表
		List<Brand> brandList = null;
		
		if(cat!=null){
			brandList  = this.brandManager.listByTypeId(cat.getType_id());
		}else{
			brandList  = this.brandManager.list();
		}
		

		/**
		 * ------------------------ 生成'全部'品牌的选择器 -----------------------
		 */
		SearchSelector allselector = new SearchSelector();
		allselector.setName("全部");
		allselector.setUrl(url+".html");
		if (StringUtil.isEmpty(urlFragment) || "0".equals(urlFragment)) {
			allselector.setSelected(true);
		} else {
			allselector.setSelected(false);
		}
		selectorList.add(allselector);

		for (Brand brand : brandList) {
			SearchSelector selector = new SearchSelector();
			selector.setName(brand.getName());
			selector.setUrl(UrlUtils.addUrl(url,"brand",brand.getBrand_id().toString()));
			if (brand.getBrand_id().toString().equals(urlFragment)) {
				selector.setSelected(true);
			} else {
				selector.setSelected(false);
			}
			selectorList.add(selector);
		}

		return selectorList;
	}

	@Override
	public String getAuthor() {

		return "kingapex";
	}

	@Override
	public String getId() {

		return "brandSearchFilter";
	}

	@Override
	public String getName() {

		return "品牌搜索过虑器";
	}

	@Override
	public String getType() {

		return "goodssearch";
	}

	@Override
	public String getVersion() {

		return "1.0";
	}

	@Override
	public void perform(Object... params) {

	}

	@Override
	public void register() {

	}

	public IBrandManager getBrandManager() {
		return brandManager;
	}

	public void setBrandManager(IBrandManager brandManager) {
		this.brandManager = brandManager;
	}


}
