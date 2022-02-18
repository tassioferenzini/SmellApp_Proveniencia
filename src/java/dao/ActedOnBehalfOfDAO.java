/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import PROV.DM.ActedOnBehalfOf;
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
public class ActedOnBehalfOfDAO {

    private Session session;

    public void save(ActedOnBehalfOf actedOnBehalfOf) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(actedOnBehalfOf);
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
    
    public ActedOnBehalfOf getActedOnBehalfOf(int id){
        try {
            session = (Session) ConnectHibernate.getSession();
            ActedOnBehalfOf wasAttributedTo = (ActedOnBehalfOf) session.get(PROV.DM.ActedOnBehalfOf.class, new Integer(id));
            session.close();
            return wasAttributedTo;
        } catch (Exception ex) {
            Logger.getLogger(ActedOnBehalfOf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<ActedOnBehalfOf> getAll(){
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from ActedOnBehalfOf");
            List<ActedOnBehalfOf> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(ActedOnBehalfOfDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ActedOnBehalfOf deleteWasAttributedTo(int id){
        
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            ActedOnBehalfOf wasAttributedTo = (ActedOnBehalfOf) session.get(PROV.DM.ActedOnBehalfOf.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasAttributedTo);
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
    
    public void deleteWasAttributedTo(ActedOnBehalfOf wasAttributedTo){
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(wasAttributedTo);
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
