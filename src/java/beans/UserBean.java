package beans;

import PROV.DM.WasAttributedTo;
import dao.AgentDAO;
import dao.DiseaseDAO;
import dao.EntityNDAO;
import dao.UserDAO;
import dao.UserDiseaseDAO;
import dao.WasAttributedToDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.AgentP;
import model.Disease;
import model.EntityN;
import model.User;
import model.User_Disease;

/**
 *
 * @author Nathalia
 */
@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    @PostConstruct
    public void init() {
        this.users = new UserDAO().getAll();
    }

    private List<User> users;

    private Integer[] selectedDiseases;

    String name;
    String gender;
    int age, frequency_exercise;
    double weight, height;
    int id;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
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

    public int getFrequency_exercise() {
        return frequency_exercise;
    }

    public void setFrequency_exercise(int frequency_exercise) {
        this.frequency_exercise = frequency_exercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Integer[] getSelectedDiseases() {
        return selectedDiseases;
    }

    public void setSelectedDiseases(Integer[] selectedDiseases) {
        this.selectedDiseases = selectedDiseases;
    }

    public void buttonAction(ActionEvent actionEvent) {

        //  System.out.println("DISEASEEEE"+this.selectedDiseases[0]);
        User user = new User();
        user.setName(this.name);
        user.setAge(age);
        user.setGender(gender);
        user.setWeight(weight);
        user.setHeight(height);
        user.setFrequency_exercise(frequency_exercise);
        new UserDAO().save(user);
        this.id = user.getId();
        System.out.println("Register User made");

        // FProvW3C
        AgentP ag = new AgentP();
        ag.setIdUser(user.getId());
        ag.setTypeAgent("Person");
        new AgentDAO().save(ag);

        for (int i = 0; i < this.selectedDiseases.length; i++) {
            Disease disease = new DiseaseDAO().getDisease(this.selectedDiseases[i]);
            User_Disease usd = new User_Disease(user, disease);

            new UserDiseaseDAO().save(usd);

            // FProvW3C
            EntityN ed = new EntityN();
            ed.setName(disease.getName());
            new EntityNDAO().save(ed);
            WasAttributedTo wat = new WasAttributedTo();
            wat.setAgent(ag);
            wat.setEntity(ed);
            new WasAttributedToDAO().save(wat);

        }

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "User id: " + this.id));
    }

}
