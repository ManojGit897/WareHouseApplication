package com.nt.service;

import java.util.List;

import com.nt.model.Uom;

public interface IUomService {
	
	Integer saveUom(Uom uom);
	List<Uom> getAllUom();
	void deleteUom(Integer id);
    Uom getUom(Integer id);
    void updateUom(Uom uom);
    boolean isUomModelExit(String model);
	 boolean isUomModelExitForEdit(String model,Integer id);
	 
	 List<Object[]> getUomTypeAndCount();
}
