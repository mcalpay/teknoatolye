package org.mca.iwall.business;

import org.mca.iwall.domain.User;
import org.mca.iwall.domain.UserQualifier;
import org.mca.iwall.domain.Wall;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Named("current")
public class Current implements Serializable {

    @Inject
    @UserQualifier(User.Qualifiers.ANONYMOUS)
    private User anonymous;

    private User user;

    @Inject
    private EntityManager entityManager;

    public User getUser() {
        if (user == null) {
            user = anonymous;
        }
        return user;
    }

    public String checkLogin(User user) {
        try {
            this.user = (User) entityManager.createNamedQuery(User.Queries.GETUSERBYNAME)
                    .setParameter(1, user.getName())
                    .getSingleResult();
            this.user.login();
            return "/index";
        } catch (NoResultException nre) {
            return "";
        }
    }

    public String logout() {
        this.user = anonymous;
        return "redirect:/index";
    }

    @Produces
    @RequestScoped
    @UserQualifier(User.Qualifiers.PRINCIPAL)
    @Named("principal")
    private User getPrincipal() {
        return this.getUser();
    }

    @Produces
    @RequestScoped
    @Named("wall")
    private Wall getWall() {
        User usr = this.getUser();
        return usr.getWall();
    }

}
