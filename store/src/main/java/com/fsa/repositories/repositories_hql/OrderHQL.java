package com.fsa.repositories.repositories_hql;

import com.fsa.entities.Order;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class OrderHQL {

    private OrderHQL() {
    }

    public static Order getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Order order = session.createQuery("FROM Order WHERE id = :orderId", Order.class)
                    .setParameter("orderId", id)
                    .uniqueResult();
            transaction.commit();
            return order;
        } catch (Exception e) {
            System.out.println("Error while finding order with id " + id + ": " + e.getMessage());
        }
        return null;
    }

    public static Order getByIdWithDetails(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Order order = session.createQuery(
                            "SELECT DISTINCT o FROM Order o " +
                                    "LEFT JOIN FETCH o.orderLines ol " +
                                    "LEFT JOIN FETCH ol.product " +
                                    "WHERE o.id = :orderId", Order.class)
                    .setParameter("orderId", id)
                    .uniqueResult();
            transaction.commit();
            return order;
        } catch (Exception e) {
            System.out.println("Error while finding order with id " + id + ": " + e.getMessage());
        }
        return null;
    }

    public static Order addOrder(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            System.out.println("Error while adding order: " + e.getMessage());
        }
        return null;
    }

    public static Order updateOrder(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            System.out.println("Error while updating order: " + e.getMessage());
        }
        return null;
    }

    public static void deleteOrder(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(order);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while deleting order: " + e.getMessage());
        }
    }

    public static List<Order> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT DISTINCT o FROM Order o " +
                                    "LEFT JOIN FETCH o.orderLines ol " +
                                    "LEFT JOIN FETCH ol.product", Order.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error while retrieving orders: " + e.getMessage());
        }
        return new ArrayList<>();
    }

}
