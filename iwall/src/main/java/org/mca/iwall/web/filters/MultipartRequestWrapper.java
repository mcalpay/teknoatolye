package org.mca.iwall.web.filters;

import org.apache.commons.io.IOUtils;

import javax.enterprise.event.Event;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/**
 part : >j_idt7<, >form-data; name="j_idt7">, <null|#]
 part : >j_idt7:j_idt9<, >form-data; name="j_idt7:j_idt9">, <null|#]
 part : >j_idt7:j_idt11<, >form-data; name="j_idt7:j_idt11">, <null|#]
 part : >avatar<, >form-data; name="avatar"; filename="827171242161642.jpg">, <null|#]
 part : >j_idt7:j_idt13<, >form-data; name="j_idt7:j_idt13">, <null|#]
 part : >javax.faces.ViewState<, >form-data; name="javax.faces.ViewState">, <null|#]
 */
public class MultipartRequestWrapper extends HttpServletRequestWrapper {

    private Hashtable<String, String[]> params = new Hashtable<String, String[]>();

    MultipartRequestWrapper(HttpServletRequest request, Event<Part> uploadEvents) {
        super(request);

        try {

            for (Part item : request.getParts()) {
                String contentHeader = item.getHeader("content-disposition");
                System.out.println("part : >" + item.getName() + "<, >" + contentHeader + ">, <" + item.getHeader("content-type]"));
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
