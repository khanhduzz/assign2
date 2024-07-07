package com.fsa.repositories;

import com.fsa.entities.Customer;
import com.fsa.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private CustomerRepository() {
    }

    public static Customer findById (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            System.out.println("Error while finding customer with id " + id);
        }
        return null;
    }

    public static Customer findByIdWithDetails (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> customerCriteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = customerCriteriaQuery.from(Customer.class);

            root.fetch("orders", JoinType.LEFT)
                    .fetch("orderLines", JoinType.LEFT)
                    .fetch("product", JoinType.LEFT);
            customerCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

            Query<Customer> query = session.createQuery(customerCriteriaQuery);
            Customer customer = query.uniqueResult();

            session.flush();
            transaction.commit();
            return customer;
        }
    }

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

    public static void deleteCustomer (Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(customer);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while deleting customer " + customer);
        }
    }

    public static List<Customer> getAllCustomers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> customerCriteriaQuery = criteriaBuilder.createQuery(Customer.class);

            Root<Customer> root = customerCriteriaQuery.from(Customer.class);
            root.fetch("orders", JoinType.LEFT)
                    .fetch("orderLines", JoinType.LEFT)
                    .fetch("product", JoinType.LEFT);

            customerCriteriaQuery.select(root);

            Query<Customer> query = session.createQuery(customerCriteriaQuery);
            List<Customer> customers = query.getResultList();

            transaction.commit();
            return customers;
        } catch (Exception e) {
            System.out.println("Error while retrieving customers");
        }
        return new ArrayList<>();
    }
}
