package com.nt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.exception.PartNotFoundException;
import com.nt.model.Part;
import com.nt.repo.PartRepository;
import com.nt.service.IPartService;
import com.nt.util.MyAppUtil;


@Service
public class PartServiceImpl implements IPartService {   
	
	@Autowired
	private PartRepository repo;

	public Integer savePart(Part part) {
		return repo.save(part).getId();
	}

	public void updatePart(Part part) {
		repo.save(part);
	}

	public void deletePart(Integer id) {
		repo.delete(getOnePart(id));
	}

	
	public Part getOnePart(Integer id) {
		return repo.findById(id).orElseThrow(
				()->new PartNotFoundException("not exist")
				);
	}

	
	public List<Part> getAllParts() {
		return repo.findAll();
	}

	@Override
	public boolean getpartCodeCount(String code) {
		
		return repo.getpartCodeCount(code)>0;
	}

	@Override
	public boolean getpartCodeCountForEdit(String code, Integer id) {
		
		return repo.getpartCodeCountForEdit(code, id)>0;
	}

	@Override
	public Map<Integer, String> getPartIdAndCode() {
		List<Object[]> list=repo.getPartIdAndCode();
		
		return MyAppUtil.convertListToMap(list);
	}

}
