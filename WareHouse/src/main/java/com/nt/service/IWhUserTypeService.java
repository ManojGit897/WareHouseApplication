package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.model.WhUserType;



public interface IWhUserTypeService {  
	
	
	Integer saveWhUserType(WhUserType whut);
	List<WhUserType> getAllWhUserTypes();
	void deleteWhUserType(Integer id);
	WhUserType getOneWhUserType(Integer id);
    void updateWhUserType(WhUserType whut);
    
    // usercodeExit or not......................................................
    boolean isWhUserTypeCodeExit(String code);
	 boolean isWhUserTypeCodeExitForEdit(String code,Integer id); 
	 
	// userEmail Exit or not......................................................
	 boolean getWhUserTypeuserEmailCount(String email);
	 boolean getWhUserTypeuserEmailCountForEdit(String email, Integer id);
	 
	//userIdNum Exit or not......................................................
		
	// register check
			
	boolean getWhUserTypeuserIdNumCount(String idnum);	
	boolean getWhUserTypeuserIdNumCountForEdit(String idnum, Integer id);
	
	List<Object[]> getWhUserTypUserIDAndCount();
	
	//for integration
		Map<Integer,String> getWhUserIdAndCodeByType(String type);
	
	
	
	

}
