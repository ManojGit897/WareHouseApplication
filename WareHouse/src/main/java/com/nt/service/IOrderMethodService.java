package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.model.OrderMethod;

public interface IOrderMethodService {
	
	Integer saveOrderMethod(OrderMethod om);
	List<OrderMethod> getAllOrderMethods();
	void deleteOrderMethod(Integer id);
    OrderMethod getOneOrderMethod(Integer id);
    void updateOrderMethod(OrderMethod om);
    boolean isOrderMethodCodeExit(String code);
	 boolean isOrderMethodCodeExitForEdit(String code,Integer id);
	 
	 List<Object[]> getorderTypeAndCount();
	 
	 Map<Integer, String> getOrderMethodIdAndCount();
}
