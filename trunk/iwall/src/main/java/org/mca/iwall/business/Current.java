package org.mca.iwall.business;

import org.mca.iwall.beans.security.Principal;
import org.mca.iwall.domain.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named("current")
public class Current implements Serializable {

    private User user = ANONYMOUS;
    
    private static final User ANONYMOUS = new User("Anonymous");

    public User getUser() {
        return user;
    }

    public void logout() {
        this.user = ANONYMOUS;
    }

    public String checkLogin(User user) {
        if("testus".equals(user.getName())) {
            this.user = user;
            return "/index";
        }
        return "";
    }

    @Produces
    @RequestScoped
    @Principal
    @Named("principal")
    private User getPrincipal(Current current) {
        return current.getUser();
    }

}
