package com.enation.javashop.widget.goods.detail;

import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 商品详细信息挂件
 * @author kingapex
 *
 */
public class GoodsIntroWidget extends AbstractWidget {
 
	
	@Override
	protected void config(Map<String, String> params) {

	}

	
	@Override
	protected void display(Map<String, String> params) {
		
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		
		if(goods==null) throw new RuntimeException("参数显示挂件必须和商品详细显示挂件同时存在");
		String intro =(String)goods.get("intro");
		intro =intro==null?"":intro;
		this.putData("intro", intro);
		this.setPageName("intro");
	}

 
	

}
