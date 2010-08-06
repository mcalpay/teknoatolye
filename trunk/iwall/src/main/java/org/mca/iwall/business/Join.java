package org.mca.iwall.business;

import org.mca.iwall.domain.User;
import org.mca.iwall.domain.UserQualifier;
import org.mca.iwall.domain.Wall;
import org.mca.iwall.utils.IOUtils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;

@RequestScoped
@Named("join")
public class Join implements Serializable {

    @Inject
    @UserQualifier(User.Qualifiers.ANONYMOUSAPP)
    private User anonymous;

    private String userName;

    private String wallName;

    private byte[] avatar;

    @Inject
    private EntityManager entityManager;

    public String create() {
        entityManager.getTransaction().begin();
        Wall wall = new Wall(wallName);
        entityManager.persist(wall);
        User user = new User(userName);
        user.setWall(wall);
        user.setAvatar(avatar);
        user.getFollowers().add(this.getAnonymous());
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return "/index";
    }

    public User getAnonymous() {
        return anonymous;
    }

    public void uploadAvatar(@Observes Part item) {
        if ("image/jpeg".equals(item.getContentType())) {
            try {
                this.avatar = IOUtils.toByteArray(item.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWallName() {
        return wallName;
    }

    public void setWallName(String wallName) {
        this.wallName = wallName;
    }

}
