package org.atolye.actions;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.atolye.domain.Role;
import org.atolye.domain.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Identity;


@Scope(ScopeType.APPLICATION)
@Name("authenticator")
public class Authenticator {

	@In
	private EntityManager entityManager;

	@In
	private Identity identity;

	public boolean authenticate() {
		try {
			User u = (User) entityManager.createQuery(
					"select u from User u where "
							+ "u.login = #{credentials.username} and u.password = #{credentials.password}")
					.getSingleResult();

			CollectionUtils.forAllDo(u.getRoles(), new Closure() {
				public void execute(Object objrole) {
					Role role = (Role) objrole;
					identity.addRole(role.getName());
				}
			});

			return true;
		}
		catch (NoResultException ex) {
			return false;
		}
	}

}
