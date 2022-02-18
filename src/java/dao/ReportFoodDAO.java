/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectHibernate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report_Food;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nathalianascimento
 */
public class ReportFoodDAO {

    private Session session;

    public void save(Report_Food report_food) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.save(report_food);
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

    public Report_Food getReport_Food(int id) {
        try {
            session = (Session) ConnectHibernate.getSession();
            Report_Food report_food = (Report_Food) session.get(model.Report_Food.class, new Integer(id));
            session.close();
            return report_food;
        } catch (Exception ex) {
            Logger.getLogger(ReportFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Report_Food deleteReport_Food(int id) {

        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            Report_Food report_food = (Report_Food) session.get(model.Report_Food.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(report_food);
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

    public void deleteReport_Food(Report_Food report_food) {
        Transaction tx = null; //permite transacao com o BD 
        try {
            session = (Session) ConnectHibernate.getSession();
            tx = session.beginTransaction();
            session.delete(report_food);
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
