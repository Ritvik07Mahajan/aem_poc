package com.adobe.aem.guides.wknd.core.models.impl;


import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;

import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import org.apache.sling.models.annotations.Model;

import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;

import org.apache.sling.models.annotations.injectorspecific.SlingObject;

/**

 * Model for resolving whether the url is internal or external.

 */

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class LinkCheckerModel {

    /**
     * The "/" constant
     */
    public static final String SLASH = "/";

    /**
     * The "=" constant
     */
    public static final String EQUALS = "=";

    /**
     * The "/content/dam" constant
     */
    public static final String CONTENT_DAM = "/content/dam";

    /**
     * The "Video" constant
     */
    public static final String VIDEO = "Video";

    /**
     * The "Image" constant
     */
    public static final String IMAGE = "Image";

    /**
     * The "." constant
     */
    public static final String DOT = ".";

    /**
     * The "*" constant
     */
    public static final String ASTRIX = "*";

    /**
     *  The "html" constant
     */
    public static final String HTML = "html";

    /**
     *  The "content" constant
     */
    public static final String CONTENT = "content";

    /**
     *  The "mg-select" constant
     */
    public static final String MG_SELECT = "mg-select";

    /**
     *  The "_blank" constant
     */
    public static final String BLANK = "_blank";

    /**
     *  The "_self" constant
     */
    public static final String SELF = "_self";

    /**
     *  The "path" constant
     */
    public static final String PATH = "path";

    /**
     *  The "type" constant
     */
    public static final String TYPE = "type";

    /**
     *  The "content/mg-select/language-master/en" constant
     */
    public static final String CONTENT_MG_SELECT = "/content/mg-select/language-master/en";

    /**
     * The "/content/dam/mg-select" constant
     */
    public static final String DAM_MG_SELECT = "/content/dam/mg-select";

    /**
     *  The "p.limit" constant
     */
    public static final String P_LIMIT = "p.limit";

    /**
     *  The "mg-select-subservice" constant
     */
    public static final String MG_SELECT_SUBSERVICE = "mg-select-subservice";

    /**
     *  The "assetPath" constant
     */
    public static final String ASSET_PATH = "assetPath";
    /**

     * Injected parameter "url"

     */

    @RequestAttribute

    private String url;

    /**

     * Initializing String "href"

     */

    private String href;

    /**

     * Initializing the target

     */

    private String target;

    /**

     * injecting resource resolver

     */

    @SlingObject

    private ResourceResolver resourceResolver;

    /**

     * Post Construct Method Will Execute after all injection Checking Link as internal or external and assigning it to

     * "href" as required

     */

    @PostConstruct

    public void init() {

        if (Objects.nonNull(url)) {

            String[] str = url.split(SLASH);

            if (SLASH.equalsIgnoreCase(url.substring(0, 1))
                    && CONTENT.equalsIgnoreCase(str[1])
                    && MG_SELECT.equalsIgnoreCase(str[2])) {

                this.href = resourceResolver.map(url);

                if (!this.href.contains(DOT + HTML)) {

                    this.href = url + DOT + HTML;

                }

                this.target = SELF;

            } else {

                this.href = url;

                this.target = BLANK;

            }

        }

    }

    /**

     * Gets the href of the requested url.

     *

     * @return the url

     */

    public String getHref() {

        return href;

    }

    /**

     * Gets the target for request url

     *

     * @return the target

     */

    public String getTarget() {

        return target;

    }

}


