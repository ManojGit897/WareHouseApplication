package com.nt.service.impl;

import java.util.List
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.exception.ShipmentTypeNotFoundException;
import com.nt.model.ShipmentType;
import com.nt.repo.ShipmentTypeRepository;
import com.nt.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	
	@Autowired
	private ShipmentTypeRepository repo;

	@Override
	public Integer saveShipmentType(ShipmentType st) {
		st=repo.save(st);
		return st.getId();
	}

	@Override
	public List<ShipmentType> getAllShipmentType() {
		List<ShipmentType> list=repo.findAll();
		return list;
	}

	@Override
	public void deleteShipmentType(Integer id) {
		
		// repo.deleteById(id);
		repo.delete(getShipmentType(id));
		// TODO Auto-generated method stub
		
	}

	@Override
	public ShipmentType getShipmentType(Integer id) {
		
	/*	Optional<ShipmentType> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}*/
		return repo.findById(id)
				.orElseThrow(
					()->new ShipmentTypeNotFoundException(
							"ShipmentType '"+id+"' Not Exist")
					);
	}

	@Override
	public void updateShipmentType(ShipmentType st) {
		// TODO Auto-generated method stub
		repo.save(st);
	}

	@Override
	public boolean isShipmentTypeCodeExit(String code) {
		
		/*
		Integer count = repo.getShipmentTypeCodeCount(code);
		boolean isExist = count > 0 ? true: false;
		return isExist;*/
		//return repo.getShipmentTypeCodeCount(code) > 0 ? true: false;
		return repo.getShipmentTypeCodeCount(code) > 0 ;
		
	}

	@Override
	public boolean isShipmentTypeCodeExitForEdit(String code, Integer id) {
		// TODO Auto-generated method stub
		return repo.getShipmentTypeCodeCountForEdit(code,id) > 0 ;
	}

	@Override
	public List<Object[]> getShipmentTypeModeAndCount() {
		
		return repo.getShipmentTypeModeAndCount();
	}

	
	
	
}
