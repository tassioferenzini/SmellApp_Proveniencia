/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nathalianascimento
 */
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table
public class Report {

    public Report() {

    }

    public Report(User user) {
        this.user = user;
        // this.date = date;
    }
    // @Column
    @Column(name = "date")
    // @Temporal(TemporalType.DATE)
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;

    // @Column
    // Time time;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    @OneToMany(mappedBy = "report", targetEntity = Report_Food.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Report_Food> report_food_list;

    @OneToMany(mappedBy = "report", targetEntity = Report_Gases.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Report_Gases> report_gas_list;

    @OneToMany(mappedBy = "report", targetEntity = Report_Symptom.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Report_Symptom> report_symptom_list;

    Timestamp timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Report_Food> getReport_food_list() {
        return report_food_list;
    }

    public void setReport_food_list(List<Report_Food> report_food_list) {
        this.report_food_list = report_food_list;
    }

    public List<Report_Gases> getReport_gas_list() {
        return report_gas_list;
    }

    public void setReport_gas_list(List<Report_Gases> report_gas_list) {
        this.report_gas_list = report_gas_list;
    }

    public List<Report_Symptom> getReport_symptom_list() {
        return report_symptom_list;
    }

    public void setReport_symptom_list(List<Report_Symptom> report_symptom_list) {
        this.report_symptom_list = report_symptom_list;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
