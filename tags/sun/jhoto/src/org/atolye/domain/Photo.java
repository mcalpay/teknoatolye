package org.atolye.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("newPhoto")
@Scope(ScopeType.CONVERSATION)
public class Photo {

	@Id
	@Length(min = 1, max = 20)
	private String name;

	@NotNull
	private byte[] data;

	private Boolean approved;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Photo approve() {
		approved = Boolean.TRUE;
		return this;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return name + ", " + approved + ", " + (data != null ? data.length : 0);
	}

}
