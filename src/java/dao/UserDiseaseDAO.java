/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectHibernate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User_Disease;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nathalianascimento
 */
public class UserDiseaseDAO {

    private Session session;

    public void save(User_Disease user_disease) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(user_disease);
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

    public User_Disease getUser_Disease(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            User_Disease user_disease = (User_Disease) session.get(model.User_Disease.class, new Integer(id));
            session.close();
            return user_disease;
        } catch (Exception ex) {
            Logger.getLogger(UserDiseaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User_Disease deleteUser_Disease(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            User_Disease user_disease = (User_Disease) session.get(model.User_Disease.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(user_disease);
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

    public void deleteUser_Disease(User_Disease user_disease) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(user_disease);
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
