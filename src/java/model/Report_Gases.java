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
@Table(name = "report_gases")
public class Report_Gases {

    public Report_Gases() {

    }

    public Report_Gases(Report report) {
        this.report = report;
    }
    //@Id
    //@GeneratedValue
    int id;

    private Report report;

    // additional fields
    private double methane, hydrogen, alcohol, co2;
    private double percentmethane, percenthydrogen, percentalcohol, percentco2;
    private double envmethane, envhydrogen, envalcohol, envco2;
    private int number_measurements_env, number_measurements_person;

    private double humidity, temperature;

    @Id
    @GeneratedValue
    @Column(name = "report_gases_id")
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

    public double getMethane() {
        return methane;
    }

    public void setMethane(double methane) {
        this.methane = methane;
    }

    public double getHydrogen() {
        return hydrogen;
    }

    public void setHydrogen(double hydrogen) {
        this.hydrogen = hydrogen;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getPercentmethane() {
        return percentmethane;
    }

    public void setPercentmethane(double percentmethane) {
        this.percentmethane = percentmethane;
    }

    public double getPercenthydrogen() {
        return percenthydrogen;
    }

    public void setPercenthydrogen(double percenthydrogen) {
        this.percenthydrogen = percenthydrogen;
    }

    public double getPercentalcohol() {
        return percentalcohol;
    }

    public void setPercentalcohol(double percentalcohol) {
        this.percentalcohol = percentalcohol;
    }

    public double getPercentco2() {
        return percentco2;
    }

    public void setPercentco2(double percentco2) {
        this.percentco2 = percentco2;
    }

    public double getEnvmethane() {
        return envmethane;
    }

    public void setEnvmethane(double envmethane) {
        this.envmethane = envmethane;
    }

    public double getEnvhydrogen() {
        return envhydrogen;
    }

    public void setEnvhydrogen(double envhydrogen) {
        this.envhydrogen = envhydrogen;
    }

    public double getEnvalcohol() {
        return envalcohol;
    }

    public void setEnvalcohol(double envalcohol) {
        this.envalcohol = envalcohol;
    }

    public double getEnvco2() {
        return envco2;
    }

    public void setEnvco2(double envco2) {
        this.envco2 = envco2;
    }

    public int getNumber_measurements_env() {
        return number_measurements_env;
    }

    public void setNumber_measurements_env(int number_measurements_env) {
        this.number_measurements_env = number_measurements_env;
    }

    public int getNumber_measurements_person() {
        return number_measurements_person;
    }

    public void setNumber_measurements_person(int number_measurements_person) {
        this.number_measurements_person = number_measurements_person;
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

   

}
