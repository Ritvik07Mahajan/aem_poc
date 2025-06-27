package com.adobe.aem.guides.wknd.core.services;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface FetchImage {

    public void getImage(String imageUrl, String damPath, SlingHttpServletRequest req, SlingHttpServletResponse response, String imageName) throws IOException;
}
