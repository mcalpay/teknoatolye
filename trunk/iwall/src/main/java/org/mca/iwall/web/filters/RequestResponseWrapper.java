package org.mca.iwall.web.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RequestResponseWrapper {

    ServletRequest servletRequest;
    ServletResponse servletResponse;

    public RequestResponseWrapper(ServletRequest servletRequest, ServletResponse servletResponse) {
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    public ServletRequest getServletRequest() {
        return servletRequest;
    }

    public ServletResponse getServletResponse() {
        return servletResponse;
    }
}
