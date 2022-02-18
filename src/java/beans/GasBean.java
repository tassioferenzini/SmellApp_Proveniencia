package beans;

import dao.EntityNDAO;
import dao.GasDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.EntityN;
import model.Gas;

/**
 *
 * @author Nathalia
 */
@ManagedBean
@ViewScoped
public class GasBean implements Serializable {

    @PostConstruct
    public void init() {
        this.gass = new GasDAO().getAll();
    }

    private List<Gas> gass;

    String name;
    String formula;
    String sensor;
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

    public List<Gas> getGass() {
        return gass;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public void buttonAction(ActionEvent actionEvent) {
        Gas gas = new Gas();
        gas.setName(this.name);
        gas.setFormula(this.formula);
        gas.setSensor(this.sensor);
        new GasDAO().save(gas);
        this.id = gas.getId();
        
        //FProvW3c
        EntityN en = new EntityN();
        en.setName(gas.getName());
        new EntityNDAO().save(en);
        
        System.out.println("Register Gas made");

        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Gas id: " + this.id));
    }

}
