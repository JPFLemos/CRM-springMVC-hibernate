package com.jp.springmvchibernate.dao;

import java.util.List;

import com.jp.springmvchibernate.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers(int sortField);

	public void saveCustomer(Customer newCustomer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String searchName);
}
