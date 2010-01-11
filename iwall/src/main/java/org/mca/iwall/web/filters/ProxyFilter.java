package org.mca.iwall.web.filters;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ProxyFilter implements Filter {

    @Inject
    @Any
    private Event<FileItem> uploadEvents;

    @Inject
    @Any
    private Event<RequestResponseWrapper> filterEvent;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        
        filterEvent
                .select(new FilterLiteral(AfterBeforeFilterEnum.BEFORE))
                .fire(new RequestResponseWrapper(servletRequest, servletResponse));

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            servletRequest = new MultipartRequestWrapper(request,uploadEvents);
        }

        filterChain.doFilter(servletRequest, servletResponse);
        filterEvent
                .select(new FilterLiteral(AfterBeforeFilterEnum.AFTER))
                .fire(new RequestResponseWrapper(servletRequest, servletResponse));
    }

    @Override
    public void destroy() {
    }

    private static class FilterLiteral extends AnnotationLiteral<FilterDefinition> implements FilterDefinition {
        private AfterBeforeFilterEnum requestResponse;

        private FilterLiteral(AfterBeforeFilterEnum requestResponse) {
            this.requestResponse = requestResponse;
        }

        public AfterBeforeFilterEnum when() {
            return requestResponse;
        }
    }

}
