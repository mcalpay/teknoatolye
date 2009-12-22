package org.mca.ewall.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 *
 * @author malpay
 */
@ApplicationScoped
public class WallFactory {
     public @Produces @SessionScoped @Named Wall getTheWall() {
        final Wall wall = new Wall("Berlin");
        wall.addBrick(new Brick("Kirmizi"));
        return wall;
    }
}