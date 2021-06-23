package com.nt.repo;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.PurchaseDtl;

public interface PurchaseDtlRepository extends JpaRepository<PurchaseDtl, Integer>{

	
	@Query("SELECT pdtl FROM PurchaseDtl pdtl JOIN pdtl.po as purchaseOrder WHERE purchaseOrder.id=:poId")
 public List<PurchaseDtl> getPurchaseDt1ByPoId(Integer poId);
	
	@Query("SELECT count(pdtl) FROM PurchaseDtl pdtl JOIN pdtl.po as purchaseOrder WHERE purchaseOrder.id=:poId")
	public Integer getPurchaseDtlsCountByPoId(Integer poId);
	
	
	@Query("SELECT dtl FROM PurchaseDtl  dtl JOIN dtl.part as part JOIN dtl.po  as po WHERE part.id=:partId and po.id=:poId")
	public Optional<PurchaseDtl> getPurchaseDtlByPartIdAndPoId(Integer partId, Integer poId);
	
	@Modifying
	@Query("UPDATE PurchaseDtl SET qty = qty + :newQty WHERE id=:dtlId")
	public Integer updatePurchaseDtlQtyByDtlId(Integer newQty,Integer dtlId);
    
    /*
     * @Query("SELECT dtl FROM PurchaseDtl  dtl JOIN dtl.part as part JOIN dtl.po  as po WHERE part.id=:partId and po.id=:poId")
	public Optional<PurchaseDtl> getPurchaseDtlByPartIdAndPoId(Integer partId, Integer poId);
	
	@Modifying
	@Query("UPDATE PurchaseDtl SET qty = qty + :newQty WHERE id=:dtlId")
	public Integer updatePurchaseDtlQtyByDtlId(Integer newQty,Integer dtlId);
     * 
     */
}
