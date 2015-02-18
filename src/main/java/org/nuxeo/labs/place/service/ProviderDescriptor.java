package org.nuxeo.labs.place.service;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("Config")
public class ProviderDescriptor {

    @XNode("APIkey")
    protected String apiKey = "/";

    @XNode("providerClass")
    protected Class<?> providerClass;

    public String getApiKey() {
        return apiKey;
    }

    public Class<?> getProviderClass() {
        return providerClass;
    }
}
