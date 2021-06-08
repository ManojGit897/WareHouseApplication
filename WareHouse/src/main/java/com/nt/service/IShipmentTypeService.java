package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.model.ShipmentType;

public interface IShipmentTypeService {

	 Integer saveShipmentType(ShipmentType st);
	 List<ShipmentType>  getAllShipmentType();
	 void deleteShipmentType(Integer id);
	 ShipmentType getShipmentType(Integer id);
	 void updateShipmentType(ShipmentType st);
	 boolean isShipmentTypeCodeExit(String code);
	 boolean isShipmentTypeCodeExitForEdit(String code,Integer id);
	 
	 List<Object[]> getShipmentTypeModeAndCount();
	 
	 Map<Integer, String> getShipmentIdAndCodeByEnable(String enable);
	 
	 
	
	
}   

