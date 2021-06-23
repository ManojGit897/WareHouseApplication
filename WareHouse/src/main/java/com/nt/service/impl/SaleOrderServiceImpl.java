package com.nt.service.impl;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.SaleOrder;
import com.nt.model.SaleOrderDtl;
import com.nt.repo.SaleOrderDtlRepository;
import com.nt.repo.SaleOrderRepository;
import com.nt.service.ISaleOrderService;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {

	@Autowired
	private SaleOrderRepository repo; //has-a

	@Autowired
	private SaleOrderDtlRepository dtlrepo;// HAS-A

	@Override
	public Integer saveSaleOrder(SaleOrder saleOrder) {

		return repo.save(saleOrder).getId();
	}

	@Override
	public SaleOrder getOneSaleOrder(Integer id) {

		return repo.findById(id).orElseThrow(() -> new RuntimeException("Sale Order Not Found"));
	}

	@Override
	public List<SaleOrder> getAllSaleOrders() {

		return repo.findAll();
	}

	@Override
	public boolean isSaleOrderCodeExist(String code) {

		return repo.getOrderCodeCount(code) > 0;
	}

	@Override
	public boolean isSaleOrderCodeExistForEdit(String code, Integer id) {

		return repo.getOrderCodeCountForEdit(code, id) > 0;
	}

	// for scereen#2--------
	@Override
	public Integer saveSaleOrderDtl(SaleOrderDtl sdtl) {

		return dtlrepo.save(sdtl).getId();
	}

	@Override
	public Integer getSaleOrderDtlsCountBySoId(Integer soId) {

		return dtlrepo.getSaleOrderDtlsCountBySoId(soId);
	}

	@Override
	public List<SaleOrderDtl> getSaleOrderDtlsBySoId(Integer id) {

		return dtlrepo.getSaleOrderDtlsBySoId(id);
	}

	@Override
	public void deleteSaleOrderDtl(Integer dtlId) {

		if (dtlrepo.existsById(dtlId)) {
			dtlrepo.deleteById(dtlId);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public String getCurrentStatusOfSo(Integer soId) {

		return repo.getCurrentStatusOfSo(soId);
	}

	@Transactional
	@Override
	public void updateSoStatus(Integer soId, String newStatus) {

		repo.updateSoStatus(soId, newStatus);
	}

	@Override
	public Optional<SaleOrderDtl> getSaleOrderDtlByPartIdAndSoId(Integer partId, Integer soId) {

		return dtlrepo.getSaleOrderDtlByPartIdAndSoId(partId, soId);
	}

	@Transactional
	@Override
	public Integer updateSaleOrderDtlQtyByDtlId(Integer newQty, Integer dtlId) {

		return dtlrepo.updateSaleOrderDtlQtyByDtlId(newQty, dtlId);
	}

}
