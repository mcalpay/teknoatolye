package org.mca.iwall.business;

import org.mca.iwall.beans.security.Anonymous;
import org.mca.iwall.beans.security.Principal;
import org.mca.iwall.domain.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;

@SessionScoped
@Named("current")
public class Current implements Serializable {

    @Inject
    @Anonymous
    private User user;

    @Inject    
    private EntityManager entityManager;

    public User getUser() {
        return user;
    }

    public String checkLogin(User user) {
        try {
            this.user = (User) entityManager.createNamedQuery(User.Queries.GETUSERBYNAME)
                .setParameter(1,user.getName())
                .getSingleResult();
            return "/index";
        } catch(NoResultException nre) {
            return "";
        } finally {
            entityManager.close();
        }
    }

    @Produces
    @RequestScoped
    @Principal
    @Named("principal")
    private User getPrincipal(Current current) {
        return current.getUser();
    }

}
