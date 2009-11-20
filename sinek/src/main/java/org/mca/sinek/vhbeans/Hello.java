package org.mca.sinek.vhbeans;

import org.mca.sinek.jsf.WorkSheet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Arrays;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class Hello {
    final private String text = "Hi!";

    private String name;

    private WorkSheet sheet;

    public Hello() {
        sheet = new WorkSheet(new ArrayList(Arrays.asList(
                new ArrayList(Arrays.asList("A1", "B1", "C1", "B1", "C1")),
                new ArrayList(Arrays.asList("A2", "B2", "C2", "B1", "C1")),
                new ArrayList(Arrays.asList("A3", "B3", "C3", "B1", "C1")))));
    }

    public WorkSheet getBook() {
        return sheet;
    }

    public void setBook(WorkSheet sheet) {
        this.sheet = sheet;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
