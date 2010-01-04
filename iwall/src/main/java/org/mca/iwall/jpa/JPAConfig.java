package org.mca.iwall.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConfig {

    @Produces
    @RequestScoped
    private EntityManager createEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }

    @Produces
    @ApplicationScoped
    private EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("main");
    }

}
