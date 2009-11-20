package org.mca.sinek.jsf;

import com.sun.el.ValueExpressionImpl;
import com.sun.faces.el.ELUtils;

import javax.faces.component.UIInput;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.event.*;
import javax.faces.application.ResourceDependency;
import javax.faces.context.FacesContext;
import javax.el.ValueExpression;
import java.util.List;
import java.util.Map;

@FacesComponent(value = "sinek")
@ListenerFor(systemEventClass = PostAddToViewEvent.class)
@ResourceDependency(name = "jsf.js", library = "javax.faces", target = "body")
public class UISinek extends UIInput {

    public UISinek() {
        setImmediate(true);
    }

    public WorkSheet getWorkSheet() {
        return (WorkSheet) getValue();
    }

    @Override
    public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
        Map<String, String> requestParameterMap = getFacesContext().getExternalContext()
              .getRequestParameterMap();
        String exec = requestParameterMap.get("javax.faces.source");
        if((getClientId()+ "_addr").equals(exec)) {
            getWorkSheet().addEmptyRow();
        } else if((getClientId()+ "_addc").equals(exec)) {
            getWorkSheet().addEmptyCol();
        }
        System.out.println(getClientId() + ":" + exec +"|");
        System.out.println(":" + requestParameterMap);
        int count = getChildCount();
        createRows();
    }

    private void createRows() {
        WorkSheet sheet = getWorkSheet();
        int rindex = 0;
        for (List<String> row : sheet.getSheet()) {
            createRow(row, rindex);
            rindex++;
        }
    }

    private void createRow(List<String> row, int rindex) {
        ValueExpression ve = this.getValueExpression("value");
        String exp = ve.getExpressionString();
        exp = exp.substring(0, exp.length() - 1);
        int cl = 0;
        for (String cell : row) {
            String id = getId() + "_" + rindex + "_" + cl;
            if(findComponent(id) != null) {
                continue;
            }
            HtmlInputText in = new HtmlInputText();
            ValueExpression el = ELUtils.createValueExpression(exp + ".sheet[" + rindex + "][" + cl + "]}");
            in.setId(id);
            in.setValueExpression("value", el);
            in.setLocalValueSet(true);
            in.setOnkeyup("jsf.ajax.request(this,event);");
            getChildren().add(in);
            cl++;
        }
    }

}
