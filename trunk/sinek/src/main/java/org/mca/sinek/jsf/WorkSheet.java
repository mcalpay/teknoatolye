package org.mca.sinek.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class WorkSheet {
    List<List<String>> sheet;

    public WorkSheet(List<List<String>> sheet) {
        this.sheet = sheet;
    }

    public List<List<String>> getSheet() {
        return sheet;
    }

    public void setSheet(List<List<String>> sheet) {
        this.sheet = sheet;
    }

    public Integer getNumOfColumns() {
        return sheet.get(0).size();
    }


    public int getNumOfRows() {
        return sheet.size();
    }

    public List<String> addEmptyRow() {
        List<String> row = new ArrayList<String>();
        int i = 0;
        int r = getNumOfColumns();
        while( i < r) {
            row.add("");
            i++;
        }
        
        sheet.add(row);
        return row;
    }

    public void addEmptyCol() {
        for(List<String> row : sheet) {
            row.add("");
        }
    }
}
