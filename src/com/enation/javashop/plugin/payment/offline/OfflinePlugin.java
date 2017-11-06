package com.enation.javashop.plugin.payment.offline;

import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.payment.AbstractPaymentPlugin;
import com.enation.javashop.core.plugin.payment.IPaymentEvent;

/**
 * 线下支付插件
 * @author kingapex
 * 2010-5-26上午10:06:13
 */
public class OfflinePlugin extends AbstractPaymentPlugin implements
		IPaymentEvent {


	
	@Override
	public String onCallBack() {
		return "";
	}

	
	@Override
	public String onPay(PayCfg payCfg, Order order) {
		
		return "";
	}

	
	@Override
	public String getAuthor() {
		return "kingapex";
	}

	
	@Override
	public String getId() {
		return "offline";
	}

	
	@Override
	public String getName() {
		return "线下支付";
	}

	
	@Override
	public String getType() {
		return "payment";
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


	@Override
	public String onReturn() {
		// TODO Auto-generated method stub
		return "";
	}
}
