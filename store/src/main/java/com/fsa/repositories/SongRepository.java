package com.fsa.repositories;

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

public class SongRepository {

    private SongRepository() {
    }

    public static Song createSong (Song song) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(song);
            session.flush();
            transaction.commit();
            return song;
        } catch (Exception e) {
            System.out.println("Error while create: " + e.getMessage());
        }
        return null;
    }

    public static Song getByIdWithDetails (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Song> songCriteriaQuery = criteriaBuilder.createQuery(Song.class);
            Root<Song> root = songCriteriaQuery.from(Song.class);

            root.fetch("orderLines", JoinType.LEFT);
            songCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

            Query<Song> query = session.createQuery(songCriteriaQuery);
            Song song = query.uniqueResult();

            session.flush();
            transaction.commit();
            return song;
        }
    }

    public static Song updateSong (Song song) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(song);
            transaction.commit();
            return song;
        } catch (Exception e) {
            System.out.println("Error while update: " + e.getMessage());
        }
        return null;
    }

    public static void deleteSong (Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Song song = session.get(Song.class, id);
            if (song == null) {
                System.out.println("Song does not exist");
                return;
            }
            session.remove(song);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while delete: " + e.getMessage());
        }
    }

    public static List<Song> getAllSongsWithDetails() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Song> songCriteriaQuery = criteriaBuilder.createQuery(Song.class);

            Root<Song> root = songCriteriaQuery.from(Song.class);
            songCriteriaQuery.select(root);

            Query<Song> query = session.createQuery(songCriteriaQuery);
            List<Song> songs = query.list();

            session.flush();
            transaction.commit();

            return songs;
        } catch (Exception e) {
            System.out.println("Error while get: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
