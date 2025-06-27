package com.adobe.aem.guides.wknd.core.servlets;

import com.google.gson.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_METHODS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_PATHS;

@Component(service = {Servlet.class},
           property = {SLING_SERVLET_PATHS+"=/bin/servlet/api",
           SLING_SERVLET_METHODS+"=GET"})
public class ApiServlet extends SlingAllMethodsServlet {

    public static final String APIURL="https://reqres.in/api/users?page=2";

    private static final Logger LOG = LoggerFactory.getLogger(ApiServlet.class);

    @Override
    public void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse res) throws IOException, ServletException {
        res.getWriter().write("hello");

        String jsonData = "";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet req = new HttpGet(APIURL);
            try (CloseableHttpResponse response = httpClient.execute(req)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    jsonData = EntityUtils.toString(entity);
                    JsonObject root = JsonParser.parseString(jsonData).getAsJsonObject();
                    JsonArray dataArray = root.getAsJsonArray("data");

                    List<JsonObject> validUsers = new ArrayList<>();

                    for (JsonElement element : dataArray) {
                        if (element.isJsonObject()) {
                            JsonObject userObj = element.getAsJsonObject();
                                validUsers.add(userObj);
                        }
                    }
                    res.getWriter().write(new Gson().toJson(validUsers));
                }
            }
        } catch (IOException e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.getWriter().write("An error occurred: " + e.getMessage());
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal error occurred!!");
            LOG.error("Error fetching data from URL: {}", APIURL, e);
        }
    }
}
