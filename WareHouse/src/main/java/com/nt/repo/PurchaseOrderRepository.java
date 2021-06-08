package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer>
{

	// For Register
	@Query("SELECT count(orderCode) FROM PurchaseOrder WHERE orderCode=:orderCode")
	Integer getOrderCodeCount(String orderCode);
	
	// For Edit
	@Query("SELECT count(orderCode) FROM PurchaseOrder WHERE orderCode=:orderCode AND id!=:id")
	Integer getOrderCodeCountForEdit(String orderCode,Integer id);
	
	@Query("SELECT status FROM PurchaseOrder WHERE id=:poId")
	String getCurrentStatusOfPo(Integer poId);

	@Modifying
	@Query("UPDATE PurchaseOrder SET status=:newStatus WHERE id=:poId")
	void updatePoStatus(Integer poId,String newStatus);
	
	
}
