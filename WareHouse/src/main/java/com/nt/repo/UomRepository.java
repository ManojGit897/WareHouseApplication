package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.Uom;

public interface UomRepository extends JpaRepository<Uom,Integer> {
	
	// register check
		@Query("SELECT count(uomModel) from Uom where uomModel=:model")
		Integer getUomModelCount(String model);
		
		// edit check
		
		@Query("SELECT count(uomModel) from Uom where uomModel=:model and id!=:id")
		Integer getUomModelCountForEdit(String model, Integer id);
		
		//For Charts Data
		@Query("SELECT uomType, count(uomType) FROM Uom GROUP BY uomType")
		List<Object[]> getUomTypeAndCount();
		
		//For Integration
		@Query("SELECT id,uomModel FROM Uom")
		List<Object[]> getUomIdAndModel();

}
