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
import model.Report;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nathalianascimento
 */
public class ReportDAO {

    private Session session;

    public void save(Report report) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(report);
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

    public Report getReport(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Report report = (Report) session.get(model.Report.class, new Integer(id));
            session.close();
            return report;
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Report deleteReport(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            Report report = (Report) session.get(model.Report.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(report);
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

    public void deleteReport(Report report) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(report);
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

    public List<Report> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from model.Report");
            List<Report> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
