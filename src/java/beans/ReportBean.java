package beans;

import PROV.DM.ActedOnBehalfOf;
import model.ActivityN;
import PROV.DM.Agent;
import PROV.DM.Used;
import PROV.DM.WasAssociatedWith;
import PROV.DM.WasAttributedTo;
import PROV.DM.WasGeneratedBy;
import control.Fila;
import control.GasSmell;
import control.Measurement;
import dao.ActedOnBehalfOfDAO;
import dao.ActivityDAO;
import dao.AgentDAO;
import dao.EntityNDAO;
import dao.FoodDAO;
import dao.GasDAO;
import dao.ReportDAO;
import dao.ReportFoodDAO;
import dao.ReportGasDAO;
import dao.ReportSymptomDAO;
import dao.SymptomDAO;
import dao.UsedDAO;
import dao.UserDAO;
import dao.WasAssociatedWithDAO;
import dao.WasAttributedToDAO;
import dao.WasGeneratedByDAO;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.AgentP;
import model.EntityN;
import model.Food;
import model.Gas;
import model.Report;
import model.Report_Food;
import model.Report_Gases;
import model.Report_Symptom;
import model.Symptom;
import model.User;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Nathalia
 */
@ManagedBean
@ViewScoped
public class ReportBean implements Serializable {
    
    @PostConstruct
    public void init() {
        this.reports = new ReportDAO().getAll();
        //gasSmell = new GasSmell();
    }

    // GasSmell gasSmell;
    private boolean skip;
    private List<Report> reports;
    
    private Integer[] selectedSymptoms;
    private Integer[] selectedFoods;
    
    int userId;
    int id;
    
    ActivityN ac;
    EntityN en;
    AgentP ag, agDevice;
    
    double percentM = 0, percentH = 0, percentA = 0, percentC = 0;
    
    private String message;
    
    boolean gasButtonPressed = true;
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public List<Report> getReports() {
        return reports;
    }
    
    public Integer[] getSelectedSymptoms() {
        return selectedSymptoms;
    }
    
    public void setSelectedSymptoms(Integer[] selectedSymptoms) {
        this.selectedSymptoms = selectedSymptoms;
    }
    
    public Integer[] getSelectedFoods() {
        return selectedFoods;
    }
    
    public void setSelectedFoods(Integer[] selectedFoods) {
        this.selectedFoods = selectedFoods;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        
        User user = new UserDAO().getUser(userId);
        Report report = new Report(user);
        new ReportDAO().save(report);
        this.id = report.getId();
        //System.out.println("Register Report made");
        for (int i = 0; i < this.selectedSymptoms.length; i++) {
            Symptom symptom = new SymptomDAO().getSymptom(this.selectedSymptoms[i]);
            Report_Symptom rsy = new Report_Symptom(report, symptom);
            
            new ReportSymptomDAO().save(rsy);

            //FProvW3C
            en = new EntityNDAO().getEntityN(this.selectedSymptoms[i]);
            ag = new AgentDAO().getAgent(user.getId());
            WasAttributedTo wat = new WasAttributedTo();
            wat.setEntity(en);
            wat.setAgent(ag);
            new WasAttributedToDAO().save(wat);
            
        }
        for (int i = 0; i < this.selectedFoods.length; i++) {
            Food food = new FoodDAO().getFood(this.selectedFoods[i]);
            Report_Food rsf = new Report_Food(report, food);
            
            new ReportFoodDAO().save(rsf);

            //FProvW3C
            en = new EntityNDAO().getEntityN(this.selectedFoods[i]);
            ag = new AgentDAO().getAgent(user.getId());
            WasAttributedTo wat = new WasAttributedTo();
            wat.setEntity(en);
            wat.setAgent(ag);
            new WasAttributedToDAO().save(wat);
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Successful", "Report id: " + this.id));
    }
    
    public void buttonActionSaveSmell(ActionEvent actionEvent) {
        //Food food =  new FoodDAO().getFood(this.selectedFoods[i]);
        //   Report_Food rsf = new Report_Food(report, food);
    }
    
    public void buttonActionStartSmell(ActionEvent actionEvent) {
        System.out.println("Apertei action start");
        System.out.println(gasButtonPressed);
        if (gasButtonPressed) {
            gasButtonPressed = false;

            // gasSmell = GasSmell.getInstance();
            
            
        
             // FProvW3C
            Timestamp startT = new Timestamp(System.currentTimeMillis());
            ac = new ActivityN();
            ac.setStartTime(startT);
       
            
            GasSmell.getInstance().reinit();
            // FProvW3C
            Timestamp endT = new Timestamp(System.currentTimeMillis());
            ac.setEndTime(endT);
//            a1.setDescription("A Gases Device was connected to the system");
            ac.setDescription("User " + this.getUserId()+ "has connected a Gases Device on port "+GasSmell.getInstance().getConnectedPortName());

            new ActivityDAO().save(ac);
            
            agDevice = new AgentP();
           // agDevice.setIdUser(user.getId());
            agDevice.setTypeAgent("Software Agent - Gases Device");
            new AgentDAO().save(agDevice);
            
            /*ActedOnBehalfOf act = new ActedOnBehalfOf();
            act.setAgent(ag);
            act.setAgent1(agDevice);
            act.setActivity(ac);
            new ActedOnBehalfOfDAO().save(act);*/
            
           /* en = new EntityN();
            en.setName("Gases Device");
            new EntityNDAO().save(en);*/
            
         /*   WasAssociatedWith waw = new WasAssociatedWith();
            waw.setAgent(ag);
            ac = new ActivityDAO().getActivity(ac.getIdActivity());
            waw.setActivity(ac);
            new WasAssociatedWithDAO().save(waw);*/
            
            /*
            Used used = new Used();
            used.setActivity(ac);
            used.setEntity(en);
            used.setTimeUsed(startT);
            new UsedDAO().save(used);*/

            
           
       
            
            
            //  boolean initDevice = gasSmell.initialize();

            // if(initDevice){
            GasSmell.getInstance().setStartReadGasEnvironment(true);
            GasSmell.getInstance().setStartReadGasPerson(false);

            //  FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_WARN, actionEvent.getComponent().getId() + " toggled", "WAIT!");
            // FacesContext.getCurrentInstance().addMessage(null, message2);
             // FProvW3C
            startT = new Timestamp(System.currentTimeMillis());
            ac = new ActivityN();
            ac.setStartTime(startT);
       

            while (GasSmell.getInstance().getEnvironmentQueue().size() < 10) {
                
            }
                 
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, actionEvent.getComponent().getId() + " toggled", "GO!");
            FacesContext.getCurrentInstance().addMessage(null, message);
//                
            GasSmell.getInstance().setStartReadGasEnvironment(false);
            GasSmell.getInstance().setStartReadGasPerson(true);
            
            // FProvW3C
            endT = new Timestamp(System.currentTimeMillis());
            ac.setEndTime(endT);
//            a1.setDescription("A Gases Device was connected to the system");
            ac.setDescription("The Gases Device Agent connected on port " +GasSmell.getInstance().getConnectedPortName()+ " measured environmental gases "+ GasSmell.getInstance().getEnvironmentQueue().size()+ " times");

            new ActivityDAO().save(ac);
            
           /* waw = new WasAssociatedWith();
            waw.setAgent(agDevice);
            ac = new ActivityDAO().getActivity(ac.getIdActivity());
            waw.setActivity(ac);
            new WasAssociatedWithDAO().save(waw);*/
           /* used = new Used();
            used.setActivity(ac);
            used.setEntity(en);
            used.setTimeUsed(startT);
            new UsedDAO().save(used);*/
           /* WasGeneratedBy wgb = new WasGeneratedBy();
            wgb.setActivity(ac);
            wgb.setEntity(en);
            wgb.setTimeWasGeneratedBy(startT);
            new WasGeneratedByDAO().save(wgb);*/
            
            
            // FProvW3C
            //Measure human gases
            startT = new Timestamp(System.currentTimeMillis());
            ac = new ActivityN();
            ac.setStartTime(startT);
            
            System.out.println("Size eh ");
            System.out.println(GasSmell.getInstance().getEnvironmentQueue().size());
            System.out.println("Size 2eh ");
            System.out.println(GasSmell.getInstance().getPersonQueue().size());

            //FProvW3c
           // Timestamp startTime = new Timestamp(System.currentTimeMillis());
           // ac = new ActivityN();
           // ac.setStartTime(startTime);
        //    new ActivityDAO().save(ac);
      /*      waw = new WasAssociatedWith();
            waw.setAgent(ag);
            ac = new ActivityDAO().getActivity(ac.getIdActivity());
            waw.setActivity(ac);
            new WasAssociatedWithDAO().save(waw);
            Used used = new Used();
            used.setActivity(ac);
            used.setEntity(en);
            used.setTimeUsed(startTime);
            new UsedDAO().save(used);
            WasGeneratedBy wgb = new WasGeneratedBy();
            wgb.setActivity(ac);
            wgb.setEntity(en);
            wgb.setTimeWasGeneratedBy(startTime);
            new WasGeneratedByDAO().save(wgb);*/

            // wait(20);
            // }
//           else{
//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, actionEvent.getComponent().getId() + " toggled", "You don't have a device connected!");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//                gasButtonPressed = true;
//                
//                //this.percentM = 100;
//                //this.percentA = 100;
//                //this.percentC = 100;
//                //this.percentH = 100;
//           }
        }
    }
    
    public void buttonActionStopSmell(ActionEvent actionEvent) {
        if (!gasButtonPressed && GasSmell.getInstance().getEnvironmentQueue() != null) {
            GasSmell.getInstance().setStartReadGasEnvironment(false);
            GasSmell.getInstance().setStartReadGasPerson(false);
            
             // FProvW3C
            Timestamp endT = new Timestamp(System.currentTimeMillis());
            ac.setEndTime(endT);
            ac.setDescription("The Gases Device Agent connected on port " +GasSmell.getInstance().getConnectedPortName()+ " measured gases from User"+ this.getUserId()+ " "+GasSmell.getInstance().getPersonQueue().size()+ " times");
            new ActivityDAO().save(ac);
            
         /*   WasAssociatedWith waw = new WasAssociatedWith();
            waw.setAgent(agDevice);
            ac = new ActivityDAO().getActivity(ac.getIdActivity());
            waw.setActivity(ac);
            new WasAssociatedWithDAO().save(waw);*/
          /*  Used used = new Used();
            used.setActivity(ac);
            used.setEntity(en);
            used.setTimeUsed(ac.getStartTime());
            new UsedDAO().save(used);*/
           /* WasGeneratedBy wgb = new WasGeneratedBy();
            wgb.setActivity(ac);
            wgb.setEntity(en);
            wgb.setTimeWasGeneratedBy(ac.getStartTime());
            new WasGeneratedByDAO().save(wgb);*/
            
            Timestamp startT = new Timestamp(System.currentTimeMillis());
            
            
            Fila<Measurement> environmentQueue = new Fila<Measurement>();
            Fila<Measurement> personQueue = new Fila<Measurement>();
            double em = 0, eh = 0, ec = 0, ea = 0;
            double pm = 0, ph = 0, pc = 0, pa = 0;
            
            System.out.println("Coletei env " + GasSmell.getInstance().getEnvironmentQueue().size());
            System.out.println("Coletei person " + GasSmell.getInstance().getPersonQueue().size());
            int sizeP = GasSmell.getInstance().getPersonQueue().size();
            int sizeE = GasSmell.getInstance().getEnvironmentQueue().size();
            if (sizeP > 10) {
                sizeP = 10;
            }
            if (sizeE > 10) {
                sizeE = 10;
            }
            for (int cont = 0; cont < sizeP; cont++) {
                Measurement m2 = GasSmell.getInstance().getPersonQueue().removeLast();
                
                pm += m2.getCh4();
                ph += m2.getH2();
                pc += m2.getCo2();
                pa += m2.getAlcohol();
            }
            
            for (int cont = 0; cont < sizeE; cont++) {
                Measurement m1 = GasSmell.getInstance().getEnvironmentQueue().removeLast();
                
                em += m1.getCh4();
                eh += m1.getH2();
                ec += m1.getCo2();
                ea += m1.getAlcohol();
            }

            //calc averages
            em = (em / sizeE);
            eh = (eh / sizeE);
            ec = (ec / sizeE);
            ea = (ea / sizeE);
            
            System.out.println("em: " + em);
            System.out.println("eh: " + eh);
            System.out.println("ec: " + ec);
            System.out.println("ea: " + ea);
            
            pm = (pm / sizeP);
            ph = (ph / sizeP);
            pc = (pc / sizeP);
            pa = (pa / sizeP);
            
            System.out.println("pm: " + pm);
            System.out.println("ph: " + ph);
            System.out.println("pc: " + pc);
            System.out.println("pa: " + pa);
            
            //((pm-em)/em)*100;
            
            this.percentM = (((pm-em)/em)*100);//((100 * pm) / em) - 100;
            this.percentA = ((pa-ea)/ea)*100;//((100 * pa) / ea) - 100;
            this.percentC = ((pc-ec)/ec)*100;//((100 * pc) / ec) - 100;
            this.percentH = ((ph-eh)/eh)*100;//((100 * ph) / eh) - 100;
            
            System.out.println("M " + this.percentM);
            System.out.println("A " + this.percentA);
            System.out.println("C " + this.percentC);
            System.out.println("H " + this.percentH);
            
            gasButtonPressed = true;
            GasSmell.getInstance().reinit();
            
            
            Report report = new ReportDAO().getReport(this.getId());
            
            Report_Gases regas = new Report_Gases(report);
            regas.setAlcohol(pa);
            regas.setCo2(pc);
            regas.setHydrogen(ph);
            regas.setMethane(pm);
            regas.setEnvhydrogen(eh);
            regas.setEnvalcohol(ea);
            regas.setEnvco2(ec);
            regas.setEnvmethane(em);
            regas.setPercentmethane(percentM);
            regas.setPercentalcohol(percentA);
            regas.setPercentco2(percentC);
            regas.setPercenthydrogen(percentH);
            regas.setNumber_measurements_person(sizeP);
            regas.setNumber_measurements_env(sizeE);
            new ReportGasDAO().save(regas);
            
            
           /* en = new EntityN();
            en.setName("The percentage of change of methane");
            new EntityNDAO().save(en);*/

            //FProvW3c
            agDevice = new AgentP();
           // agDevice.setIdUser(user.getId());
            agDevice.setTypeAgent("Software Agent - Analyzer");
            new AgentDAO().save(agDevice);
            
            ac = new ActivityN();
            ac.setStartTime(startT);
            endT = new Timestamp(System.currentTimeMillis());
            ac.setEndTime(endT);
            ac.setDescription("Analyzer Agent calculated the percentage of change from environmental gases to the gases exhaled by the User: "+ this.getUserId()+ ": "+ 
                    "1. Methane: "+String.format("%.2f", this.percentM)+ "%  2. Hydrogen: "+ String.format("%.2f", this.percentH)+ "%  3. Alcohol: "+ String.format("%.2f", this.percentA)+ "%  4. CO2: "+ String.format("%.2f", this.percentC)+ " %");
            new ActivityDAO().save(ac);
            

          /*  waw = new WasAssociatedWith();
            waw.setAgent(agDevice);
            ac = new ActivityDAO().getActivity(ac.getIdActivity());
            waw.setActivity(ac);
            new WasAssociatedWithDAO().save(waw);*/
            
           /* Timestamp endTime = new Timestamp(System.currentTimeMillis());
            ac.setEndTime(endTime);
            new ActivityDAO().save(ac);*/

            //gasSmell.close();
            //   Map<String,Object> options = new HashMap<String, Object>();
            //  options.put("resizable", false);
            //  RequestContext.getCurrentInstance().openDialog("viewgases", options, null);
            this.showMessage();
        }
    }
    
    public void showMessage() {
        String text = "Methane: " + String.format("%.2f", this.percentM) + "% - ";
        text += "Alcohol: " + String.format("%.2f", this.percentA) + "% - ";
        text += "Carbon Dioxide: " + String.format("%.2f", this.percentC) + "% - ";
        text += "Hydrogen: " + String.format("%.2f", this.percentH) + "% .";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Percentage Change", text);
        
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public double getPercentM() {
        return percentM;
    }
    
    public void setPercentM(double percentM) {
        this.percentM = percentM;
    }
    
    public double getPercentH() {
        return percentH;
    }
    
    public void setPercentH(double percentH) {
        this.percentH = percentH;
    }
    
    public double getPercentA() {
        return percentA;
    }
    
    public void setPercentA(double percentA) {
        this.percentA = percentA;
    }
    
    public double getPercentC() {
        return percentC;
    }
    
    public void setPercentC(double percentC) {
        this.percentC = percentC;
    }
    
    public void onClose(CloseEvent event) {
        // FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        //  FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onToggle(ToggleEvent event) {
        //   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        //  FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
