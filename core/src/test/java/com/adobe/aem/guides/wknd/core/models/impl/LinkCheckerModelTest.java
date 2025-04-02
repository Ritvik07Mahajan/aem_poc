package com.adobe.aem.guides.wknd.core.models.impl;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class, AemContextExtension.class})
class LinkCheckerModelTest {

    private final AemContext aemContext = new AemContext(ResourceResolverType.JCR_MOCK);

    private MockSlingHttpServletRequest request;
    private LinkCheckerModel linkCheckerModel;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForPackage("com.adobe.aem.guides.wknd.core.models.impl");
        request = aemContext.request();

    }

    @Test
    void newTest () {
        request.setAttribute("url","/content/mg-select/mypage");
        linkCheckerModel = request.adaptTo(LinkCheckerModel.class);
        assertEquals("/content/mg-select/mypage.html", linkCheckerModel.getHref());
    }

    @Test
    void newTestSecond () {
        request.setAttribute("url","/content/mg-select/somepage");
        linkCheckerModel = request.adaptTo(LinkCheckerModel.class);
        assertEquals("/content/mg-select/somepage.html", linkCheckerModel.getHref());
    }
}