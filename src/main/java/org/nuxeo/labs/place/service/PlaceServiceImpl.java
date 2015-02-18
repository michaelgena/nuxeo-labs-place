package org.nuxeo.labs.place.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.labs.place.providers.PlaceProvider;
import org.nuxeo.runtime.model.ComponentInstance;
import org.nuxeo.runtime.model.DefaultComponent;

import java.lang.reflect.InvocationTargetException;

public class PlaceServiceImpl extends DefaultComponent implements PlaceService {

    protected ProviderDescriptor config;
    
    protected static final Log log = LogFactory.getLog(PlaceServiceImpl.class);
    
    protected static final String EXT_POINT = "Provider";


    @Override
    public void registerContribution(Object contribution, String extensionPoint, 
                                     ComponentInstance contributor) {
        if (EXT_POINT.equals(extensionPoint)) {
           config = (ProviderDescriptor) contribution;
        } 
    }
    
    
    @Override
    public PlaceProvider getProvider() {
        try {
            return (PlaceProvider) config.getProviderClass().getConstructor(String.class).newInstance(config.getApiKey());
        } catch (NoSuchMethodException|InvocationTargetException|
                 InstantiationException|IllegalAccessException e) {
            log.debug(e);
            return null;
        } 
    }
}
