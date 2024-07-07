package com.fsa.repositories;

import com.fsa.entities.Order;
import com.fsa.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderRepository {

    private OrderRepository() {
    }

    public static Order getByIdWithDetails (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> orderCriteriaQuery = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = orderCriteriaQuery.from(Order.class);

            root.fetch("orderLines", JoinType.LEFT);
            orderCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

            Query<Order> query = session.createQuery(orderCriteriaQuery);
            Order order = query.uniqueResult();

            session.flush();
            transaction.commit();
            return order;
        }
    }

    public static Order getById (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            transaction.commit();
            return order;
        } catch (Exception e) {
            System.out.println("Error while getting the order");
        }
        return null;
    }

    public static Order addOrder (Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(order);
            session.flush();
            transaction.commit();
            return order;
        } catch (Exception e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
        return null;
    }

    public static Order updateOrder (Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(order);
            session.flush();
            transaction.commit();
            return order;
        } catch (Exception e) {
            System.out.println("Error updating order: " + e.getMessage());
        }
        return null;
    }

    public static void deleteOrder (Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(order);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }

    public static List<Order> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);

            Root<Order> root = criteriaQuery.from(Order.class);
            root.fetch("orderLines", JoinType.LEFT)
                    .fetch("product", JoinType.LEFT);

            criteriaQuery.select(root);
            Query<Order> query = session.createQuery(criteriaQuery);

            return query.getResultList();
        }
    }
}
