/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.ActivityN;
import connection.ConnectHibernate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class ActivityDAO {

    private Session session;

    public void save(ActivityN activity) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(activity);
            session.flush();
            tx.commit();//faz a transacao
        } catch (Exception e) {
            e.printStackTrace();
            //cancela a transcao em caso de falha
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public ActivityN getActivity(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            ActivityN activity = (ActivityN) session.get(ActivityN.class, new Integer(id));
            session.close();
            return activity;
        } catch (Exception ex) {
            Logger.getLogger(ActivityN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ActivityN> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from Activity");
            List<ActivityN> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ActivityN deleteActivity(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            ActivityN activity = (ActivityN) session.get(ActivityN.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(activity);
            session.flush();
            tx.commit();//faz a transacao
        } catch (Exception e) {
            e.printStackTrace();
            //cancela a transcao em caso de falha
            tx.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public void deleteActivity(ActivityN activity) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(activity);
            session.flush();
            tx.commit();//faz a transacao
        } catch (Exception e) {
            e.printStackTrace();
            //cancela a transcao em caso de falha
            tx.rollback();
        } finally {
            session.close();
        }

    }

}
