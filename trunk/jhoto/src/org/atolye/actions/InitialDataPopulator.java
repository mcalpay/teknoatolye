package org.atolye.actions;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.atolye.domain.Photo;
import org.atolye.domain.Role;
import org.atolye.domain.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;

@Name("initializer")
@Startup(depends = "entityManager")
@Scope(ScopeType.APPLICATION)
public class InitialDataPopulator {

	@Logger
	private Log logger;

	@In
	private EntityManager entityManager;

	@Create
	@Transactional
	public void initTestUsers() throws IOException {
		entityManager.persist(new User("mca", "mca").addRole(new Role("user")));
		entityManager.persist(new User("op", "op").addRole(new Role("admin")));
		Photo yoda = new Photo();
		yoda.setName("yoda");
		yoda.approve();
		yoda.setData(IOUtils.toByteArray(getClass().getResourceAsStream("yoda.jpg")));
		logger.info("persisting test data : " + yoda);
		entityManager.persist(yoda);
	}

}
