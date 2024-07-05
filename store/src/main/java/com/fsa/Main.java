package com.fsa;

import com.fsa.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            System.out.println("Session open");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
    }
}