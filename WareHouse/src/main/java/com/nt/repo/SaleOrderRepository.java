package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.SaleOrder;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer>
{

	// For Register
	@Query("SELECT count(orderCode) FROM SaleOrder WHERE orderCode=:orderCode")
	Integer getOrderCodeCount(String orderCode);
	
	// For Edit
	@Query("SELECT count(orderCode) FROM SaleOrder WHERE orderCode=:orderCode AND id!=:id")
	Integer getOrderCodeCountForEdit(String orderCode,Integer id);

	
	@Query("SELECT status FROM SaleOrder WHERE id=:soId")
	String getCurrentStatusOfSo(Integer soId);

	@Modifying
	@Query("UPDATE SaleOrder SET status=:newStatus WHERE id=:soId")
	void updateSoStatus(Integer soId,String newStatus);
	
	

	
}

