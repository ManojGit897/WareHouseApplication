
package com.nt.service;

import java.util.List;

import com.nt.model.SaleOrder;
import com.nt.model.SaleOrderDtl;


public interface ISaleOrderService {

	Integer saveSaleOrder(SaleOrder saleOrder);
	
	SaleOrder getOneSaleOrder(Integer id);
	List<SaleOrder> getAllSaleOrders();
	
	boolean isSaleOrderCodeExist(String code);
	boolean isSaleOrderCodeExistForEdit(String code,Integer id);
	
	//for status read and update
		String getCurrentStatusOfSo(Integer poId);
		void updateSoStatus(Integer poId,String newStatus);
		Integer getSaleOrderDtlsCountByPoId(Integer poId);
	
	///------------ for Screen#2----------------
	
	Integer saveSaleOrderDtl(SaleOrderDtl sdtl);
	
	List<SaleOrderDtl> getSaleOrderDtlsByPoId(Integer id);
	void deleteSaleOrderDtl(Integer dtlId);
	
}
