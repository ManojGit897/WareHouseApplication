package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nt.consts.PurchaseOrderStatus;
import com.nt.consts.SaleOrderStatus;
import com.nt.model.PurchaseOrder;
import com.nt.model.SaleOrder;
import com.nt.model.SaleOrderDtl;
import com.nt.service.IPartService;
import com.nt.service.ISaleOrderService;
import com.nt.service.IShipmentTypeService;
import com.nt.service.IWhUserTypeService;

@Controller
@RequestMapping("/sale")
public class SaleOrderController {

	@Autowired
	private ISaleOrderService service;
	
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	
	@Autowired
	private IWhUserTypeService whUserTypeService;
	
	@Autowired
	private IPartService partService;
	
	
	/***
	 * For dynamic DropDown
	 */
	private void addCommonUi(Model model) {
		model.addAttribute("sts", shipmentTypeService.getShipmentIdAndCodeByEnable("Yes"));
		model.addAttribute("custs", whUserTypeService.getWhUserIdAndCodeByType("Customer"));
	}
	
	
	//1. show Register
	@GetMapping("/register")
	public String showReg(Model model) {
		addCommonUi(model);
		return "SaleOrderRegister";
	}
	
	//2. create order
	@PostMapping("/save")
	public String save(
			@ModelAttribute SaleOrder saleOrder,
			Model model) 
	{
		try {
			Integer id = service.saveSaleOrder(saleOrder);
			model.addAttribute("message", "Order '"+id+"' is created!");
		} catch (Exception e) {
			model.addAttribute("message", "Order is failed to created!");
			e.printStackTrace();
		}
		addCommonUi(model);
		return "SaleOrderRegister";
	}
	
	//3. display all
	@GetMapping("/all")
	public String getAll(Model model)
	{
		try {
			List<SaleOrder> list =  service.getAllSaleOrders();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SaleOrderData";
	}
	
//   3. Ajax validations 
	@GetMapping("/validate")
	@ResponseBody
	public String validateSaleOrderCode(@RequestParam String code,
			                                 @RequestParam Integer id) {
		
		String message="";
		if(id==0 && service.isSaleOrderCodeExist(code)) {
			message=code+" already exit";
		}
		else if(id!=0 && service.isSaleOrderCodeExistForEdit(code,id)){
			message=code+" already exit";
		}
		return message;
		
	}
	
	///---------------------------(Screen#2)------------------------------
		
	private void commonUiForParts(Model model) {
		model.addAttribute("parts", partService.getPartIdAndCode());
	}
	//po/parts?id=1
		@GetMapping("/parts")
		public String showSoPartsPage(
				@RequestParam Integer id,
				Model model
				) 
		{
			// Load PO from DB
			SaleOrder sale = service.getOneSaleOrder(id);
			
			//send PO to UI
			model.addAttribute("so", sale);
			
			// dropDown for parts
			commonUiForParts(model);
			
			List<SaleOrderDtl> soDtls = service.getSaleOrderDtlsByPoId(id); //OrderId/POId
			model.addAttribute("list", soDtls);
			
			return "SaleOrderParts";
		}
		
		@PostMapping("/addPart")
		public String addPart(SaleOrderDtl sdtl){
			service.saveSaleOrderDtl(sdtl);
			   
			Integer soId=sdtl.getSo().getId();
			
			if(SaleOrderStatus.SALE_OPEN.name()
					.equals(service.getCurrentStatusOfSo(soId))
					) {
				service.updateSoStatus(soId, SaleOrderStatus.SALE_READY.name());
			}
			return "redirect:parts?id="+soId;
		}
		
		@GetMapping("/removePart")
		public String removePart(
				@RequestParam Integer soId,
				@RequestParam Integer dtlId
				)
		{
			service.deleteSaleOrderDtl(dtlId);
			
			if(service.getSaleOrderDtlsCountByPoId(soId)==0) {
				service.updateSoStatus(soId, SaleOrderStatus.SALE_OPEN.name());
			}
			return "redirect:parts?id="+soId; //orerId/PO ID
		}
		
		
}
