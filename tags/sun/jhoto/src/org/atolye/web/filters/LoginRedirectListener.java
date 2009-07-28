package org.atolye.web.filters;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;

@Name("loginRedirect")
@Scope(ScopeType.SESSION)
public class LoginRedirectListener {

	private String serviceUrl;

	@Observer("org.jboss.seam.security.postAuthenticate")
	public void postAuthentication() throws IOException {
		if (serviceUrl != null && serviceUrl.length() > 0) {
			FacesContext.getCurrentInstance().getExternalContext().redirect(serviceUrl);
		}
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

}
