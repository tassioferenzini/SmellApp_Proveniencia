/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ActivityDAO;
import dao.AgentDAO;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/*import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;*/
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import model.ActivityN;
import model.AgentP;
/*import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;*/

/**
 *
 * @author tassio
 */
@ManagedBean(name = "agentBean")
@ViewScoped
public class AgentBean {

    AgentP agent = new AgentP();

    List agents = new ArrayList();

    //construtor
    public AgentBean() {
        agents = new AgentDAO().getAll();
        agent = new AgentP();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new AgentDAO().save(agent);
        agents = new AgentDAO().getAll();
        agent = new AgentP();

    }

    public void exclude(ActionEvent actionEvent) {
        new AgentDAO().deleteAgent(agent);
        agents = new AgentDAO().getAll();
        agent = new AgentP();
    }
    
    public void remove(int agentId) {
       try {
        new AgentDAO().deleteAgent(agentId);
        agents = new AgentDAO().getAll();
        agent = new AgentP();
        } catch (Exception e) {
        e.printStackTrace();
     }
    }

    //getters and setters
    public AgentP getAgent() {
        return agent;
    }

    public void setAgent(AgentP agent) {
        this.agent = agent;
    }


    public List getAgents() {
        return agents;
    }

    public void setAgents(List agents) {
        this.agents = agents;
    }

  /*  public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";

        // pdf.add(Image.getInstance(logo));
    }*/

}
