package org.mca.iwall.business;

import org.apache.commons.fileupload.FileItem;
import org.mca.iwall.domain.User;
import org.mca.iwall.domain.Wall;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;

@RequestScoped
@Named("join")
public class Join implements Serializable {

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
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return "/index";
    }

    public void uploadAvatar(@Observes FileItem item) {
        if ("image/jpeg".equals(item.getContentType())) {
            this.avatar = item.get();
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
