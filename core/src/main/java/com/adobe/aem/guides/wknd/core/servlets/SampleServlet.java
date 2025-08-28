package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.aem.guides.wknd.core.services.ActivePageService;
import com.adobe.aem.guides.wknd.core.services.ExcelUpdate;
import com.adobe.aem.guides.wknd.core.services.impl.ActivePageServiceImpl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_METHODS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_PATHS;

@Component(service = {Servlet.class},
        property = {SLING_SERVLET_PATHS+"=/bin/servlet/sample",
                SLING_SERVLET_METHODS+"=GET"})
public class SampleServlet extends SlingAllMethodsServlet {


    private static final Logger LOG = LoggerFactory.getLogger(SampleServlet.class);

    @Reference
    ActivePageService activePageService;

    @Reference
    ExcelUpdate excelUpdate;

    List<String> pagePaths;
    @Override
    public void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse res) throws IOException, ServletException {
        res.getWriter().write("hello");

        pagePaths=activePageService.getActivePages();
        excelUpdate.UpdateExcel(pagePaths);
    }
}
