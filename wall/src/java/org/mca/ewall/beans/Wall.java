package org.mca.ewall.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

}
