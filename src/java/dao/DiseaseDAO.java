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
import model.Disease;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nathalianascimento
 */
public class DiseaseDAO {

    private Session session;

    public void save(Disease disease) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(disease);
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

    public Disease getDisease(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Disease disease = (Disease) session.get(model.Disease.class, new Integer(id));
            session.close();
            return disease;
        } catch (Exception ex) {
            Logger.getLogger(DiseaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Disease deleteDisease(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            Disease disease = (Disease) session.get(model.Disease.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(disease);
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

    public void deleteDisease(Disease disease) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(disease);
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

    public List<Disease> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from model.Disease");
            List<Disease> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(DiseaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
