package org.mca.iwall.web.filters;

import org.apache.commons.io.IOUtils;

import javax.enterprise.event.Event;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class MultipartRequestWrapper extends HttpServletRequestWrapper {

    private Hashtable<String, String[]> params = new Hashtable<String, String[]>();

    MultipartRequestWrapper(HttpServletRequest request, Event<Part> uploadEvents) {
        super(request);

        try {

            for (Part item : request.getParts()) {
                String contentHeader = item.getHeader("content-disposition");
                if(contentHeader.contains("filename=")) {
                    uploadEvents.fire(item);
                } else {
                    params.put(item.getName(), new String[]{new String(IOUtils.toByteArray(item.getInputStream()))});
                }
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        if (values == null || values.length == 0) {
            return null;
        }

        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return params.keys();
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

}
