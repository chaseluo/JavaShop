package com.enation.javashop.core.plugin.field;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.plugin.goods.IGoodsAfterAddEvent;
import com.enation.javashop.core.plugin.goods.IGoodsAfterEditEvent;
import com.enation.javashop.core.plugin.goods.IGoodsBeforeAddEvent;
import com.enation.javashop.core.plugin.goods.IGoodsBeforeEditEvent;
import com.enation.javashop.core.service.IGoodsFieldManager;

/**
 * 商品自字义字段插件
 * 
 * @author kingapex
 * 
 */
public class GoodsCustomFieldPlugin extends AutoRegisterPlugin implements
		IGoodsBeforeAddEvent, IGoodsBeforeEditEvent, IGoodsAfterAddEvent,
		IGoodsAfterEditEvent {

	private IGoodsFieldManager goodsFieldManager;

	private GoodsFieldPluginBundle goodsFieldPluginBundle;

	public IGoodsFieldManager getGoodsFieldManager() {
		return goodsFieldManager;
	}

	public void setGoodsFieldManager(IGoodsFieldManager goodsFieldManager) {
		this.goodsFieldManager = goodsFieldManager;
	}

	/**
	 * 处理商品数据字段保存事件
	 * 
	 * @param goods
	 */
	private void save(Map goods) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		Integer typeid = Integer.valueOf(request.getParameter("goods.type_id"));
		if (typeid == null)
			throw new RuntimeException("保存商品时typeid不能为空");

		List<GoodsField> fieldList = goodsFieldManager.list(typeid);
		for (GoodsField goodsField : fieldList) {
			goodsFieldPluginBundle.onSave(goods, goodsField);
		}
	}
	
	private void afterSave(Map goods){
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		Integer typeid = Integer.valueOf(request.getParameter("goods.type_id"));
		if (typeid == null)
			throw new RuntimeException("保存商品时typeid不能为空");

		List<GoodsField> fieldList = goodsFieldManager.list(typeid);
		for (GoodsField goodsField : fieldList) {
			goodsFieldPluginBundle.onAfterSave(goods, goodsField);
		}
	}

	@Override
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest r) {
		this.save(goods);
	}

	@Override
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest r) {
		this.save(goods);
	}

	@Override
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException {
		 this.afterSave(goods);
	}

	@Override
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request) {
		 this.afterSave(goods);

	}

	public GoodsFieldPluginBundle getGoodsFieldPluginBundle() {
		return goodsFieldPluginBundle;
	}

	public void setGoodsFieldPluginBundle(
			GoodsFieldPluginBundle goodsFieldPluginBundle) {
		this.goodsFieldPluginBundle = goodsFieldPluginBundle;
	}

	@Override
	public void register() {
	}

	@Override
	public String getAuthor() {

		return "kingapex";
	}

	@Override
	public String getId() {

		return "customfield";
	}

	@Override
	public String getName() {

		return "商品自定义字段插件";
	}

	@Override
	public String getType() {

		return "goods";
	}

	@Override
	public String getVersion() {

		return "1.0";
	}

	@Override
	public void perform(Object... params) {
	}

}
