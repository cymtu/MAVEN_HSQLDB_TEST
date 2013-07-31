package com.mycompany.maven_irkpk;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Hibernate extends Observable{
    private static SessionFactory sessions;
    private static Session session;
    private static Hibernate hibernate;
    
    private Hibernate(){  
        try {
            sessions = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Hibernate getInstance(){
        if(hibernate==null) hibernate = new Hibernate();
        return hibernate;    
    }
    
    public void save(Vector v) { 
        session = sessions.openSession();
        Transaction tx = null;                
        try {
            tx = session.beginTransaction();
            for(Object tmp:v){
               session.save((Person)tmp);
            }
            tx.commit();
            try {
                // для HSQLDB иначе не сохраняет последние строки импорта
                session.connection().createStatement().execute("SHUTDOWN");
            } catch (SQLException ex) {
                Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
            }
            setChanged();
            notifyObservers();
            tx = null;
        } 
        catch (HibernateException e) {
            if (tx != null) 
                tx.rollback();
        } 
        finally {
            if(session.isOpen()) session.close();
        }
    }
    
    public void delete(){
        session = sessions.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();            
            List list = session.createQuery("from Person").list();
            for(Object p:list) session.delete(p);
            tx.commit();
            try {
                // для HSQLDB иначе не сохраняет последние строки импорта
                session.connection().createStatement().execute("SHUTDOWN");
            } catch (SQLException ex) {
                Logger.getLogger(Hibernate.class.getName()).log(Level.SEVERE, null, ex);
            }   
            setChanged();
            notifyObservers();
            tx = null;
        }
        catch (HibernateException e) {
            if (tx != null) 
                tx.rollback();
        } 
        finally {
            if(session.isOpen()) session.close();
        }
    }
    
    public List getList(){
        session = sessions.openSession();               
        List v = session.createQuery("from Person").list();
        session.close();      
        return v;
    }
}
