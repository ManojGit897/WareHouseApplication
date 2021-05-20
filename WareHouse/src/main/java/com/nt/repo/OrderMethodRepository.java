package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod,Integer> {

	@Query("SELECT COUNT(orderCode) FROM OrderMethod WHERE orderCode=:code")
	Integer isOrderMethodCodeExit(String code);

	@Query("SELECT COUNT(orderCode) FROM OrderMethod WHERE orderCode=:code AND id!=:id")
	Integer isOrderMethodCodeExitForEdit(String code, Integer id);

	//For Charts Data
			@Query("SELECT orderType, count(orderType) FROM OrderMethod GROUP BY orderType")
			List<Object[]> getorderTypeAndCount();
	
	
}
