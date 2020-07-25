package com.hersa.app.faces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
@RequestScoped
public class MenuView {
 
    private MenuModel model;
 
    @PostConstruct
    public void init() {
    	
        model = new DefaultMenuModel();
        
        DefaultMenuItem  item = DefaultMenuItem.builder().value("Add Reading").icon("fa fa-gittip").outcome("addreading").build();
        
        model.getElements().add(item);
        
        item = DefaultMenuItem.builder().value("Readings").icon("fa fa-heart").outcome("readings").build();
        
        model.getElements().add(item);
        
        item = DefaultMenuItem.builder().value("Dashboard").icon("fa fa-dashboard").outcome("dashboard").build();
        
        model.getElements().add(item);
    
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                .label("Tools").icon("fa fa-cog")
                .build();
 
        item = DefaultMenuItem.builder()
                .value("API Manager")
                .outcome("apitool")
                .icon("fa fa-paper-plane")
                .build();
        
        firstSubmenu.getElements().add(item);
        
        model.getElements().add(firstSubmenu);
        
        item = DefaultMenuItem.builder().value("Logout").icon("fa fa-sign-out").outcome("login").build();
        
        model.getElements().add(item);
    }
 
    public MenuModel getModel() {
        return model;
    }
 
    public void save() {
        addMessage("Success", "Data saved");
    }
 
    public void update() {
        addMessage("Success", "Data updated");
    }
 
    public void delete() {
        addMessage("Success", "Data deleted");
    }
 
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
