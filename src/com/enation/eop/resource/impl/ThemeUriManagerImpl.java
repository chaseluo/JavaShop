package com.enation.eop.resource.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.eop.sdk.database.BaseSupport;

public class ThemeUriManagerImpl extends BaseSupport<ThemeUri> implements
		IThemeUriManager {

	@Override
	public void clean() {
		this.baseDaoSupport.execute("truncate table themeuri");
	}
	@Override
	public ThemeUri get(Integer id) {
		
		return this.baseDaoSupport.queryForObject("select * from themeuri where id=?", ThemeUri.class, id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void edit(List<ThemeUri> uriList) {
		for(ThemeUri uri: uriList){
			this.baseDaoSupport.update("themeuri", uri, "id="+uri.getId());
		}
	}
	 
	@Override
	public List<ThemeUri> list( ) {
		String sql ="select * from themeuri";
		return this.baseDaoSupport.queryForList(sql, ThemeUri.class);
	}

	
	@Override
	public ThemeUri getPath( String uri) {
		List<ThemeUri> list = list();
		 
		for(ThemeUri themeUri:list){
			if(themeUri.getUri().equals(uri)){
				return themeUri;
			}
		}
		return null;
	}

	
	@Override
	public void add(ThemeUri uri) {
		this.baseDaoSupport.insert("themeuri", uri);
	}

	
	@Override
	public void delete(int id){
		this.baseDaoSupport.execute("delete from themeuri where id=? ", id);
	}

	@Override
	public void edit(ThemeUri themeUri) {
		this.baseDaoSupport.update("themeuri", themeUri, "id="+themeUri.getId());
	}


}
