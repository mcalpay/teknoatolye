package org.mca.iwall.domain;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WallTests {

    @Test
    public void testCanPersistAWall() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        String name = "Test_Wall";
        Wall wall = new Wall("Test_Wall");
        manager.getTransaction().begin();
        manager.persist(wall);
        manager.getTransaction().commit();

        Wall result = (Wall) manager
                .createQuery("SELECT w FROM Wall w WHERE w.name = :name")
                .setParameter("name", name)
                .getSingleResult();

        Assert.assertEquals(name, result.getName());
        manager.close();
        factory.close();
    }
}
