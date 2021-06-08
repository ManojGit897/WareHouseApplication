package com.nt.controller;

import java.util.List;

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

import com.nt.exception.OrderMethodNotFound;
import com.nt.exception.PartNotFoundException;
import com.nt.model.OrderMethod;
import com.nt.model.Part;
import com.nt.service.IOrderMethodService;
import com.nt.service.IPartService;
import com.nt.service.IUomService;
import com.nt.view.OrderMethodExcelView;
import com.nt.view.PartExcelView;

@Controller
@RequestMapping("/part")
public class PartController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderMethodController.class);

	@Autowired
	private IPartService service;
	
	@Autowired
	private IUomService uomService;
	
	@Autowired
	private IOrderMethodService omservice;
	
	/**
	 * Sends data to create dynamic dropdown for Uom
	 */
	private void commonUi(Model model) {
		model.addAttribute("uoms", uomService.getUomIdAndModel());
		model.addAttribute("oms",omservice.getOrderMethodIdAndCount());
	}
	
	private void commonFetchAll(Model model) {
		model.addAttribute("list", service.getAllParts());
	}

	@GetMapping("/register")
	public String showReg(Model model) {
		commonUi(model);
		return "PartRegister";
	}

	@PostMapping("/save")
	public String savePart(
			@ModelAttribute Part part,
			Model model) 
	{
		LOG.info("ENTERED INTO SAVE METHOD");
		
		try {
		Integer id  = service.savePart(part);
		model.addAttribute("message", "Part '"+id+"' Created!");
		LOG.debug("message", "Part '"+id+"' Created!");
		commonUi(model);
		}
		catch (Exception e) {
			LOG.error("UNABLE TO SAVE {}",e.getMessage());
			e.printStackTrace();
			 model.addAttribute("message"," Unable to process request Due to duplicate Data" );
		}
		LOG.info("ABOUT TO LEAVE SAVE METHOD");
		return "PartRegister";
	}

	//3. fetch data from DB and send to UI
	@GetMapping("/all")
	public String displayAll(
			Model model) 
	{
		LOG.info("ENTERED INTO FETCH ALL ROWS");
		try {
		commonFetchAll(model);
		}catch (Exception e) {
			LOG.error(" Unable to fetch data  {}",e.getMessage());
			e.printStackTrace();
		}
		LOG.info("MOVING DATA PAGE TO DISPLAY");
		// goto UI page
		return "PartData";
	} 
	
	//4. delete data..
			@GetMapping("/delete")
			public String deletePart( @RequestParam Integer id,Model model) {
				try {
				service.deletePart(id);
				String msg="Part '"+id+"' deleted !";
				model.addAttribute("message", msg);
				
				// load the new data
				commonFetchAll(model);
				
				}catch (PartNotFoundException e) {
					e.printStackTrace();
					model.addAttribute("message", e.getMessage());
					
					// load the new data
					commonFetchAll(model);
				}
				return "PartData";
			}

	// 5. show edit page
		@GetMapping("/edit")
		public String showPartEdit(
				@RequestParam Integer id,
					Model model) {
				LOG.info("ENTERED INTO EDIT METHOD");
				String page = null;
				try {
			  Part part=service.getOnePart(id);
				model.addAttribute("part", part);	
				
				page= "PartEdit";  
			}catch (PartNotFoundException e) {
				LOG.error("Unable to process Edit Request : {}",e.getMessage());
				e.printStackTrace();
				//if row not exist
				page = "PartData";
				model.addAttribute("message", e.getMessage());

				// load the new data
				commonFetchAll(model);
				
				
			}
				LOG.info("ABOUT TO GO PAGE {} ", page);
			//GOTO UI
			return page;
		}	
	
		//6. read from data and submit
				@PostMapping("/update")
				public String updatePart(@ModelAttribute Part part,Model model) {
				
					LOG.info("ENTERED INTO UPDATE METHOD");
					try {
					// call service for for update 
					service.updatePart(part);
				
					}
					catch (PartNotFoundException e) {
						LOG.error("Unable to Perform Update : {}",e.getMessage());
						e.printStackTrace();
						model.addAttribute("message", e.getMessage());
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
		if(id==0 && service.getpartCodeCount(code)) {
			message=code+" already exit";
		}
		else if(id!=0 && service.getpartCodeCountForEdit(code,id)){
			message=code+" already exit";
		}
		return message;
}
	// 8. Export to Excel
			@GetMapping("/excel")
			public ModelAndView exportData() {
				ModelAndView m=new ModelAndView();
				m.setView(new PartExcelView()); // view class object
				
				// fetch data from from Db
				// call service 
						List<Part> list=service.getAllParts();
						// send data to UI
						m.addObject("list", list);
				return m;
				
			}
			
		/*	//9. Show charts
					@GetMapping("/charts")
				public String generateCharts() {
						List<Object[]> list=service.getorderTypeAndCount();
						String path=context.getRealPath("/"); // root folder
						util.generatePieChart(path, list);
						util.generateBarChart(path, list);
						return "OrderMethodCharts";
					}  */
		
}