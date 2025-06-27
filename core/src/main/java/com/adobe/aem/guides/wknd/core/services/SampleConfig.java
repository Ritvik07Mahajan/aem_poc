package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="OSGI Factory Configuration", description = "Practicing factory configuration")
public @interface SampleConfig {

    @AttributeDefinition(name = "Site Identifier")
    String siteId() default "";

    @AttributeDefinition(name = "API ID")
    String apiId() default "";

    @AttributeDefinition(name = "API Key")
    String apiKey() default "";
}
