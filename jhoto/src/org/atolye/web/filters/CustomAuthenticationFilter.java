package org.atolye.web.filters;

import static org.jboss.seam.ScopeType.APPLICATION;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.annotations.web.Filter;
import org.jboss.seam.contexts.Context;
import org.jboss.seam.contexts.SessionContext;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jboss.seam.servlet.ServletRequestSessionMap;
import org.jboss.seam.web.AbstractFilter;

@Scope(APPLICATION)
@Name("customAuthenticationFilter")
@Install(precedence = Install.DEPLOYMENT)
@BypassInterceptors
@Filter(within = "org.jboss.seam.web.exceptionFilter")
public class CustomAuthenticationFilter extends AbstractFilter {
	// /seam/resource/rest/*
	@Logger
	protected Log log;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (!(request instanceof HttpServletRequest)) {
			throw new ServletException("This filter can only process HttpServletRequest requests");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpRequest.getSession();
		Context ctx = new SessionContext(new ServletRequestSessionMap(httpRequest));
		Identity identity = (Identity) ctx.get(Identity.class);
		if (identity.isLoggedIn()) {
			chain.doFilter(request, response);
		}
		else {
			// go login than come back
			httpResponse.sendRedirect("/jhoto/login?serviceUrl="
					+ httpRequest.getRequestURL());
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		setUrlPattern("/seam/resource/rest/*");
		super.init(filterConfig);
	}
}
