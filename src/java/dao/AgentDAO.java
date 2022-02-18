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
import model.AgentP;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class AgentDAO {

    private Session session;

    public void save(AgentP agent) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(agent);
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

    public AgentP getAgent(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            AgentP agent = (AgentP) session.get(AgentP.class, new Integer(id));
            session.close();
            return agent;
        } catch (Exception ex) {
            Logger.getLogger(AgentP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<AgentP> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from Agent");
            List<AgentP> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(AgentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public AgentP deleteAgent(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            AgentP agent = (AgentP) session.get(AgentP.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(agent);
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

    public void deleteAgent(AgentP agent) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(agent);
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
