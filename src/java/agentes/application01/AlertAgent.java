/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes.application01;

import dao.ActivityDAO;
import dao.AgentDAO;
import dao.ReportGasDAO;
import jade.core.Agent;
import jade.core.behaviours.*;
import java.sql.Timestamp;
import java.util.List;
import model.ActivityN;
import model.AgentP;
import model.Report_Gases;

/**
 *
 * @author Nathalia
 */
public class AlertAgent extends Agent {

    public AlertAgent(int id) {
        super();
    }

    protected void setup() {
        //FProvW3c
            AgentP agDevice = new AgentP();
           // agDevice.setIdUser(user.getId());
            agDevice.setTypeAgent("Software Agent - Alert");
            new AgentDAO().save(agDevice);

        addBehaviour(new TickerBehaviour(this, 100000) {
            @Override
            protected void onTick() {
                System.out.println("Estou vivo");
                 //FProvW3c
                ActivityN ac = new ActivityN();
                Timestamp startT = new Timestamp(System.currentTimeMillis());
                ac.setStartTime(startT);

                List<Report_Gases> list_report_gases = new ReportGasDAO().getAll();
                
                for (Report_Gases list_report_gase : list_report_gases) {
                    double methane = list_report_gase.getPercentmethane();
                    double hydrogen = list_report_gase.getPercenthydrogen();
                    
                    if(methane>hydrogen){
                        int reportID = list_report_gase.getReport().getId();
                        int userId = list_report_gase.getReport().getUser().getId();
                        ActivityN ac2 = new ActivityN();
                        startT = new Timestamp(System.currentTimeMillis());
                        ac2.setStartTime(startT);
                        Timestamp endT = new Timestamp(System.currentTimeMillis());
                        ac2.setEndTime(endT);
                        ac2.setDescription("Alert Agent sent an alert to User "+ userId+ " to seek a medical assistance: methane > hydrogen. (Report Id" +reportID+")");
                        new ActivityDAO().save(ac2);
                    }
                }
                 //FProvW3c
                Timestamp endT = new Timestamp(System.currentTimeMillis());
                ac.setEndTime(endT);
                ac.setDescription("Alert Agent verified data from all patients to generate alerts.");
            new ActivityDAO().save(ac);
            }
        });

    }

}
