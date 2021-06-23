package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.GrnDtl;

public interface GrnDtlReposiory extends JpaRepository<GrnDtl, Integer> {

	@Modifying
	@Query("UPDATE GrnDtl SET status=:status WHERE id=:id")
	 void updateGrnDtlStatus(Integer id,String status);
}
