package com.jp.springmvchibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.springmvchibernate.dao.CustomerDAO;
import com.jp.springmvchibernate.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer newCustomer) {
		customerDAO.saveCustomer(newCustomer);

	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {

		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String searchName) {
		
		return customerDAO.searchCustomers(searchName);
	}

}
