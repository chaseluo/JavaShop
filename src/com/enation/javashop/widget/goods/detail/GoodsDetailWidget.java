package com.enation.javashop.widget.goods.detail;

import java.util.Map;
import com.enation.app.base.widget.header.HeaderConstants;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.ObjectNotFoundException;

/**
 * 商品详细挂件</br>
 * @author kingapex
 */
public class GoodsDetailWidget extends AbstractWidget {
	 

	@Override
	protected void  display(Map<String, String> params) {
		
		try{
		 Map goodsMap=  (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		 
		 if(goodsMap.get("page_title")!=null &&!goodsMap.get("page_title").equals("") )
			this.putData(HeaderConstants.title, goodsMap.get("page_title"));
		 else
			 this.putData(HeaderConstants.title, goodsMap.get("name") );
		 
		 if(goodsMap.get("meta_keywords")!=null &&!goodsMap.get("meta_keywords").equals("")  )
			this.putData(HeaderConstants.keywords,goodsMap.get("meta_keywords"));
		 
		 if(goodsMap.get("meta_description")!=null &&!goodsMap.get("meta_description").equals("") )
			this.putData(HeaderConstants.description,goodsMap.get("meta_description"));
		  
		}catch(ObjectNotFoundException e){
			
			this.setPageName("notfound");
		}
	}
	
	@Override
	protected void config(Map<String, String> params) {
		this.showHtml=false;
	}


	
	public static void main(String[] args){
	}

	
}
