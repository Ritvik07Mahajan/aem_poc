package com.adobe.aem.guides.wknd.core.servlets;


import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Servlet;

import com.adobe.aem.guides.wknd.core.services.PracticeUser;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.osgi.framework.Constants;

@Component(service = Servlet.class, property = {
        Constants.SERVICE_DESCRIPTION + "=Custom Servlets",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/bin/resource/resolver"
})
public class ResourceResolverPractice extends SlingAllMethodsServlet {

    @Reference
    PracticeUser practiceUser;

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(ResourceResolverPractice.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {

        ResourceResolver resourceResolver = practiceUser.getResourceResolver();
        logger.info("user id: {}", resourceResolver.getUserID());

        Resource contentPageResource = resourceResolver.getResource("/content/indusind-corporate/en/personal/jcr:content/header");

        if (contentPageResource != null) {
            ValueMap vm = contentPageResource.getValueMap();
            Iterator<Map.Entry<String, Object>> properties = vm.entrySet().iterator();

            while (properties.hasNext()) {
                Map.Entry<String, Object> entry = properties.next();
                logger.info("key: {}, value: {}", entry.getKey(), entry.getValue());
            }
        } else {
            logger.error("/content/indusind-corporate/en/personal/jcr:content/header");
        }

        response.setStatus(SlingHttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("Service Called");
    }
}