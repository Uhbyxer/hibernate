package com.howtodoinjava.hibernate.test;

import com.howtodoinjava.hibernate.test.dto.EmployeeEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

public class TestJpa {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAUnit");
        EntityManager entityManager = factory.createEntityManager();

        String email = insert(entityManager);
        System.out.println("----------------- by firstName");
        List<EmployeeEntity> byFirstname = findByFirstname(entityManager, "demo");
        System.out.println(byFirstname);

        EmployeeEntity emp10 = findById(entityManager, 10);
        System.out.println("------------ Emp 10");
        System.out.println(emp10);

        EmployeeEntity empByEmail = findByEmail(entityManager, "demo-user111@mail.com");
        System.out.println("------------ empByEmail");
        System.out.println(empByEmail);



        entityManager.close();
        factory.close();
    }

    private static String insert(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmail("demo-user" + UUID.randomUUID().toString() + "@mail.com");
        emp.setFirstName("demo");
        emp.setLastName("user");

        entityManager.persist(emp);

        entityManager.getTransaction().commit();

        return emp.getEmail();
    }

    private static EmployeeEntity findById(EntityManager entityManager, Integer id) {
        EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class, id);
        return employeeEntity;
    }

    private static EmployeeEntity findByEmail(EntityManager entityManager, String email) {
        String sql = "SELECT e FROM Employee e WHERE e.email = :email";
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(sql, EmployeeEntity.class);
        query.setParameter("email", email);


        EmployeeEntity employeeEntity = query.getSingleResult();

        return employeeEntity;
    }


    private static List<EmployeeEntity> findByFirstname(EntityManager entityManager, String firstName) {
        TypedQuery<EmployeeEntity> query = entityManager.createNamedQuery("Employee.findByFirstName", EmployeeEntity.class);
        query.setParameter("firstName", firstName);

        List<EmployeeEntity> entityList = query.getResultList();

        return entityList;
    }
}
