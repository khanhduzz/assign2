package com.fsa.repositories.repositories_hql;

import com.fsa.entities.Ebook;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EbookHQL {

    private EbookHQL() {
    }

    public static Ebook createEbook(Ebook ebook) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(ebook);
            transaction.commit();
            return ebook;
        } catch (Exception e) {
            System.out.println("Error while creating ebook: " + e.getMessage());
        }
        return null;
    }

    public static Ebook getByIdWithDetails(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ebook ebook = session.createQuery(
                            "SELECT DISTINCT e FROM Ebook e " +
                                    "LEFT JOIN FETCH e.orderLines " +
                                    "WHERE e.id = :ebookId",
                            Ebook.class)
                    .setParameter("ebookId", id)
                    .uniqueResult();
            transaction.commit();
            return ebook;
        } catch (Exception e) {
            System.out.println("Error while retrieving ebook with id " + id + ": " + e.getMessage());
        }
        return null;
    }

    public static Ebook updateEbook(Ebook ebook) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(ebook);
            transaction.commit();
            return ebook;
        } catch (Exception e) {
            System.out.println("Error while updating ebook: " + e.getMessage());
        }
        return null;
    }

    public static void deleteEbook(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ebook ebook = session.get(Ebook.class, id);
            if (ebook == null) {
                System.out.println("Ebook with id " + id + " does not exist.");
                return;
            }
            session.remove(ebook);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while deleting ebook with id " + id + ": " + e.getMessage());
        }
    }

    public static List<Ebook> getAllEbooksWithDetails() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Ebook> ebooks = session.createQuery(
                            "SELECT DISTINCT e FROM Ebook e " +
                                    "LEFT JOIN FETCH e.orderLines",
                            Ebook.class)
                    .getResultList();
            transaction.commit();
            return ebooks;
        } catch (Exception e) {
            System.out.println("Error while retrieving ebooks: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

