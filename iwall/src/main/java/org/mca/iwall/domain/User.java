package org.mca.iwall.domain;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = User.Queries.GETUSERBYNAME,
                query = "select u from User u " +
                        "join fetch u.wall left join fetch w.bricks " +
                        "where u.name = ?1"),
        @NamedQuery(name = User.Queries.GETUSERSFOLLOWERSBYNAME,
                query = "select u.followers from User u " +
                        "where u.name = ?1")})
public class User implements Principal, Serializable {

    public enum Queries {
        ;
        public static final String GETUSERBYNAME = "User.getUserByName";
        public static final String GETUSERSFOLLOWERSBYNAME = "User.getUsersFollowers";
    }

    public enum Qualifiers {
        LOGIN, PRINCIPAL, ANONYMOUS
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private byte[] avatar;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Wall wall;

    @Transient
    private Boolean loggedIn = false;

    @ManyToMany
    private List<User> followers = new ArrayList<User>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Boolean isLoggedIn() {
        return loggedIn;
    }

    public void login() {
        this.loggedIn = true;
    }

    public void logout() {
        this.loggedIn = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brick addBrick(EntityManager entityManager, Brick brick) {
        entityManager.getTransaction().begin();
        brick.setUser(this);
        entityManager.persist(brick);
        getWall().getBricks().add(0,brick);
        entityManager.merge(getWall());

        List<User> users = entityManager.createNamedQuery(Queries.GETUSERSFOLLOWERSBYNAME)
                .setParameter(1, getName()).getResultList();

        for (User user : users) {
            user.getWall().getBricks().add(0,brick);
            entityManager.merge(user.getWall());
        }

        entityManager.getTransaction().commit();
        return brick;
    }


    @Produces
    @RequestScoped
    @UserQualifier(Qualifiers.LOGIN)
    @Named("login")
    private User getLogin() {
        return new User();
    }

    @Produces
    @UserQualifier(Qualifiers.ANONYMOUS)
    private User getAnonymous(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User anonymous = new User("Anonymous");
        anonymous.setWall(new Wall("Anonymous"));
        try {
            anonymous = (User) entityManager.createNamedQuery(Queries.GETUSERBYNAME)
                    .setParameter(1, anonymous.getName()).getSingleResult();
        } catch (NoResultException nre) {
            entityManager.getTransaction().begin();
            entityManager.persist(anonymous);
            entityManager.getTransaction().commit();
        }

        return anonymous;
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

}
