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

import com.nt.exception.OrderMethodNotFound;
import com.nt.model.OrderMethod;
import com.nt.service.IOrderMethodService;
import com.nt.util.OrderMethodUtil;
import com.nt.view.OrderMethodExcelView;

@Controller
@RequestMapping("/om")
public class OrderMethodController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderMethodController.class);
	@Autowired
	private IOrderMethodService service;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private OrderMethodUtil util;
	
	// 1. show register 
	@GetMapping("/register")
	public String ShowRegister() {
		
		return "OrderMethodRegister";
	}
	
	//2. save method
	@PostMapping("/save")
	public String saveOrderMethod(@ModelAttribute OrderMethod orderMethod,Model model) {
		LOG.info("ENTERED INTO SAVE METHOD");
		
		try {
		Integer id=service.saveOrderMethod(orderMethod);
		String msg=" Order Method '"+id+"' created";
		LOG.debug(msg);
		model.addAttribute("message", msg);
		}
		catch (Exception e) {
			LOG.error("UNABLE TO SAVE {}",e.getMessage());
			e.printStackTrace();
			 model.addAttribute("message"," Unable to process request Due to duplicate Data" );
		}
		LOG.info("ABOUT TO LEAVE SAVE METHOD");
		return "OrderMethodRegister";
	}
	
	///3. fetch data from DB and send to UI
		@GetMapping("/all")
		public String fetchData(Model model) {
			LOG.info("ENTERED INTO FETCH ALL ROWS");
			try {
			List<OrderMethod> list = service.getAllOrderMethods();
			model.addAttribute("list", list);
			}
			catch (Exception e) {
				LOG.error(" Unable to fetch data  {}",e.getMessage());
				e.printStackTrace();
			}
			LOG.info("MOVING DATA PAGE TO DISPLAY");
			// goto UI page
			return "OrderMethodData";
		}
		
		//4. delete data..
		@GetMapping("/delete")
		public String deleteOrderMethod( @RequestParam Integer id,Model model) {
			try {
			service.deleteOrderMethod(id);
			String msg="Order method '"+id+"' deleted";
			model.addAttribute("message", msg);
			
			// load the new data
			List<OrderMethod> list = service.getAllOrderMethods();
			model.addAttribute("list", list);
			}catch (OrderMethodNotFound e) {
				e.printStackTrace();
				model.addAttribute("message", e.getMessage());
				
				// load the new data
				List<OrderMethod> list = service.getAllOrderMethods();
				model.addAttribute("list", list);
			}
			return "OrderMethodData";
		}
		
		// 5. show edit page
		@GetMapping("/edit")
		public String showShipmentTypeEdt(
				@RequestParam Integer id,
				Model model) {
			LOG.info("ENTERED INTO EDIT METHOD");
			String page = null;
			try {
		  OrderMethod om=service.getOneOrderMethod(id);
			model.addAttribute("orderMethod", om);	
			
			page= "OrderMethodEdit";  
		}catch (OrderMethodNotFound e) {
			LOG.error("Unable to process Edit Request : {}",e.getMessage());
			e.printStackTrace();
			//if row not exist
			page = "OrderMethodData";
			model.addAttribute("message", e.getMessage());

			// load the new data
			List<OrderMethod> list = service.getAllOrderMethods();
			model.addAttribute("list", list);
			
		}
			LOG.info("ABOUT TO GO PAGE {} ", page);
		//GOTO UI
		return page;
	}
		//6. read from data and submit
		@PostMapping("/update")
		public String updateShipmentType(@ModelAttribute OrderMethod orderMethod,Model model) {
		
			LOG.info("ENTERED INTO UPDATE METHOD");
			try {
			// call service for for update 
			service.updateOrderMethod(orderMethod);
		
			}
			catch (OrderMethodNotFound e) {
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
		public String validateOrderMethodCode(@RequestParam String code,
				                                 @RequestParam Integer id) {
			
			String message="";
			if(id==0 && service.isOrderMethodCodeExit(code)) {
				message=code+" already exit";
			}
			else if(id!=0 && service.isOrderMethodCodeExitForEdit(code,id)){
				message=code+" already exit";
			}
			return message;
			
		}
		
		// 8. Export to Excel
		@GetMapping("/excel")
		public ModelAndView exportData() {
			ModelAndView m=new ModelAndView();
			m.setView(new OrderMethodExcelView()); // view class object
			
			// fetch data from from Db
			// call service 
					List<OrderMethod> list=service.getAllOrderMethods();
					// send data to UI
					m.addObject("list", list);
			return m;
			
		}
		
		//9. Show charts
				@GetMapping("/charts")
			public String generateCharts() {
					List<Object[]> list=service.getorderTypeAndCount();
					String path=context.getRealPath("/"); // root folder
					util.generatePieChart(path, list);
					util.generateBarChart(path, list);
					return "OrderMethodCharts";
				}
	
	

}
