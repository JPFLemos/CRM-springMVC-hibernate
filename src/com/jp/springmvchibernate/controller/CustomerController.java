package com.jp.springmvchibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jp.springmvchibernate.entity.Customer;
import com.jp.springmvchibernate.service.CustomerService;
import com.jp.springmvchibernate.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject the customer DAO
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {
		
		// get customers from the service
		List<Customer> customers = null;
				
		// check for sort field
		if (sort != null) {
			int sortField = Integer.parseInt(sort);
			customers = customerService.getCustomers(sortField);
		} else {
			
			// no sort field was provided, default to sorting by last name
			customers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		// add the customers to the model
		theModel.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		
		//Model Attribute to bind form data
		Customer newCustomer = new Customer();
		
		model.addAttribute("customer", newCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer newCustomer) {
		
		customerService.saveCustomer(newCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		// get the customer from the service
		Customer customer = customerService.getCustomer(id);
		
		// set customer as a model attribute to pre-populate form
		model.addAttribute("customer", customer);
		
		// send over to our form
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id, Model model) {
		
		customerService.deleteCustomer(id);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("searchName") String searchName, Model theModel) {
		
		// search customers from the service
		
		List<Customer> customers = customerService.searchCustomers(searchName);
		
		// add customer to the model
		
		theModel.addAttribute("customers", customers);
		
		return "list-customers";
	}
}
