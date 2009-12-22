/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mca.ewall.beans;

import org.mca.ewall.beans.context.ActionScoped;
import java.io.Serializable;
import javax.inject.Named;

/**
 * @author malpay
 */
@ActionScoped
@Named("newBrick")
public class Brick implements Serializable {

    private String grafiti;

    public Brick() {
    }

    public Brick(String grafiti) {
        this.grafiti = grafiti;
    }

    public String getGrafiti() {
        return grafiti;
    }

    public void setGrafiti(String grafiti) {
        this.grafiti = grafiti;
    }

}
