package org.mca.iwall.domain;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
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
    @IndexColumn(name = "sira")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wall wall = (Wall) o;

        if (name != null ? !name.equals(wall.name) : wall.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
