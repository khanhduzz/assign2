package com.fsa.repositories;

import com.fsa.entities.Song;
import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SongRepository {

    public static Song createSong (Song song) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(song);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

//    public static Song updateSong (Long id, Song updateSong) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()){
//            Transaction transaction = session.beginTransaction();
//            Song song = session.get(Song.class, id);
//
//        }
//    }
}
