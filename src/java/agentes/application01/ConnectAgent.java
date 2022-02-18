/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes.application01;

import dao.AgentDAO;
import dao.FoodDAO;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import model.AgentP;
import model.Food;

/**
 *
 * @author Nathalia
 */
public class ConnectAgent extends Agent {

    int id;
    public ConnectAgent(int id) {
        super();
        this.id = id;
    }

    protected void setup() {
        AgentP ag = new AgentP();
        ag.setIdUser(this.id);
        ag.setTypeAgent("Software Agent - Connector");
        new AgentDAO().save(ag);

      /*  addBehaviour(new TickerBehaviour(this, 200) {
            @Override
            protected void onTick() {
                System.out.println("Estou vivo");
                Food food = new Food();
                food.setName("Tassio");
                food.setPortuguesename("Tassio2");
                new FoodDAO().save(food);
                int id = food.getId();
                System.out.println("Register Food made");
            }
        });*/

    }

}
