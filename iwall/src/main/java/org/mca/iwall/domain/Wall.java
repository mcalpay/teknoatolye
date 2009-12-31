package org.mca.iwall.domain;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds bricks
 * @author mcalpay
 */
@Entity
public class Wall implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    private List<Brick> bricks = new ArrayList<Brick>();

    public Wall() {
    }

    public Wall(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public Brick addBrick(Brick b) {
        bricks.add(b);
        return b;
    }

    /**
     * Creates a demo wall
     * @return wall
     */
    @Produces
    @SessionScoped
    @Named("theWall")
    private Wall buidTheWall() {
        final Wall wall = new Wall("Berlin");
        wall.addBrick(new Brick("Kirmizi"));
        return wall;
    }

}
