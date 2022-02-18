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
@Table(name = "report_symptom")
public class Report_Symptom {

    public Report_Symptom() {

    }

    public Report_Symptom(Report report, Symptom symptom) {
        this.report = report;
        this.symptom = symptom;
    }
    //@Id
    //@GeneratedValue
    int id;

    private Report report;
    private Symptom symptom;

    @Id
    @GeneratedValue
    @Column(name = "report_symptom_id")
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
    @JoinColumn(name = "symptom_id")
    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

}
