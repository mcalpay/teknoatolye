package org.mca.iwall.web.filters;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.enterprise.event.Event;
import javax.servlet.http.*;
import java.util.*;

public class MultipartRequestWrapper extends HttpServletRequestWrapper {

    private Hashtable<String, String[]> params = new Hashtable<String, String[]>();

    MultipartRequestWrapper(HttpServletRequest request, Event<FileItem> uploadEvents) {
        super(request);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(2097152);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(2097152);

        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    params.put(item.getFieldName(), new String[]{new String(item.get())});
                } else {
                    uploadEvents.fire(item);
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getParameter(String name) {
        String [] values = getParameterValues(name);
        if(values == null || values.length == 0) {
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
