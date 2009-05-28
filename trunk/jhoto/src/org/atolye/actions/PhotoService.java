package org.atolye.actions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProduceMime;

import org.atolye.domain.Photo;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

@Name("photoService")
@Scope(ScopeType.APPLICATION)
@Path("/foto")
public class PhotoService {

	@Logger
	private Log logger;

	@In
	private EntityManager entityManager;

	@In
	private Identity identity;

	@SuppressWarnings("unchecked")
	public List<Photo> getFrontPagePhotos() {
		logger.info("getFrontPagePhotos whit admin :" + identity.hasRole("admin"));
		if (identity.hasRole("admin")) {
			return entityManager.createQuery("select p from Photo p").getResultList();
		}

		return entityManager.createQuery("select p from Photo p where p.approved=true").getResultList();

	}

	@GET
	@Path("/{fotoId}")
	@ProduceMime("image/jpeg")
	@Restrict("#{s:hasRole('user') || s:hasRole('admin')}")
	public byte[] getFoto(@PathParam("fotoId")
	String fotoId) {
		Photo p = getPhoto(fotoId);
		if (p == null) {
			return null;
		}
		return p.getData();
	}

	private Photo getPhoto(String fotoId) {
		try {
			return (Photo) entityManager.createQuery("select p from Photo p where p.name=?1").setParameter(1, fotoId)
					.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public void createNewPhoto(Photo p) {
		if (p.getData() != null) {
			if (getPhoto(p.getName()) == null) {
				entityManager.persist(p);
			}
			else {
				FacesMessages.instance().add("Ayný isimde bir kayýt var!");
			}
		}
		else {
			FacesMessages.instance().add("Dosya zorunludur!");
		}
	}

	@Restrict("#{s:hasRole('admin')}")
	public void approvePhoto(Photo p) {
		p.approve();
		entityManager.merge(p);
	}

	@Restrict("#{s:hasRole('admin')}")
	public void disapprovePhoto(Photo p) {
		entityManager.remove(getPhoto(p.getName()));
	}

}
