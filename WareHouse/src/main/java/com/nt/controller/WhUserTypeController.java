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

import com.nt.exception.WhUserTypeNotFound;
import com.nt.model.WhUserType;
import com.nt.service.IWhUserTypeService;
import com.nt.util.MailUtil;
import com.nt.util.WhUserTypeUtil;
import com.nt.view.WhUserTypeExcelView;

@RequestMapping("/ut")
@Controller
public class WhUserTypeController {

	private static final Logger LOG=LoggerFactory.getLogger(ShipmentTypeController.class);
	
	@Autowired
	private IWhUserTypeService service;
	
	@Autowired
	private WhUserTypeUtil util;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private MailUtil mailUtil;
	
	//1. show register page 
		@GetMapping("/register")
		public String ShowRegister() {
			return "WhUserTypeRegister";
		}
		
		//2.on click submit
		@PostMapping("/save")
		public String saveWhUserType(@ModelAttribute WhUserType whut,Model model) {
			
			LOG.info("ENTERED INTO SAVE METHOD");
			
			try {
			// call service method
			Integer id=service.saveWhUserType(whut);
			
			if(id>0) {
				new Thread(
						()->{
							mailUtil.sendEmail(whut.getUserEmail(),"Auto Generated Email",mailUtil.getWhUserTemplateData(whut));
						}).start();
			}
			// success msg
			String msg="WhUserType '"+id+"' is created";
			model.addAttribute("message", msg);
			}
			
			catch (Exception e) {
				LOG.error(" Unable to process request due to {}",e.getMessage());
			    e.printStackTrace();
			    model.addAttribute("message"," Unable to process request Due to duplicate Data" );
			}
			LOG.info("ABOUT GO TO UI PAGE !");
			
			// goto UI page---------
			return "WhUserTypeRegister";
		}
		
		//3. find all method
		@GetMapping("/all")
		public String fetchWhUserTypes(Model model) {
			
			LOG.info("ENTERED INTO FETCH ALL ROWS");
			
			try {
			// call service 
			List<WhUserType> list=service.getAllWhUserTypes();
			// send data to UI
			model.addAttribute("list", list);
			}
			catch (Exception e) {
				LOG.error(" Unable to fetch data  {}",e.getMessage());
				e.printStackTrace();
			}
			
			LOG.info("MOVING DATA PAGE TO DISPLAY");
			// goto UI page
			return "WhUserTypeData";			
			
		}
		
		// 4. delete row 
		@GetMapping("/delete")
		public String deleteWhUserTypeData(
				@RequestParam Integer id,
				Model model	
				) {
			LOG.info("ENTERED INTO DELETE METHOD");
			try {
				// call service
				service.deleteWhUserType(id);
				
				String msg="WhUserType  '"+id+"' is Deleted !";
				LOG.debug(msg);
				model.addAttribute("message",msg);
			} catch (WhUserTypeNotFound e) {
				LOG.error("Unable to process delete Request {}",e.getMessage());
				e.printStackTrace();
				model.addAttribute("message",e.getMessage());
			}
			
			// load the new data 
			// call service 
						List<WhUserType> list=service.getAllWhUserTypes();
						// send data to UI
						model.addAttribute("list", list);
					
			return "WhUserTypeData";
		}
		
		// 5. show edit page
		@GetMapping("/edit")
		public String showWhUserTypeData(
				@RequestParam Integer id,
				Model model) {
			LOG.info("ENTERED INTO EDIT METHOD");
			String page = null;
			try {
			WhUserType whut=service.getOneWhUserType(id);
			model.addAttribute("whusertype", whut);	
			
			page= "WhUserTypeEdit";  
		}catch (WhUserTypeNotFound e) {
			LOG.error("Unable to process Edit Request : {}",e.getMessage());
			e.printStackTrace();
			//if row not exist
			page = "WhUserTypeData";
			model.addAttribute("message", e.getMessage());

			//load new data
			List<WhUserType> list=service.getAllWhUserTypes();
			// send data to UI
			model.addAttribute("list", list);
			
		}
			LOG.info("ABOUT TO GO PAGE {} ", page);
		//GOTO UI
		return page;
	}
		
		//6. read from data and submit
		@PostMapping("/update")
		public String updateWhUserType(@ModelAttribute WhUserType whut ) {
		
			LOG.info("ENTERED INTO UPDATE METHOD");
			try {
			// call service for for update 
			service.updateWhUserType(whut);
		
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
		public String validateWhUserTypeCode(@RequestParam String code,
				                                 @RequestParam Integer id) {
			
			String message="";
			if(id==0 && service.isWhUserTypeCodeExit(code)) {
				message=code+" already exit";
			}
			else if(id!=0 && service.isWhUserTypeCodeExitForEdit(code,id)){
				message=code+" already exit";
			}
			return message;
			
		}
		
	//  7.Ajax validations 
			@GetMapping("/validateemail")
			@ResponseBody
			public String validateWhUserTypeEmail(@RequestParam String email,
					                                 @RequestParam Integer id) {
				
				String message="";
				if(id==0 && service.getWhUserTypeuserEmailCount(email)) {
					message=email+" already exit";
				}
				else if(id!=0 && service.getWhUserTypeuserEmailCountForEdit(email,id)){
					message=email+" already exit";
				}
				return message;
				
			}
			
		//  7.Ajax validations 
					@GetMapping("/validateidnum")
					@ResponseBody
					public String validateWhUserIdNum(@RequestParam String idnum,
							                                 @RequestParam Integer id) {
						
						String message="";
						if(id==0 && service.getWhUserTypeuserIdNumCount(idnum)) {
							message=idnum+" already exit";
						}
						else if(id!=0 && service.getWhUserTypeuserIdNumCountForEdit(idnum,id)){
							message=idnum+" already exit";
						}
						return message;
						
					}
		
		
		// 8. Export to Excel
		@GetMapping("/excel")
		public ModelAndView exportData() {
			ModelAndView m=new ModelAndView();
			m.setView(new WhUserTypeExcelView()); // view class object
			
			// fetch data from from Db
			// call service 
					List<WhUserType> list=service.getAllWhUserTypes();
					// send data to UI
					m.addObject("list", list);
			return m;
			
		}
		
		//9. Show charts
			@GetMapping("/charts")
			public String generateCharts() {
					List<Object[]> list=service.getWhUserTypUserIDAndCount();
					String path=context.getRealPath("/"); // root folder
					util.generatePieChart(path, list);
					util.generateBarChart(path, list);
					return "WhUserTypeCharts";
					
				}  
		
	
	
}
