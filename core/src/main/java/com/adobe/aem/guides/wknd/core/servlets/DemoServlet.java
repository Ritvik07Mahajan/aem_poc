package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.aem.guides.wknd.core.dto.DataClass;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletPathsStrict;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service=Servlet.class)
@SlingServletPathsStrict(
        paths="/bin/demo/servlet",
        methods=HttpConstants.METHOD_POST
)
public class DemoServlet extends SlingAllMethodsServlet {

    private static final Logger log= LoggerFactory.getLogger(DemoServlet.class);

    @Override
    public void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {

        res.getWriter().write("Hello");
        String path=req.getParameter("path");
        ResourceResolver resourceResolver=req.getResourceResolver();

        Resource resource=resourceResolver.getResource(path);
        Resource resource1=req.getResource();

        assert resource != null;
        DataClass dataClass=resource.adaptTo(DataClass.class);

        JsonObject jsonObject=new JsonObject();

        assert dataClass != null;
        jsonObject.addProperty("cif",dataClass.cif);
        jsonObject.addProperty("dob",dataClass.dob);
        jsonObject.addProperty("confirmPin",dataClass.confirmPIN);
        res.getWriter().write(new Gson().toJson(jsonObject));


        log.info("JCR Data: {}", dataClass);
    }
}
