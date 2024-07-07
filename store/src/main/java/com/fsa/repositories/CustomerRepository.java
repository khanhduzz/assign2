package com.fsa.repositories;

import com.fsa.entities.Customer;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerRepository {

    private CustomerRepository() {
    }

    public static Customer findById (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.enableFetchProfile("customer-with-orders");
            Customer customer =  session.get(Customer.class, id);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            System.out.println("Error while finding customer with id " + id);
        }
        return null;
    }

//    public static Customer findByIdWithDetails (Long id) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            EntityGraph<Customer> entityGraph = em.createEntityGraph(Customer.class);
//            entityGraph.addAttributeNodes("orders");
//            Subgraph<Order> orderSubgraph = entityGraph.addSubgraph("orders");
//            orderSubgraph.addAttributeNodes("orderLines");
//
//            Map<String, Object> properties = new HashMap<>();
//            properties.put("javax.persistence.fetchgraph", entityGraph);
//
//            Customer customer = em.find(Customer.class, customerId, properties);
//            em.close();
//            emf.close();
//        }
//    }

    public static Customer addCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            session.flush();
            transaction.commit();
            return customer;
        } catch (Exception e) {
            System.out.println("Error while adding customer " + customer);
        }
        return null;
    }

    public static Customer updateCustomer (Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while updating customer " + customer);
        }
        return null;
    }
}
