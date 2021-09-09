package com.howtodoinjava.hibernate.test;

import org.hibernate.Session;

import com.howtodoinjava.hibernate.test.dto.EmployeeEntity;

import java.util.UUID;

public class TestHibernate {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
       
		//Add new Employee object
		EmployeeEntity emp = new EmployeeEntity();
		emp.setEmail("demo-user" + UUID.randomUUID().toString() + "@mail.com");
		emp.setFirstName("demo");
		emp.setLastName("user");
		
		session.save(emp);
		
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}

}
