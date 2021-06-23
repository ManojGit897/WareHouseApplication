package com.nt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.exception.DataNotFoundException;
import com.nt.model.Grn;
import com.nt.repo.GrnDtlReposiory;
import com.nt.repo.GrnReposiory;
import com.nt.service.IGrnService;



@Service
public class GrnServiceImpl implements IGrnService {

	@Autowired
	private GrnReposiory repo;
	
	private GrnDtlReposiory dtlRepo;
	
	public Integer saveGrn(Grn grn) {
		return repo.save(grn).getId();
	}

	public List<Grn> fetchAllGrns() {
		return repo.findAll();
	}

	public Grn getOneGrn(Integer id) {
		return repo.findById(id)
				.orElseThrow(
						()->new DataNotFoundException("GRN NOT EXIST")
						);
	}

	@Transactional
	@Override
	public void updateGrnDtlStatus(Integer dtlId, String name) {
		
		dtlRepo.updateGrnDtlStatus(dtlId, name);
	}

	
	
	
}
