package beans;

import dao.EntityNDAO;
import dao.FoodDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.EntityN;
import model.Food;

/**
 *
 * @author Nathalia
 */
@ManagedBean
@ViewScoped
public class FoodBean implements Serializable {

    @PostConstruct
    public void init() {
        this.foods = new FoodDAO().getAll();
    }

    private List<Food> foods;

    String name;
    String portuguesename;
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

    public String getPortuguesename() {
        return portuguesename;
    }

    public void setPortuguesename(String portuguesename) {
        this.portuguesename = portuguesename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void buttonAction(ActionEvent actionEvent) {
        Food food = new Food();
        food.setName(this.name);
        food.setPortuguesename(portuguesename);
        new FoodDAO().save(food);
        this.id = food.getId();
        
        //FProvW3C
         EntityN en = new EntityN();
        en.setName(food.getName());
        new EntityNDAO().save(en);
        
        System.out.println("Register Food made");
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Food id: " + this.id));
    }

}
