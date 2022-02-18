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
import model.Report_Gases;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nathalianascimento
 */
public class ReportGasDAO {

    private Session session;

    public void save(Report_Gases report_gas) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(report_gas);
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

    public Report_Gases getReport_Gas(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Report_Gases report_gas = (Report_Gases) session.get(model.Report_Gases.class, new Integer(id));
            session.close();
            return report_gas;
        } catch (Exception ex) {
            Logger.getLogger(ReportGasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Report_Gases deleteReport_Gas(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            Report_Gases report_gas = (Report_Gases) session.get(model.Report_Gases.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(report_gas);
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

    public void deleteReport_Gas(Report_Gases report_gas) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(report_gas);
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
    
     public List<Report_Gases> getAll() {
        try {
            session = (Session) ConnectHibernate.getSession();
            Query query = session.createQuery("from model.Report_Gases");
            List<Report_Gases> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(ReportGasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
