/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.EntityN;
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
public class EntityNDAO {

    private Session session;

    public void save(EntityN entityName) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(entityName);
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
    
    public EntityN getEntityN(int id){
        try {
            session = (Session) ConnectHibernate.getSession();
            EntityN entityName = (EntityN) session.get(EntityN.class, new Integer(id));
            session.close();
            return entityName;
        } catch (Exception ex) {
            Logger.getLogger(EntityN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<EntityN> getAll(){
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from Entity"); //nathy
            //Query query = session.createQuery("from EntityDisease");
            List<EntityN> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(EntityNDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public EntityN deleteEntityDisease(int id){
        
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            EntityN entityName = (EntityN) session.get(EntityN.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(entityName);
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
    
    public void deleteEntityDisease(EntityN entityName){
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(entityName);
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
