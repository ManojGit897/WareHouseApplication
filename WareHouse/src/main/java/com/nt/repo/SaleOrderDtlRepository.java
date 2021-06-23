package com.nt.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.nt.model.SaleOrderDtl;

public interface SaleOrderDtlRepository extends JpaRepository<SaleOrderDtl, Integer> {

	
	@Query("SELECT sdtl FROM SaleOrderDtl sdtl JOIN sdtl.so as SaleOrderDtl WHERE SaleOrderDtl.id=:soId")
		public List<SaleOrderDtl> getSaleOrderDtlsBySoId(Integer soId);
	
	@Query("SELECT count(sdtl) FROM SaleOrderDtl sdtl JOIN sdtl.so as SaleOrder WHERE SaleOrder.id=:soId")
	public Integer getSaleOrderDtlsCountBySoId(Integer soId);
	
	
	@Query("SELECT dtl FROM SaleOrderDtl  dtl JOIN dtl.part as part JOIN dtl.so  as so WHERE part.id=:partId and so.id=:soId")
    public Optional<SaleOrderDtl> getSaleOrderDtlByPartIdAndSoId(Integer partId, Integer soId);

       @Modifying
	@Query("UPDATE SaleOrderDtl SET qty = qty + :newQty WHERE id=:dtlId")
	public Integer updateSaleOrderDtlQtyByDtlId(Integer newQty,Integer dtlId);

}
