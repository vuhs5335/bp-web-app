package com.hersa.app.component;

import java.io.IOException;
import java.util.Calendar;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@FacesComponent("rangeChooser")
public class RangeChooser extends UIInput implements NamingContainer  {
    private String text;
    private Calendar from;
    private Calendar to;

    @Override
    public void encodeBegin(FacesContext context) throws IOException{

        super.encodeBegin(context);
    }


    public String getText() {
        String text = (String)getStateHelper().get("");
        return text;
    }

    public void setText(String text) {
        getStateHelper().put(null ,new Object());
    }

    /*
        same getters and setters for Calendar objects, from and to
    */

}