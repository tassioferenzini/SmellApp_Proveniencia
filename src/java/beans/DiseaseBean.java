package beans;

import dao.DiseaseDAO;
import dao.EntityNDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Disease;
import model.EntityN;

/**
 *
 * @author Nathalia
 */
@ManagedBean
@ViewScoped
public class DiseaseBean implements Serializable {

    @PostConstruct
    public void init() {
        this.diseases = new DiseaseDAO().getAll();
    }

    private List<Disease> diseases;

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

    public List<Disease> getDiseases() {
        return diseases;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void buttonAction(ActionEvent actionEvent) {
        Disease disease = new Disease();
        disease.setName(this.name);
        disease.setPortugusename(this.portugusename);
        disease.setDescription(this.description);
        new DiseaseDAO().save(disease);
        this.id = disease.getId();

        //FProvW3C
        EntityN en = new EntityN();
        en.setName(disease.getName());
        new EntityNDAO().save(en);

        System.out.println("Register Disease made");

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Disease id: " + this.id));
    }

}
