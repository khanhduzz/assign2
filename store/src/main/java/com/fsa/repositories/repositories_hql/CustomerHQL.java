package com.fsa.repositories.repositories_hql;

import com.fsa.entities.Customer;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerHQL {

    private CustomerHQL() {
    }

    public static Customer findById (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.createQuery("FROM Customer WHERE id = :customerId",
                            Customer.class)
                    .setParameter("customerId", id)
                    .uniqueResult();
            transaction.commit();
            return customer;
        } catch (Exception e) {
            System.out.println("Error while finding customer with id " + id);
        }
        return null;
    }

    public static Customer findByIdWithDetails(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.createQuery(
                            "SELECT DISTINCT c FROM Customer c " +
                                    "LEFT JOIN FETCH c.orders o " +
                                    "LEFT JOIN FETCH o.orderLines ol " +
                                    "LEFT JOIN FETCH ol.product WHERE c.id = :customerId",
                            Customer.class)
                    .setParameter("customerId", id)
                    .uniqueResult();
            transaction.commit();
            return customer;
        } catch (Exception e) {
            System.out.println("Error while finding customer with id " + id);
        }
        return null;
    }

    public static Customer addCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            System.out.println("Error while adding customer " + customer);
        }
        return null;
    }

    public static Customer updateCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            System.out.println("Error while updating customer " + customer);
        }
        return null;
    }

    public static void deleteCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while deleting customer " + customer);
        }
    }

    public static List<Customer> getAllCustomers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Customer> customers = session.createQuery(
                            "SELECT DISTINCT c FROM Customer c " +
                                    "LEFT JOIN FETCH c.orders o " +
                                    "LEFT JOIN FETCH o.orderLines ol " +
                                    "LEFT JOIN FETCH ol.product",
                            Customer.class)
                    .getResultList();
            transaction.commit();
            return customers;
        } catch (Exception e) {
            System.out.println("Error while retrieving customers");
        }
        return new ArrayList<>();
    }

}
