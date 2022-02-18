/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import PROV.DM.WasAssociatedWith;
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
public class WasAssociatedWithDAO {

    private Session session;

    public void save(WasAssociatedWith wasAssociatedWith) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(wasAssociatedWith);
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

    public WasAssociatedWith getWasAssociatedWith(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            WasAssociatedWith wasAssociatedWith = (WasAssociatedWith) session.get(PROV.DM.WasAssociatedWith.class, new Integer(id));
            session.close();
            return wasAssociatedWith;
        } catch (Exception ex) {
            Logger.getLogger(WasAssociatedWith.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<WasAssociatedWith> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from WasAssociatedWith");
            List<WasAssociatedWith> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(WasAssociatedWithDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public WasAssociatedWith deleteWasAssociatedWith(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            WasAssociatedWith wasAssociatedWith = (WasAssociatedWith) session.get(PROV.DM.WasAssociatedWith.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasAssociatedWith);
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

    public void deleteWasAssociatedWith(WasAssociatedWith wasAssociatedWith) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(wasAssociatedWith);
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
