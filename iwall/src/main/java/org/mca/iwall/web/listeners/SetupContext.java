package org.mca.iwall.web.listeners;

import org.mca.iwall.domain.User;
import org.mca.iwall.domain.Wall;

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
        createATestUser(entityManager);
        entityManager.close();
    }

    private void createATestUser(EntityManager entityManager) {
        try {
            entityManager.createNamedQuery(User.Queries.GETUSERBYNAME)
                    .setParameter(1, "testus").getSingleResult();
        } catch (NoResultException nre) {
            entityManager.getTransaction().begin();
            User user = new User("testus");
            user.setWall(new Wall("demo"));
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

}
