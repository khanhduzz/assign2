package com.fsa.repositories;

import com.fsa.entities.Product;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ProductRepository {

    private ProductRepository() {
    }

    public static Product findById (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.flush();
            transaction.commit();
            return product;
        } catch (Exception e) {
            System.out.println("Error when get product");
        }
        return null;
    }

    public static Product findByName (String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Product> product = session.createQuery("FROM Product WHERE title =:title", Product.class)
                    .setParameter(title, "%" + title + "%");
            session.flush();
            transaction.commit();
            return product.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error when get product");
        }
        return null;
    }
}
