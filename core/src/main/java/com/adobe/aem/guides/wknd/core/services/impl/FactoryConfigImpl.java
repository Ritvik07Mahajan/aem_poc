package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.services.FactoryConfig;
import com.adobe.aem.guides.wknd.core.services.SampleConfig;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import java.util.ArrayList;
import java.util.List;

@Component(service = FactoryConfig.class, configurationPid = "com.example.SearchEngineConfig")
@Designate(ocd = SampleConfig.class, factory = true)
public class FactoryConfigImpl implements FactoryConfig{

    private String siteId;
    private String apiKey;
    private String apiId;

    List<SampleConfig> config;

    @Activate
    @Modified
    protected void activate(SampleConfig sampleConfig) {
        this.apiId=sampleConfig.apiId();
        this.apiKey=sampleConfig.apiKey();
        this.siteId= sampleConfig.siteId();

        if(config==null){
            config=new ArrayList<>();
        }
        else{
            config.add(sampleConfig);
        }
    }


    @Override
    public String siteId() {
        return siteId;
    }

    @Override
    public String apiId() {
        return apiId;
    }

    @Override
    public String apiKey() {
        return apiKey;
    }

    @Override
    public List<SampleConfig> getConfig() {
        return config;
    }
}
