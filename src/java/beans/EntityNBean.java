/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.EntityNDAO;
import model.EntityN;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import java.util.List;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author tassio
 */
@ManagedBean(name = "entityBean")
@ViewScoped
public class EntityNBean {

    EntityN entity = new EntityN();

    List entitys = new ArrayList();

    //construtor
    public EntityNBean() {
        entitys = new EntityNDAO().getAll();
        entity = new EntityN();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new EntityNDAO().save(entity);
        entitys = new EntityNDAO().getAll();
        entity = new EntityN();

    }

    public void exclude(ActionEvent actionEvent) {
       // new EntityNDAO().deleteEntityN(entity);
        entitys = new EntityNDAO().getAll();
        entity = new EntityN();
    }
    
    public void remove(int entityId) {
       try {
        new EntityNDAO().deleteEntityDisease(entityId);
        entitys = new EntityNDAO().getAll();
        entity = new EntityN();
        } catch (Exception e) {
        e.printStackTrace();
     }
    }

    //getters and setters
    public EntityN getEntityN() {
        return entity;
    }

    public void setEntityN(EntityN entity) {
        this.entity = entity;
    }

    public EntityN getEntity() {
        return entity;
    }

    public void setEntity(EntityN entity) {
        this.entity = entity;
    }

    public List getEntitys() {
        return entitys;
    }

    public void setEntitys(List entitys) {
        this.entitys = entitys;
    }

   
}
