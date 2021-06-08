package com.nt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.SaleOrder;
import com.nt.model.SaleOrderDtl;
import com.nt.repo.SaleOrderDtlRepository;
import com.nt.repo.SaleOrderRepository;
import com.nt.service.ISaleOrderService;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {

	@Autowired
	private SaleOrderRepository repo;
	
@Autowired
	private SaleOrderDtlRepository dtlrepo;
	
	@Override
	public Integer saveSaleOrder(SaleOrder saleOrder) {
		
		return repo.save(saleOrder).getId();
	}

	@Override
	public SaleOrder getOneSaleOrder(Integer id) {
		
		return repo.findById(id).orElseThrow(
				()-> new RuntimeException("Sale Order Not Found")
		                                  );
	}

	@Override
	public List<SaleOrder> getAllSaleOrders() {
		
		return repo.findAll();
	}

	@Override
	public boolean isSaleOrderCodeExist(String code) {
		
		return repo.getOrderCodeCount(code)>0;
	}

	@Override
	public boolean isSaleOrderCodeExistForEdit(String code, Integer id) {
		
		return repo.getOrderCodeCountForEdit(code, id)>0;
	}

	
	
	// for scereen#2--------
	@Override
	public Integer saveSaleOrderDtl(SaleOrderDtl sdtl) {
		
		return dtlrepo.save(sdtl).getId();
	}

	@Override
	public List<SaleOrderDtl> getSaleOrderDtlsByPoId(Integer id) {
		// TODO Auto-generated method stub
		return dtlrepo.getSaleOrderDtlsByPoId(id);
	}

	@Override
	public void deleteSaleOrderDtl(Integer dtlId) {
		
		if(dtlrepo.existsById(dtlId)) {
			dtlrepo.deleteById(dtlId);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCurrentStatusOfSo(Integer poId) {
		
		return repo.getCurrentStatusOfPo(poId);
	}

	
	@Transactional
	@Override
	public void updateSoStatus(Integer poId, String newStatus) {
		
		repo.updateSoStatus(poId, newStatus);
	}

	@Override
	public Integer getSaleOrderDtlsCountByPoId(Integer poId) {
		
		return dtlrepo.getSaleOrderDtlsCountByPoId(poId); 
	}

}
