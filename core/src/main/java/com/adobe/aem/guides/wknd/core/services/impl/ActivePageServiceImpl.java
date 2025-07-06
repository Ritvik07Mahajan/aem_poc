package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.services.ActivePageService;
import com.adobe.aem.guides.wknd.core.services.PracticeUser;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.component.annotations.Component;

import java.util.*;
import javax.jcr.Session;

@Component(service = ActivePageService.class)
public class ActivePageServiceImpl implements ActivePageService {

    @Reference
    QueryBuilder queryBuilder;

    @Reference
    PracticeUser practiceUser;

    List<String>activePaths;

    private static final Logger LOG= LoggerFactory.getLogger(PracticeScheduledTask.class);


    public Map<String,String> createTextSearchQuery(){
        Map<String,String> queryMap=new HashMap<>();
        queryMap.put("1_property","jcr:content/cq:lastReplicationAction");
        queryMap.put("1_property.value","Activate");
        queryMap.put("path","/content/wknd");
        queryMap.put("type","cq:Page");
        queryMap.put("p.limit","-1");
        return queryMap;
    }

    @Override
    public List<String> getActivePages() {
        try {
            activePaths=new ArrayList<>();;
            ResourceResolver resourceResolver = practiceUser.getResourceResolver();

            final Session session = resourceResolver.adaptTo(Session.class);
            Query query = queryBuilder.createQuery(PredicateGroup.create(createTextSearchQuery()), session);
            SearchResult result = query.getResult();
            List<Hit> hits =result.getHits();

            for(Hit hit: hits) {
                Page page = hit.getResource().adaptTo(Page.class);
                if (Objects.nonNull(page)) {
                    activePaths.add(page.getPath());
                }
            }

        }catch (Exception e){
            LOG.info("\n ----ERROR -----{} ",e.getMessage());
        }
        return activePaths;
        }
}
