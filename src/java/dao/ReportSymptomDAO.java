/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectHibernate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report_Symptom;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nathalianascimento
 */
public class ReportSymptomDAO {

    private Session session;

    public void save(Report_Symptom report_symptom) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(report_symptom);
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

    public Report_Symptom getReport_Symptom(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Report_Symptom report_symptom = (Report_Symptom) session.get(model.Report_Symptom.class, new Integer(id));
            session.close();
            return report_symptom;
        } catch (Exception ex) {
            Logger.getLogger(ReportSymptomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Report_Symptom deleteReport_Symptom(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            Report_Symptom report_symptom = (Report_Symptom) session.get(model.Report_Symptom.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(report_symptom);
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

    public void deleteReport_Symptom(Report_Symptom report_symptom) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(report_symptom);
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
