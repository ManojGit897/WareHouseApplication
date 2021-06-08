package com.nt.service.impl;

import java.util.List
;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.exception.UomNotFoundException;
import com.nt.model.Uom;
import com.nt.repo.UomRepository;
import com.nt.service.IUomService;

@Service
public class UomServiceImpl implements IUomService {
	
	@Autowired
	private UomRepository repo;

	@Override
	public Integer saveUom(Uom uom) {
		uom=repo.save(uom);
		return uom.getId();
	}

	@Override
	public List<Uom> getAllUom() {
		
		List<Uom> list=repo.findAll();
		return list;
	}
	@Override
	public void deleteUom(Integer id) {
		
		repo.delete(getUom(id));
	}

	@Override
	public Uom getUom(Integer id) {
		
	return	repo.findById(id).
			orElseThrow(
					()->new UomNotFoundException("Uom  id '"+id+"' Not exit")
				
				);
	
	}

	@Override
	public void updateUom(Uom uom) {
		
		repo.save(uom);
		
	}

	@Override
	public boolean isUomModelExit(String model) {
		return repo.getUomModelCount(model) > 0 ;
	}

	@Override
	public boolean isUomModelExitForEdit(String model, Integer id) {
		return repo.getUomModelCountForEdit(model,id) > 0 ;
	}

	@Override
	public List<Object[]> getUomTypeAndCount() {
		
		return repo.getUomTypeAndCount();
	}

	public Map<Integer, String> getUomIdAndModel() {
		List<Object[]> list =  repo.getUomIdAndModel();
		//JDK 1.8
		return list.stream()
				.collect(
						Collectors.toMap(
								ob->(Integer)ob[0], ob->(String)ob[1])
						);

		/*Map<Integer, String> map = new LinkedHashMap<>();
		for(Object[] ob: list ) {
			map.put((Integer)ob[0], (String)ob[1]);
		}
		return map;*/
	}
	

}
