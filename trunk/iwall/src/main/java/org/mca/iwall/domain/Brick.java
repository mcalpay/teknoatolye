package org.mca.iwall.domain;

import org.mca.iwall.beans.context.ActionScoped;
import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * A Brick on the wall that has some text 
 * @author mcalpay
 */
@ActionScoped
@Named("newBrick")
@Entity
public class Brick implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String grafiti;

    @ManyToOne
    private User user;

    public Brick() {
    }

    public Brick(String grafiti) {
        this.grafiti = grafiti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrafiti() {
        return grafiti;
    }

    public void setGrafiti(String grafiti) {
        this.grafiti = grafiti;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
