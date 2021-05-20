package com.nt.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType,Integer>{

	
	// register check
	@Query("SELECT count(shipCode) from ShipmentType where shipCode=:code")
	Integer getShipmentTypeCodeCount(String code);
	
	// edit check
	
	@Query("SELECT count(shipCode) from ShipmentType where shipCode=:code and id!=:id")
	Integer getShipmentTypeCodeCountForEdit(String code, Integer id);
	
	//For Charts Data
		@Query("SELECT shipMode, count(shipMode) FROM ShipmentType GROUP BY shipMode")
		List<Object[]> getShipmentTypeModeAndCount();
}
