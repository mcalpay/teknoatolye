package org.mca.iwall.web.filters;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

@WebFilter("/*")
public class ProxyFilter implements Filter {

    @Inject
    @Any
    private Event<Part> uploadEvents;

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

        String contenttype = request.getContentType();
        if (contenttype != null && contenttype.startsWith("multipart/form-data")) {
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
