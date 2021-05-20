package com.nt.controller;
	
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nt.exception.ShipmentTypeNotFoundException;
import com.nt.model.ShipmentType;
import com.nt.service.IShipmentTypeService;
import com.nt.util.ShipmentTypeUtil;
import com.nt.view.ShipmentTypeExcelView;

@Controller
@RequestMapping("/st")
public class ShipmentTypeController {

	private static final Logger LOG=LoggerFactory.getLogger(ShipmentTypeController.class);
	
	@Autowired
	private IShipmentTypeService service;
	
	@Autowired
	private ShipmentTypeUtil util;
	
	@Autowired
	private ServletContext context;
	
	//1. show register page 
	@GetMapping("/register")
	public String ShowRegister() {
		return "ShipmentTypeRegister";
	}
	
	//2.on click submit
	@PostMapping("/save")
	public String saveShipmentType(@ModelAttribute ShipmentType shipmentType,Model model) {
		
		LOG.info("ENTERED INTO SAVE METHOD");
		
		try {
		// call service method
		Integer id=service.saveShipmentType(shipmentType);
		
		// success msg
		String msg="Shipment Type '"+id+"' is created";
		model.addAttribute("message", msg);
		}
		
		catch (Exception e) {
			LOG.error(" Unable to process request due to {}",e.getMessage());
		    e.printStackTrace();
		}
		LOG.info("ABOUT GO TO UI PAGE !");
		// goto UI page---------
		
		return "ShipmentTypeRegister";
		
	}
	
	//3. find all method
	@GetMapping("/all")
	public String fetchShipmentTypes(Model model) {
		
		LOG.info("ENTERED INTO FETCH ALL ROWS");
		
		try {
		// call service 
		List<ShipmentType> list=service.getAllShipmentType();
		// send data to UI
		model.addAttribute("list", list);
		}
		catch (Exception e) {
			LOG.error(" Unable to fetch data  {}",e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("MOVING DATA PAGE TO DISPLAY");
		// goto UI page
		return "ShipmentTypeData";
			
		
	}
	
	// 4. delete row 
	@GetMapping("/delete")
	public String deleteShipmentTypeData(
			@RequestParam Integer id,
			Model model	
			) {
		LOG.info("ENTERED INTO DELETE METHOD");
		try {
			// call service
			service.deleteShipmentType(id);
			
			String msg="Shipment Type '"+id+"' is Deleted !";
			LOG.debug(msg);
			model.addAttribute("message",msg);
		} catch (ShipmentTypeNotFoundException e) {
			LOG.error("Unable to process delete Request {}",e.getMessage());
			e.printStackTrace();
			model.addAttribute("message",e.getMessage());
		}
		
		// load the new data 
		// call service 
				List<ShipmentType> list=service.getAllShipmentType();
				
		 		//send to uI
				
				model.addAttribute("list", list);
				
		return "ShipmentTypeData";
	}
	
	// 5. show edit page
	@GetMapping("/edit")
	public String showShipmentTypeEdt(
			@RequestParam Integer id,
			Model model) {
		LOG.info("ENTERED INTO EDIT METHOD");
		String page = null;
		try {
		ShipmentType st=service.getShipmentType(id);
		model.addAttribute("shipmentType", st);	
		
		page= "ShipmentTypeEdit";  
	}catch (ShipmentTypeNotFoundException e) {
		LOG.error("Unable to process Edit Request : {}",e.getMessage());
		e.printStackTrace();
		//if row not exist
		page = "ShipmentTypeData";
		model.addAttribute("message", e.getMessage());

		//load new data
		List<ShipmentType> list = service.getAllShipmentType();
		//send data to UI
		model.addAttribute("list", list);
		
	}
		LOG.info("ABOUT TO GO PAGE {} ", page);
	//GOTO UI
	return page;
}
		

	
	//6. read from data and submit
	@PostMapping("/update")
	public String updateShipmentType(@ModelAttribute ShipmentType shipmentType ) {
	
		LOG.info("ENTERED INTO UPDATE METHOD");
		try {
		// call service for for update 
		service.updateShipmentType(shipmentType);
	
		}
		catch (Exception e) {
			LOG.error("Unable to Perform Update : {}",e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("REDIRECTING TO FETCH ALL ROWS");
		// redirect to All
		
		return "redirect:all";
	}
	
	//  7.Ajax validations 
	@GetMapping("/validate")
	@ResponseBody
	public String validateShipmentTypeCode(@RequestParam String code,
			                                 @RequestParam Integer id) {
		
		String message="";
		if(id==0 && service.isShipmentTypeCodeExit(code)) {
			message=code+" already exit";
		}
		else if(id!=0 && service.isShipmentTypeCodeExitForEdit(code,id)){
			message=code+" already exit";
		}
		return message;
		
	}
	 	// 8. Export to Excel
	@GetMapping("/excel")
	public ModelAndView exportData() {
		ModelAndView m=new ModelAndView();
		m.setView(new ShipmentTypeExcelView()); // view class object
		
		// fetch data from from Db
		// call service 
				List<ShipmentType> list=service.getAllShipmentType();
				// send data to UI
				m.addObject("list", list);
		return m;
		
	}
	//9. Show charts
		@GetMapping("/charts")
	public String generateCharts() {
			List<Object[]> list=service.getShipmentTypeModeAndCount();
			String path=context.getRealPath("/"); // root folder
			util.generatePieChart(path, list);
			util.generateBarChart(path, list);
			return "ShipmentTypeCharts";
		}
}
