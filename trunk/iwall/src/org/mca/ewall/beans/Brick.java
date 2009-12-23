package org.mca.ewall.beans;

import org.mca.ewall.beans.context.ActionScoped;
import java.io.Serializable;
import javax.inject.Named;

/**
 * A Brick on the wall that has some text 
 * @author mcalpay
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
