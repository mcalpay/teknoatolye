package org.atolye.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.jboss.seam.annotations.security.management.RoleName;

@Entity
public class Role {

	@Id
	@RoleName
	private String name;
	
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
