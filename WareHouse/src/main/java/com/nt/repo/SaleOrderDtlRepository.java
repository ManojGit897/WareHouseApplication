package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.nt.model.SaleOrderDtl;

public interface SaleOrderDtlRepository extends JpaRepository<SaleOrderDtl, Integer> {

	
	@Query("SELECT sdtl FROM SaleOrderDtl sdtl JOIN sdtl.so as SaleOrderDtl WHERE SaleOrderDtl.id=:soId")
		public List<SaleOrderDtl> getSaleOrderDtlsByPoId(Integer soId);
	
	@Query("SELECT count(sdtl) FROM SaleOrderDtl sdtl JOIN sdtl.so as SaleOrder WHERE SaleOrder.id=:soId")
	public Integer getSaleOrderDtlsCountByPoId(Integer soId);
}
