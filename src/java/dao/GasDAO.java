/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectHibernate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Gas;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nathalianascimento
 */
public class GasDAO {

    private Session session;

    public void save(Gas gas) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(gas);
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

    public Gas getGas(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Gas gas = (Gas) session.get(model.Gas.class, new Integer(id));
            session.close();
            return gas;
        } catch (Exception ex) {
            Logger.getLogger(Gas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Gas getGas(String name) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Criteria criteria = session.createCriteria(model.Gas.class);
            Gas gasObject = (Gas) criteria.add(Restrictions.eq("name", name)).uniqueResult();
            session.close();
            return gasObject;
        } catch (Exception ex) {
            Logger.getLogger(GasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Gas deleteGas(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            Gas gas = (Gas) session.get(model.Gas.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(gas);
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

    public void deleteGas(Gas gas) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(gas);
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

    public List<Gas> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from model.Gas");
            List<Gas> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(GasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
