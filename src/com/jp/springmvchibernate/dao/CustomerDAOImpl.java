package com.jp.springmvchibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jp.springmvchibernate.entity.Customer;

@Repository //allows component scan and exception handling
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// get the result list
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer newCustomer) {

		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// actually save new customer 
		currentSession.saveOrUpdate(newCustomer);
		
	}


	@Override
	public Customer getCustomer(int id) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// read customer from db 
		return currentSession.get(Customer.class, id);
	
	}


	@Override
	public void deleteCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer customer = currentSession.get(Customer.class, id);
		
		currentSession.delete(customer);
		
		// we could also use: 
		// Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		// theQuery.setParameter("customerId", id);
		// theQuery.executeUpdate();
		
	}


	@Override
	public List<Customer> searchCustomers(String searchName) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		//only search by name if theSearchName is not empty
		if (searchName != null && searchName.trim().length() > 0) {
			
			//search for firstName or lastName (case insensitive)
			theQuery = currentSession.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name", Customer.class);
			theQuery.setParameter("name", "%" + searchName.toLowerCase() + "%");
		} else {
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
		
		//execute query and get the result list
		List<Customer> customers = theQuery.getResultList();
		
		// return results
		return customers;
	}

}
