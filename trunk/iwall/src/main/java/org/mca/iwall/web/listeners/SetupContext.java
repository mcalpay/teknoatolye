package org.mca.iwall.web.listeners;

import org.mca.iwall.domain.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SetupContext implements ServletContextListener {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.createNamedQuery(User.Queries.GETUSERBYNAME)
                    .setParameter(1, "testus").getSingleResult();
        } catch (NoResultException nre) {
            entityManager.getTransaction().begin();
            entityManager.persist(new User("testus"));
            entityManager.getTransaction().commit();
        }
        
        entityManager.close();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

}
