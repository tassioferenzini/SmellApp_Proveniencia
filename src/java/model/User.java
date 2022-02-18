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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class User {

    @Column
    String name;
    @Column
    String gender;
    @Column
    int age;
    @Column
    double weight;
    @Column
    int frequency_exercise;
    @Column
    double height;

    @Id
    @GeneratedValue
    int id;

    @OneToMany(mappedBy = "user", targetEntity = Report.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Report> reports;

    @OneToMany(mappedBy = "user", targetEntity = User_Disease.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User_Disease> user_disease_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getFrequency_exercise() {
        return frequency_exercise;
    }

    public void setFrequency_exercise(int frequency_exercise) {
        this.frequency_exercise = frequency_exercise;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<User_Disease> getUser_disease_list() {
        return user_disease_list;
    }

    public void setUser_disease_list(List<User_Disease> user_disease_list) {
        this.user_disease_list = user_disease_list;
    }

}
