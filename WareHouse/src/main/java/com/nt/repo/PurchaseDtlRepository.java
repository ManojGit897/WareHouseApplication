package com.nt.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nt.model.PurchaseDtl;

public interface PurchaseDtlRepository extends JpaRepository<PurchaseDtl, Integer>{

	
	@Query("SELECT pdtl FROM PurchaseDtl pdtl JOIN pdtl.po as purchaseOrder WHERE purchaseOrder.id=:poId")
 public List<PurchaseDtl> getPurchaseDt1ByPoId(Integer poId);
	
	@Query("SELECT count(pdtl) FROM PurchaseDtl pdtl JOIN pdtl.po as purchaseOrder WHERE purchaseOrder.id=:poId")
	public Integer getPurchaseDtlsCountByPoId(Integer poId);
	
}
