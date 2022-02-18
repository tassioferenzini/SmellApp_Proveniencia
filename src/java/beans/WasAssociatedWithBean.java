/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.WasAssociatedWithDAO;
import PROV.DM.WasAssociatedWith;
import dao.ActivityDAO;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import java.util.List;
import javax.faces.bean.ManagedBean;
import model.ActivityN;


/**
 *
 * @author tassio
 */
@ManagedBean(name = "wasAssociatedWithBean")
@ViewScoped
public class WasAssociatedWithBean {

    WasAssociatedWith wasAssociatedWith = new WasAssociatedWith();

    List wasAssociatedWiths = new ArrayList();

    //construtor
    public WasAssociatedWithBean() {
        wasAssociatedWiths = new WasAssociatedWithDAO().getAll();
        wasAssociatedWith = new WasAssociatedWith();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new WasAssociatedWithDAO().save(wasAssociatedWith);
        wasAssociatedWiths = new WasAssociatedWithDAO().getAll();
        wasAssociatedWith = new WasAssociatedWith();

    }

    public void exclude(ActionEvent actionEvent) {
        new WasAssociatedWithDAO().deleteWasAssociatedWith(wasAssociatedWith);
        wasAssociatedWiths = new WasAssociatedWithDAO().getAll();
        wasAssociatedWith = new WasAssociatedWith();
    }
    
     public void remove(int wasAssociatedWithId) {
     try {
        new WasAssociatedWithDAO().deleteWasAssociatedWith(wasAssociatedWithId);
        wasAssociatedWiths = new WasAssociatedWithDAO().getAll();
        wasAssociatedWith = new WasAssociatedWith();
        } catch (Exception e) {
        e.printStackTrace();
     }
    }

    //getters and setters
    public WasAssociatedWith getWasAssociatedWith() {
        return wasAssociatedWith;
    }

    public void setWasAssociatedWith(WasAssociatedWith wasAssociatedWith) {
        this.wasAssociatedWith = wasAssociatedWith;
    }

    public WasAssociatedWith getAgent() {
        return wasAssociatedWith;
    }

    public void setAgent(WasAssociatedWith wasAssociatedWith) {
        this.wasAssociatedWith = wasAssociatedWith;
    }

    public List getAgents() {
        return wasAssociatedWiths;
    }

    public void setAgents(List wasAssociatedWiths) {
        this.wasAssociatedWiths = wasAssociatedWiths;
    }


}
