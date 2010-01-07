package org.mca.iwall.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConfig {

    @Produces
    @RequestScoped
    @Named("entityManager")
    private EntityManager createEntityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }

    private void disposeEntityManager(@Disposes EntityManager em) {
        em.close();
    }

    @Produces
    @ApplicationScoped
    private EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("main");
    }

}
