/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import PROV.DM.Used;
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
public class UsedDAO {

    private Session session;

    public void save(Used used) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(used);
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

    public Used getUsed(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Used used = (Used) session.get(PROV.DM.Used.class, new Integer(id));
            session.close();
            return used;
        } catch (Exception ex) {
            Logger.getLogger(Used.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Used> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from Used");
            List<Used> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(UsedDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Used deleteUsed(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            Used used = (Used) session.get(PROV.DM.Used.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(used);
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

    public void deleteUsed(Used used) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(used);
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
