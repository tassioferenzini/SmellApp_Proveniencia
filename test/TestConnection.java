import static com.sun.tools.xjc.reader.Ring.begin;
import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import connection.ConnectHibernate;
import static connection.ConnectHibernate.getSession;
import dao.UserDAO;
import model.Gas;
import model.Report;
import model.Report_Gas;
import model.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestConnection {
      /* @Test
       public void test() {
             User a = new User();
           //  a.setCpf("222");
             a.setName("juliano");
           //  a.setIdade(23);
             try {
                    Session session = (Session) ConnectHibernate.getSession();
                    session.save(a);
                    session.flush();
                    session.beginTransaction().commit();
                    session.close();
                   
                    Session session2 = ConnectHibernate.getSession();
                    Query q = session2.createQuery("from model.User where id =                      :id");
                    q.setParameter("id", 1);
                    User result = (User) q.uniqueResult();
                    session2.close();
                   
                    assertEquals("juliano", result.getName());
             } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
             }     
       }*/
       
   /*    @Test
       public void testUserReport() throws Exception {
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
           Date date = new Date();
            System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
        User user1 = new User();  
        user1.setName("Nathalia"); 
        Report report1 = new Report(user1,date);
        Report report2 = new Report(user1,date);
        
        Gas gas = new Gas("Methane","CH4");
  
        //report1.setDate(date);
       //report2.setDate(date);
        
        //report1.setUser(user1);
        //report2.setUser(user1);

        Session session = (Session) ConnectHibernate.getSession();
                    session.save(user1);
                    session.save(report1);
                    session.save(report2);
                    session.save(gas);
                    session.flush();
                    session.beginTransaction().commit();
                    session.close();
   //     begin();
     //   getSession().save(user1);
     //   getSession().save(report1);
      //  getSession().save(report2);  
 // commit();
 }*/
       
  /*      @Test
       public void testReportGas() throws Exception {
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
           Date date = new Date();
            System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
     //   User user1 = new User();  
      //  user1.setName("Nathalia"); 
       // Report report = new Report(user1,date);
          // 
           
             Session session = (Session) ConnectHibernate.getSession();
            // this user is obtained from the database with ID 40
            User user = (User) session.get(model.User.class, new Integer(9));
            
            Report report = user.getReports().get(0);
 
            // this group is obtained from the database with ID 26
            Gas gas = (Gas) session.get(model.Gas.class, new Integer(2));
           
           
           Report_Gas reportGas = new Report_Gas(report,gas);
           reportGas.setCount_time(10);
           
           
 
          
            session.save(reportGas);
            session.flush();
                    session.beginTransaction().commit();
                    session.close();
       }*/
    
    /* @Test
       public void testCreateUser() throws Exception {
           User user1 = new User();  
           user1.setName("Juliano");
           user1.setAge(35);
           user1.setFrequency_exercise(6);
           user1.setGender("male");
           user1.setHeight(1.75);
           user1.setWeight(92.0);
           
           new UserDAO().save(user1);
           
           System.out.println("Id user "+ user1.getId());
       }*/
       
       @Test
       public void testgetUser() throws Exception {
           User user1 = new UserDAO().getUser(13);
           System.out.println("User name ");
           System.out.println(user1.getName());
           
           new UserDAO().deleteUser(13);
           
           
       }
}