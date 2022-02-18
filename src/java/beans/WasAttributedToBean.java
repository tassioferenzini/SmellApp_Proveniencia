/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.WasAttributedToDAO;
import PROV.DM.WasAttributedTo;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


/**
 *
 * @author tassio
 */
@ManagedBean(name = "wasAttributedToBean")
@ViewScoped
public class WasAttributedToBean {

    WasAttributedTo wasAttributedTo = new WasAttributedTo();

    List wasAttributedTos = new ArrayList();

    //construtor
    public WasAttributedToBean() {
        wasAttributedTos = new WasAttributedToDAO().getAll();
        wasAttributedTo = new WasAttributedTo();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new WasAttributedToDAO().save(wasAttributedTo);
        wasAttributedTos = new WasAttributedToDAO().getAll();
        wasAttributedTo = new WasAttributedTo();

    }

    public void exclude(ActionEvent actionEvent) {
        new WasAttributedToDAO().deleteWasAttributedTo(wasAttributedTo);
        wasAttributedTos = new WasAttributedToDAO().getAll();
        wasAttributedTo = new WasAttributedTo();
    }
    
   public void remove(int wasAttributedToId) {
     try {
        new WasAttributedToDAO().deleteWasAttributedTo(wasAttributedToId);
        wasAttributedTos = new WasAttributedToDAO().getAll();
        wasAttributedTo = new WasAttributedTo();
        } catch (Exception e) {
        e.printStackTrace();
     }
    }

    //getters and setters
    public WasAttributedTo getWasAttributedTo() {
        return wasAttributedTo;
    }

    public void setWasAttributedTo(WasAttributedTo wasAttributedTo) {
        this.wasAttributedTo = wasAttributedTo;
    }

    public List getWasAttributedTos() {
        return wasAttributedTos;
    }

    public void setWasAttributedTos(List wasAttributedTos) {
        this.wasAttributedTos = wasAttributedTos;
    }

 

}
