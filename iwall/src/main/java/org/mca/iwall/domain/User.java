package org.mca.iwall.domain;

import org.mca.iwall.web.filters.FilterDefinition;
import org.mca.iwall.web.filters.AfterBeforeFilterEnum;
import org.mca.iwall.web.filters.RequestResponseWrapper;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;

@Entity
@NamedQuery(name = User.Queries.GETUSERBYNAME, query = "select u from User u where u.name = ?1")
public class User implements Principal, Serializable {

    public enum Queries {
        ;
        public static final String GETUSERBYNAME = "User.getUserByName";
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public User() {
    }

    public User(String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    private void checkUser(
            @Observes
            @FilterDefinition(when = AfterBeforeFilterEnum.REQUEST)
            RequestResponseWrapper rr
    ) {
        System.out.println("check user...");
    }

    @Produces
    @RequestScoped
    @Named("login")
    private User getLogin() {
        return new User("");
    }

}
