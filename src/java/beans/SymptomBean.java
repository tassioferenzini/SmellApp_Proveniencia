package beans;

import dao.EntityNDAO;
import dao.SymptomDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.EntityN;
import model.Symptom;

/**
 *
 * @author Nathalia
 */
@ManagedBean
@ViewScoped
public class SymptomBean implements Serializable {

    @PostConstruct
    public void init() {
        this.symptoms = new SymptomDAO().getAll();
    }

    private List<Symptom> symptoms;

    String name;
    String portugusename;
    String description;
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

    public String getPortugusename() {
        return portugusename;
    }

    public void setPortugusename(String portugusename) {
        this.portugusename = portugusename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void buttonAction(ActionEvent actionEvent) {
        Symptom symptom = new Symptom();
        symptom.setName(this.name);
        symptom.setPortugusename(portugusename);
        symptom.setDescription(this.description);
        new SymptomDAO().save(symptom);
        this.id = symptom.getId();

        //FProvW3C
        EntityN eN = new EntityN();
        eN.setName(symptom.getName());
        new EntityNDAO().save(eN);

        System.out.println("Register Symptom made");

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Symptom id: " + this.id));
    }

}
