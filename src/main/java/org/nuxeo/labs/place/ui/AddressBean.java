package org.nuxeo.labs.place.ui;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.ui.web.api.NavigationContext;
import org.nuxeo.labs.place.adapter.PlaceAdapter;
import org.nuxeo.labs.place.providers.google.GoogleMaps;
import org.nuxeo.labs.place.service.PlaceService;
import org.nuxeo.runtime.api.Framework;

import java.io.Serializable;
import java.net.URLEncoder;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static org.jboss.seam.ScopeType.EVENT;

@Name("addressAction")
@Scope(CONVERSATION)
public class AddressBean implements Serializable{

    private static final long serialVersionUID = 1L;

    @In(create = true, required = false)
    protected transient NavigationContext navigationContext;

    @Factory(value = "mapUrl", scope = EVENT)
    public String getMapUrl() {
        DocumentModel currentDoc = navigationContext.getCurrentDocument();
        PlaceAdapter placeAdapter = currentDoc.getAdapter(PlaceAdapter.class);
        GoogleMaps maps = (GoogleMaps) Framework.getService(PlaceService.class).getProvider();
        return "https://www.google.com/maps/embed/v1/place?key="+
                maps.getKey()+
                "&q="+
                URLEncoder.encode(placeAdapter.getPlace().getFormatedAddress());

    }

}
