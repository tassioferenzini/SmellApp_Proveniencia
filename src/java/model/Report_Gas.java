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
 * 
 * PARA SIMPLIFICAR, ESTOU USANDO A TABELA REPORT_GASES
 */
@Entity
@Table(name = "report_gas")
public class Report_Gas {

    public Report_Gas() {

    }

    public Report_Gas(Report report, Gas gas) {
        this.report = report;
        this.gas = gas;
    }
    //@Id
    //@GeneratedValue
    int id;

    private Report report;
    private Gas gas;

    // additional fields
    private double value, percentchange;
    private String description, source;
    private int number_measurements;
    private int count_time;

    private double humidity, temperature, value_environment;

    @Id
    @GeneratedValue
    @Column(name = "report_gas_id")
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
    @JoinColumn(name = "gas_id")
    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber_measurements() {
        return number_measurements;
    }

    public void setNumber_measurements(int number_measurements) {
        this.number_measurements = number_measurements;
    }

    public int getCount_time() {
        return count_time;
    }

    public void setCount_time(int count_time) {
        this.count_time = count_time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getValue_environment() {
        return value_environment;
    }

    public void setValue_environment(double value_environment) {
        this.value_environment = value_environment;
    }

    public double getPercentchange() {
        return percentchange;
    }

    public void setPercentchange(double percentchange) {
        this.percentchange = percentchange;
    }
    
    

}
