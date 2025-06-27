package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;

import java.util.List;

public interface FactoryConfig {

    String siteId();
    String apiId();
    String apiKey();
    List<SampleConfig> getConfig();
}
