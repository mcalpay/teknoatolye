package org.mca.iwall.web.servlets;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class RestServlet extends HttpServlet {

    @Inject
    private EntityManager entityManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String [] data = path.split("/");
        if(data.length == 4) {
            String query = "select " + data[3] + " from " + data[1] + " where id = ?1";
            byte [] bin = (byte[]) entityManager.createQuery(query).setParameter(1,Long.valueOf(data[2])).getSingleResult();
            if(bin != null) {
                OutputStream os =resp.getOutputStream();
                os.write(bin);
                os.flush();
                os.close();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
