/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import PROV.DM.Activity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author nathalianascimento
 */
@Entity
@Table(name = "Activity")
public class ActivityN extends Activity{

    @Column
    private String description;

    public ActivityN() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityN(String description, Date startTime, Date endTime, Integer idActivity) {
        super(idActivity);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.description = description;
    }

}