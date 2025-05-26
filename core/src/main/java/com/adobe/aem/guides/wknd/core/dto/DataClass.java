package com.adobe.aem.guides.wknd.core.dto;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class DataClass {

    @ValueMapValue
    public String confirmPIN;

    @ValueMapValue
    public String cif;

    @ValueMapValue
    public String dob;

    public String getConfirmPIN(){
        return confirmPIN;
    }

    public String getCif(){
        return cif;
    }

    public String getDob(){
        return dob;
    }
}
