package com.jp.springmvchibernate.service;

import java.util.List;

import com.jp.springmvchibernate.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers(int lastName);

	public void saveCustomer(Customer newCustomer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String searchName);

}
