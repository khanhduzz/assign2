package com.fsa.repositories.repositories_hql;

import com.fsa.entities.Movie;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MovieHQL {

    private MovieHQL() {
    }

    public static Movie createMovie (Movie movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            System.out.println("Error while creating movie: " + e.getMessage());
        }
        return null;
    }

    public static Movie getByIdWithDetails (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Movie movie = session.createQuery(
                            "SELECT DISTINCT m FROM Movie m " +
                                    "LEFT JOIN FETCH m.orderLines " +
                                    "WHERE m.id = :movieId",
                            Movie.class)
                    .setParameter("movieId", id)
                    .uniqueResult();
            transaction.commit();
            return movie;
        } catch (Exception e) {
            System.out.println("Error while retrieving movie with id " + id + ": " + e.getMessage());
        }
        return null;
    }

    public static Movie updateMovie (Movie movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            System.out.println("Error while updating movie: " + e.getMessage());
        }
        return null;
    }

    public static List<Movie> getAllMoviesWithDetails () {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Movie> movies = session.createQuery(
                            "SELECT DISTINCT m FROM Movie m " +
                                    "LEFT JOIN FETCH m.orderLines",
                            Movie.class)
                    .getResultList();
            transaction.commit();
            return movies;
        } catch (Exception e) {
            System.out.println("Error while retrieving movies: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void deleteMovie(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            if (movie == null) {
                System.out.println("Movie with id " + id + " does not exist.");
                return;
            }
            session.remove(movie);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while deleting movie with id " + id + ": " + e.getMessage());
        }
    }
}


