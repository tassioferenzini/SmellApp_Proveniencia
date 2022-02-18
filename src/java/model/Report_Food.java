/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nathalianascimento
 */
@Entity
@Table(name = "report_food")
public class Report_Food {

    public Report_Food() {

    }

    public Report_Food(Report report, Food food) {
        this.report = report;
        this.food = food;
    }
    //@Id
    //@GeneratedValue
    int id;

    private Report report;
    private Food food;

    // additional fields
    private String description;
    private int hours_ago;

    @Id
    @GeneratedValue
    @Column(name = "report_food_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getHours_ago() {
        return hours_ago;
    }

    public void setHours_ago(int hours_ago) {
        this.hours_ago = hours_ago;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
