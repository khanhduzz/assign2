package com.fsa.repositories.repositories_hql;

import com.fsa.entities.Song;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SongHQL {

    private SongHQL() {
    }

    public static Song createSong(Song song) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(song);
            transaction.commit();
            return song;
        } catch (Exception e) {
            System.out.println("Error while creating song: " + e.getMessage());
        }
        return null;
    }

    public static Song getByIdWithDetails(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Song song = session.createQuery(
                            "SELECT DISTINCT s FROM Song s " +
                                    "LEFT JOIN FETCH s.orderLines" +
                                    "WHERE s.id = :songId",
                            Song.class)
                    .setParameter("songId", id)
                    .uniqueResult();
            transaction.commit();
            return song;
        } catch (Exception e) {
            System.out.println("Error while retrieving song with id " + id + ": " + e.getMessage());
        }
        return null;
    }

    public static Song updateSong(Song song) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(song);
            transaction.commit();
            return song;
        } catch (Exception e) {
            System.out.println("Error while updating song: " + e.getMessage());
        }
        return null;
    }

    public static void deleteSong(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Song song = session.get(Song.class, id);
            if (song == null) {
                System.out.println("Song with id " + id + " does not exist.");
                return;
            }
            session.remove(song);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error while deleting song with id " + id + ": " + e.getMessage());
        }
    }

    public static List<Song> getAllSongsWithDetails() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Song> songs = session.createQuery(
                            "SELECT DISTINCT s FROM Song s " +
                                    "LEFT JOIN FETCH s.orderLines",
                            Song.class)
                    .getResultList();
            transaction.commit();
            return songs;
        } catch (Exception e) {
            System.out.println("Error while retrieving songs: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

