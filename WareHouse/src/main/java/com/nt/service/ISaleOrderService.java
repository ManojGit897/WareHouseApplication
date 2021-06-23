
package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.model.SaleOrder;
import com.nt.model.SaleOrderDtl;


public interface ISaleOrderService {

	Integer saveSaleOrder(SaleOrder saleOrder);
	
	SaleOrder getOneSaleOrder(Integer id);
	List<SaleOrder> getAllSaleOrders();
	
	boolean isSaleOrderCodeExist(String code);
	boolean isSaleOrderCodeExistForEdit(String code,Integer id);
	
	//for status read and update
		String getCurrentStatusOfSo(Integer soId);
		void updateSoStatus(Integer soId,String newStatus);
		Integer getSaleOrderDtlsCountBySoId(Integer soId);
	
	///------------ for Screen#2----------------
	
	Integer saveSaleOrderDtl(SaleOrderDtl sdtl);
	
	List<SaleOrderDtl> getSaleOrderDtlsBySoId(Integer id);
	void deleteSaleOrderDtl(Integer dtlId);	
	Optional<SaleOrderDtl> getSaleOrderDtlByPartIdAndSoId(Integer partId, Integer soId);
	Integer updateSaleOrderDtlQtyByDtlId(Integer newQty,Integer dtlId);
}                                                                               
