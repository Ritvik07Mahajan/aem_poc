package com.adobe.aem.guides.wknd.core.servlets;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.adobe.aem.guides.wknd.core.services.DataBaseService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/poc/dbServlet")
public class DBServlet extends SlingAllMethodsServlet {

    @Reference
    DataBaseService dataBaseService;

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        response.getWriter().write("DataBase Servlet");

        try {
            dataBaseService.getData();
            //  dataBaseService.insertData();
            dataBaseService.deleteData();
            //   dataBaseService.updateData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

