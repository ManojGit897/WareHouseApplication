package com.nt.service;

import java.util.List;

import com.nt.model.Grn;

public interface IGrnService {

	Integer saveGrn(Grn grn);
	List<Grn> fetchAllGrns();
	Grn getOneGrn(Integer id);
	
	void updateGrnDtlStatus(Integer dtlId, String name); 
	
	
}
