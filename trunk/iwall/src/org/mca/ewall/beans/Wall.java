package org.mca.ewall.beans;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds bricks
 * @author mcalpay
 */
public class Wall implements Serializable {

    private String name;

    private List<Brick> bricks = new ArrayList<Brick>();

    public Wall() {
    }

    public Wall(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Brick addBrick(Brick b) {
        bricks.add(b);
        return b;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    /**
     * Creates a demo wall
     * @return
     */
    @Produces
    @SessionScoped
    @Named
    private Wall getTheWall() {
        final Wall wall = new Wall("Berlin");
        wall.addBrick(new Brick("Kirmizi"));
        return wall;
    }

}
