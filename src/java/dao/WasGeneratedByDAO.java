/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import PROV.DM.WasGeneratedBy;
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
public class WasGeneratedByDAO {

    private Session session;

    public void save(WasGeneratedBy wasGeneratedBy) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(wasGeneratedBy);
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

    public WasGeneratedBy getWasGeneratedBy(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            WasGeneratedBy wasGeneratedBy = (WasGeneratedBy) session.get(PROV.DM.WasGeneratedBy.class, new Integer(id));
            session.close();
            return wasGeneratedBy;
        } catch (Exception ex) {
            Logger.getLogger(WasGeneratedBy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<WasGeneratedBy> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from WasGeneratedBy");
            List<WasGeneratedBy> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(WasGeneratedByDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public WasGeneratedBy deleteWasGeneratedBy(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            WasGeneratedBy wasGeneratedBy = (WasGeneratedBy) session.get(PROV.DM.WasGeneratedBy.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasGeneratedBy);
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

    public void deleteWasGeneratedBy(WasGeneratedBy wasGeneratedBy) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(wasGeneratedBy);
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
