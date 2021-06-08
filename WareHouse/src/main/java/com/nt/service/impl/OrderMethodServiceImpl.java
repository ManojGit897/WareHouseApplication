package com.nt.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.exception.OrderMethodNotFound;
import com.nt.model.OrderMethod;
import com.nt.repo.OrderMethodRepository;
import com.nt.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository repo; //HAS-A
	
	@Override
	public Integer saveOrderMethod(OrderMethod om) {
		
		return repo.save(om).getId();
	}

	@Override
	public List<OrderMethod> getAllOrderMethods() {
	
		return repo.findAll();
	}

	@Override
	public void deleteOrderMethod(Integer id) {
		 
        repo.delete(getOneOrderMethod(id));
	}

	@Override
	public OrderMethod getOneOrderMethod(Integer id) {
		 
		return repo.findById(id).orElseThrow(
				()-> new OrderMethodNotFound(" Order method '"+id+"' Not Exit")
				
				
				);
	}

	@Override
	public void updateOrderMethod(OrderMethod om) {
		   
		if(om.getId()==null || !repo.existsById(om.getId()))
			throw new OrderMethodNotFound("Order Method  '"+om.getId()+"' Not Exist");
		repo.save(om).getId();

	}

	@Override
	public boolean isOrderMethodCodeExit(String code) {
		 
		return repo.isOrderMethodCodeExit(code)>0;
	}

	@Override
	public boolean isOrderMethodCodeExitForEdit(String code, Integer id) {
		 
		return repo.isOrderMethodCodeExitForEdit(code,id)>0;
	}

	@Override
	public List<Object[]> getorderTypeAndCount() {
		
		return repo.getorderTypeAndCount();
	}

	@Override
	public Map<Integer, String> getOrderMethodIdAndCount() {
		List<Object[]> list=repo.getOrderMethodIdAndCount();
		
		 //JDK 1.8
				return list.stream()
						.collect(
								Collectors.toMap(
										ob->(Integer)ob[0], ob->(String)ob[1])
								);
	}

	

}
