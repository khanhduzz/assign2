package com.fsa.repositories;

import com.fsa.entities.Ebook;
import com.fsa.entities.Song;
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

public class EbookRepository {

    private EbookRepository() {
    }

    public static Ebook createEbook (Ebook eBook) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(eBook);
            session.flush();
            transaction.commit();
            return eBook;
        } catch (Exception e) {
            System.out.println("Error while create: " + e.getMessage());
        }
        return null;
    }

    public static Ebook getByIdWithDetails (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Ebook> eBookCriteriaQuery = criteriaBuilder.createQuery(Ebook.class);
            Root<Ebook> root = eBookCriteriaQuery.from(Ebook.class);

            root.fetch("orderLines", JoinType.LEFT);
            eBookCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

            Query<Ebook> query = session.createQuery(eBookCriteriaQuery);
            Ebook eBook = query.uniqueResult();

            session.flush();
            transaction.commit();
            return eBook;
        }
    }

    public static Ebook updateEbook (Ebook eBook) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(eBook);
            transaction.commit();
            return eBook;
        } catch (Exception e) {
            System.out.println("Error while update: " + e.getMessage());
        }
        return null;
    }

    public static void deleteEbook (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Ebook eBook = session.get(Ebook.class, id);
            if (eBook == null) {
                System.out.println("Ebook does not exist");
                return;
            }
            session.remove(eBook);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while delete: " + e.getMessage());
        }
    }

    public static List<Ebook> getAllEbooksWithDetails() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Ebook> eBookCriteriaQuery = criteriaBuilder.createQuery(Ebook.class);

            Root<Ebook> root = eBookCriteriaQuery.from(Ebook.class);
            eBookCriteriaQuery.select(root);

            Query<Ebook> query = session.createQuery(eBookCriteriaQuery);
            List<Ebook> eBooks = query.list();

            session.flush();
            transaction.commit();

            return eBooks;
        } catch (Exception e) {
            System.out.println("Error while get: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
