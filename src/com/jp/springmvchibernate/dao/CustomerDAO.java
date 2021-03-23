package com.jp.springmvchibernate.dao;

import java.util.List;

import com.jp.springmvchibernate.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
}
