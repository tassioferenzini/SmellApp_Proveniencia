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
import model.Symptom;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nathalianascimento
 */
public class SymptomDAO {

    private Session session;

    public void save(Symptom symptom) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(symptom);
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

    public Symptom getSymptom(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Symptom symptom = (Symptom) session.get(model.Symptom.class, new Integer(id));
            session.close();
            return symptom;
        } catch (Exception ex) {
            Logger.getLogger(SymptomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Symptom deleteSymptom(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            Symptom symptom = (Symptom) session.get(model.Symptom.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(symptom);
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

    public void deleteSymptom(Symptom symptom) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(symptom);
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

    public List<Symptom> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from model.Symptom");
            List<Symptom> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(SymptomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
