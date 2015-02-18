package org.nuxeo.labs.place.adapter;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.adapter.DocumentAdapterFactory;

/**
 * Created by Michaël on 17/02/2015.
 */
public class PlaceAdapterFactory implements DocumentAdapterFactory {
    
    @Override
    public Object getAdapter(DocumentModel doc, Class<?> itf) {
        return new PlaceAdapterImpl(doc);
    }
    
}
