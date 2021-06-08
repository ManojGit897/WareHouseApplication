package com.nt.service;

import java.util.List;

import com.nt.model.PurchaseDtl;
import com.nt.model.PurchaseOrder;


public interface IPurchaseOrderService {

	Integer savePurchaseOrder(PurchaseOrder po);
	
	PurchaseOrder getOnePurchaseOrder(Integer id);
	List<PurchaseOrder> getAllPurchaseOrders();
	boolean isPurchaseOrderCodeExist(String code);
	boolean isPurchaseOrderCodeExistForEdit(String code,Integer id);
	
	//for status read and update
		String getCurrentStatusOfPo(Integer poId);
		void updatePoStatus(Integer poId,String newStatus);
		Integer getPurchaseDtlsCountByPoId(Integer poId);
	
	
	///------------ for Screen#2----------------
	
	Integer savePurchaseDtl(PurchaseDtl pdt1);
	List<PurchaseDtl> getPurchaseDt1sByPoId(Integer id);
	void deletePurchaseDtl(Integer dtlId);
	
	
}
