package com.fsa.utils;

import com.fsa.entities.Customer;
import com.fsa.entities.Ebook;
import com.fsa.entities.Movie;
import com.fsa.entities.Order;
import com.fsa.entities.OrderLine;
import com.fsa.entities.Product;
import com.fsa.entities.Song;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    @Getter
    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {

        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                Configuration cfg = new Configuration().configure();
                cfg.addAnnotatedClass(Product.class);
                cfg.addAnnotatedClass(Customer.class);
                cfg.addAnnotatedClass(Order.class);
                cfg.addAnnotatedClass(OrderLine.class);
                cfg.addAnnotatedClass(Song.class);
                cfg.addAnnotatedClass(Movie.class);
                cfg.addAnnotatedClass(Ebook.class);
                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder()
                        .applySettings(cfg.getProperties());

                sessionFactory = cfg.buildSessionFactory(standardServiceRegistryBuilder.build());
            } catch (Exception e) {
                System.out.println("Error while build session factory: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
}
