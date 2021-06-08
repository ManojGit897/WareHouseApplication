package com.nt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.PurchaseDtl;
import com.nt.model.PurchaseOrder;
import com.nt.repo.PurchaseDtlRepository;
import com.nt.repo.PurchaseOrderRepository;
import com.nt.service.IPurchaseOrderService;


@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repo; //HAS-A
	
	@Autowired
	private PurchaseDtlRepository dt1Repo;

	@Override
	public Integer savePurchaseOrder(PurchaseOrder po) {
		return repo.save(po).getId();
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		return repo.findById(id).orElseThrow(()->new RuntimeException("Purchase Order NOT FOUND"));
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return repo.findAll();
	}

	@Override
	public boolean isPurchaseOrderCodeExist(String code) {
		return repo.getOrderCodeCount(code)>0;
	}

	@Override
	public boolean isPurchaseOrderCodeExistForEdit(String code, Integer id) {
		return repo.getOrderCodeCountForEdit(code, id)>0;
	}

	@Override
	public Integer savePurchaseDtl(PurchaseDtl pdt1) {
		
		
		return dt1Repo.save(pdt1).getId();
	}

	@Override
	public List<PurchaseDtl> getPurchaseDt1sByPoId(Integer id) {
		
		return dt1Repo.getPurchaseDt1ByPoId(id);
	}

	@Override
	public void deletePurchaseDtl(Integer dtlId) {
		if(dt1Repo.existsById(dtlId)) {
			dt1Repo.deleteById(dtlId);
		}
		
	}

	@Override
	public String getCurrentStatusOfPo(Integer poId) {
	
		return repo.getCurrentStatusOfPo(poId);
	}

	@Transactional
	@Override
	public void updatePoStatus(Integer poId, String newStatus) {
		
	 repo.updatePoStatus(poId, newStatus);
		
	}

	@Override
	public Integer getPurchaseDtlsCountByPoId(Integer poId) {
		
		return dt1Repo.getPurchaseDtlsCountByPoId(poId);
	}

	

}
