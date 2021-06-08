package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.model.Part;

public interface IPartService {

	Integer savePart(Part part);
	void updatePart(Part part);
	void deletePart(Integer id);
	
	Part getOnePart(Integer id);
	List<Part> getAllParts();
	 
	 boolean getpartCodeCount(String code);
	 boolean getpartCodeCountForEdit(String code, Integer id);
	 
	 
	Map<Integer, String> getPartIdAndCode();
	
	
}
