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

import com.nt.exception.UomNotFoundException;
import com.nt.model.Uom;
import com.nt.service.IUomService;
import com.nt.util.UomUtil;
import com.nt.view.UomExcelView;

@Controller
@RequestMapping("/uom")
public class UomController {
	
	@Autowired
 private IUomService service;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private UomUtil util;
	
	private static final Logger LOG=LoggerFactory.getLogger(ShipmentTypeController.class);
	
	private void commonFetchAll(Model model) {
		List<Uom> list =  service.getAllUom();
		model.addAttribute("list",list);
	}

	
	//1. Show register page
	@GetMapping("/register")
	public String ShowRegister() {
		return "UomRegister";
		
	}
	
	//2.click on submit
	@PostMapping("/save")
	public String saveUomType(@ModelAttribute Uom uom,Model model) {
		
		LOG.info("ENTERED INTO SAVE METHOD");
		try {
		Integer id=service.saveUom(uom);
		String message="Uom  id '"+id+"' created";
		model.addAttribute("message", message);
		}catch (Exception e) {
			LOG.error(" Unable to process request due to {}",e.getMessage());
		    e.printStackTrace();
		}
		LOG.info("ABOUT GO TO UI PAGE !");
		// goto UI page---------
		
		return "UomRegister";
	}
	
	//3. find all method
	@GetMapping("/all")
	public String fetchUom(Model model) {
		LOG.info("ENTERED INTO FETCH ALL ROWS");
		try {
		/*List<Uom> list=service.getAllUom();
		model.addAttribute("list",list);*/
		commonFetchAll(model);
		}
		catch (Exception e) {
			LOG.error(" Unable to fetch data  {}",e.getMessage());
			e.printStackTrace();
		}
		
		LOG.info("MOVING DATA PAGE TO DISPLAY");
		// goto UI page
		return "UomData";
	}
	
	//4. delete row
	@GetMapping("/delete")
	public String deleteUom(@RequestParam Integer id,Model model) {
		String msg="";
		LOG.info("ENTERED INTO DELETE METHOD");

		try {
		// call service
		service.deleteUom(id);
		 msg= "Uom '"+id+"'id Deleted";
		 LOG.debug(msg);
		model.addAttribute("message", msg);
		}catch (UomNotFoundException e) {
			LOG.error("Unable to process delete Request {}",e.getMessage());
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}	
		
		// load the new data 
		// call service 
		/*List<Uom> list=service.getAllUom();
		model.addAttribute("list",list);*/
		commonFetchAll(model);
		
	
		return "UomData";
		
	}
	
	// 5. edit row
	@GetMapping("/edit")
	public String shouUomEdit(@RequestParam Integer id,Model model) {
		String page="";
		LOG.info("ENTERED INTO EDIT METHOD");
		try {
		Uom uom=service.getUom(id);
		
		model.addAttribute("uom",uom);
		page="UomDataEdit";
		}
		catch (UomNotFoundException e) {
			LOG.error("Unable to process Edit Request : {}",e.getMessage());
			e.printStackTrace();
			model.addAttribute("message",e.getMessage());
			// load the new data 
			// call service 
			/*List<Uom> list=service.getAllUom();
			model.addAttribute("list",list); */
			commonFetchAll(model);
			page="UomData";
		}
		
		LOG.info("ABOUT TO GO PAGE {} ", page);
		//GOTO UI
		return page;
		
	}
	
	// 6. update row
	@PostMapping("/update")
	public String updateUom(@ModelAttribute Uom uom) {
		
		LOG.info("ENTERED INTO UPDATE METHOD");
		try {
		service.updateUom(uom);
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
	public String validateShipmentTypeCode(@RequestParam String model,
			                                 @RequestParam Integer id) {
		
		String message="";
		if(id==0 && service.isUomModelExit(model)) {
			message=model+" already exit";
		}
		else if(id!=0 && service.isUomModelExitForEdit(model,id)){
			message=model+" already exit";
		}
		return message;
		
	}
	
	// 8. Export to Excel
		@GetMapping("/excel")
		public ModelAndView exportData() {
			ModelAndView m=new ModelAndView();
			m.setView(new UomExcelView()); // view class object
			
			// fetch data from from Db
			// call service 
					List<Uom> list=service.getAllUom();
					// send data to UI
					m.addObject("list", list);
			return m;
			
		}
		
		//9. Show charts
		@GetMapping("/charts")
	public String generateCharts() {
			List<Object[]> list=service.getUomTypeAndCount();
			String path=context.getRealPath("/"); // root folder
			util.generatePieChart(path, list);
			util.generateBarChart(path, list);
			return "UomCharts";
		}

}
