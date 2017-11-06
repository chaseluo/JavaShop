package com.enation.javashop.plugin.promotion;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.plugin.promotion.IPromotionPlugin;
import com.enation.javashop.core.service.promotion.PromotionConditions;
import com.enation.javashop.core.service.promotion.PromotionType;

public class EnoughPriceReducePrice extends AutoRegisterPlugin implements
		IPromotionPlugin {

	
	@Override
	public void register() {

	}

	
	@Override
	public String[] getConditions() {
		 
		return new String[]{ PromotionConditions.ORDER ,PromotionConditions.MEMBERLV};
	}

	
	@Override
	public String getMethods() {
		 
		return "reducePrice";
	}

	
	@Override
	public String getAuthor() {
		return "kingapex";
	}

	
	@Override
	public String getId() {
		return "enoughPriceReducePrice";
	}

	
	@Override
	public String getName() {
		return "满就减———购物车中商品总金额大于指定金额,就可立减某金额";
	}

	
	@Override
	public String getType() {
		return PromotionType.PMTTYPE_ORDER;
	}

	
	@Override
	public String getVersion() {
		return "1.0";
	}

	
	@Override
	public void perform(Object... params) {

	}

}
