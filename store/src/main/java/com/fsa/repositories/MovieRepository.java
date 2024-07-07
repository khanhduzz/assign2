package com.fsa.repositories;

import com.fsa.entities.Movie;
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

public class MovieRepository {

    private MovieRepository() {
    }

    public static Movie createMovie (Movie movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(movie);
            session.flush();
            transaction.commit();
            return movie;
        } catch (Exception e) {
            System.out.println("Error while create: " + e.getMessage());
        }
        return null;
    }

    public static Movie getByIdWithDetails (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Movie> movieCriteriaQuery = criteriaBuilder.createQuery(Movie.class);
            Root<Movie> root = movieCriteriaQuery.from(Movie.class);

            root.fetch("orderLines", JoinType.LEFT);
            movieCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

            Query<Movie> query = session.createQuery(movieCriteriaQuery);
            Movie movie = query.uniqueResult();

            session.flush();
            transaction.commit();
            return movie;
        }
    }

    public static Movie updateMovie (Movie movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            System.out.println("Error while update: " + e.getMessage());
        }
        return null;
    }

    public static List<Movie> getAllMoviesWithDetails() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Movie> movieCriteriaQuery = criteriaBuilder.createQuery(Movie.class);

            Root<Movie> root = movieCriteriaQuery.from(Movie.class);
            movieCriteriaQuery.select(root);

            Query<Movie> query = session.createQuery(movieCriteriaQuery);
            List<Movie> movies = query.list();

            session.flush();
            transaction.commit();

            return movies;
        } catch (Exception e) {
            System.out.println("Error while get: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void deleteMovie (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            if (movie == null) {
                System.out.println("Movie does not exist");
                return;
            }
            session.remove(movie);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while delete: " + e.getMessage());
        }
    }
}
