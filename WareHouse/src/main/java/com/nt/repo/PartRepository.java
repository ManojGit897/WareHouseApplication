package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {
	
	
	// register check
		@Query("SELECT count(partCode) from Part where partCode=:code")
		Integer getpartCodeCount(String code);
		
		// edit check
		
		@Query("SELECT count(partCode) from Part where partCode=:code and id!=:id")
		Integer getpartCodeCountForEdit(String code, Integer id);
		
		@Query("SELECT id,partCode from Part")
		List<Object[]>  getPartIdAndCode();

}
