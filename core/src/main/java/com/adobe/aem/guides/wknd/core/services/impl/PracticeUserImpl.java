package  com.adobe.aem.guides.wknd.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import com.adobe.aem.guides.wknd.core.services.PracticeUser;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PracticeUser.class, immediate = true)
public class PracticeUserImpl implements PracticeUser {

    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    public ResourceResolver getResourceResolver() {
        ResourceResolver resolver = null;
        Map<String, Object> param = getServiceParams();

        try {
            resolver = resolverFactory.getServiceResourceResolver(param);

        } catch (LoginException e) {
            e.printStackTrace();
        }
        return resolver;
    }

    public static Map<String, Object> getServiceParams() {
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "sampleService");
        return param;
    }

}