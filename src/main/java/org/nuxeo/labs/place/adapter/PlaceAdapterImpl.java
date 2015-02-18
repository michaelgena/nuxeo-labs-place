package org.nuxeo.labs.place.adapter;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.labs.place.model.Place;

/**
 * Created by Michaël on 17/02/2015.
 */
public class PlaceAdapterImpl implements PlaceAdapter {
    
    private DocumentModel doc;
    
    public PlaceAdapterImpl(DocumentModel doc) {
        this.doc = doc;
    }

    @Override
    public Place getPlace() {
        Place place = new Place();
        place.setId((String) doc.getPropertyValue("address:identification"));
        place.setFormatedAddress((String) doc.getPropertyValue("address:formated"));
        place.setStreetNumber((String) doc.getPropertyValue("address:street_number"));
        place.setStreet((String) doc.getPropertyValue("address:street_name"));
        place.setCity((String) doc.getPropertyValue("address:city"));
        place.setZip((String) doc.getPropertyValue("address:zip"));
        place.setAdministrativeLevel1((String) doc.getPropertyValue("address:administratibe_level_1"));
        place.setAdministrativeLevel2((String) doc.getPropertyValue("address:administratibe_level_2"));
        place.setCountry((String) doc.getPropertyValue("address:country"));
        place.setLat((Double) doc.getPropertyValue("address:latitude"));
        place.setLng((Double) doc.getPropertyValue("address:longitude"));
        return place;
    }

    @Override
    public void updatePlace(Place place) {
        doc.setPropertyValue("address:identification",place.getId());
        doc.setPropertyValue("address:formated",place.getFormatedAddress());
        doc.setPropertyValue("address:street_number",place.getStreetNumber());
        doc.setPropertyValue("address:street_name", place.getStreet());
        doc.setPropertyValue("address:city",place.getCity());
        doc.setPropertyValue("address:zip",place.getZip());
        doc.setPropertyValue("address:administratibe_level_1",place.getAdministrativeLevel1());
        doc.setPropertyValue("address:administratibe_level_2",place.getAdministrativeLevel2());
        doc.setPropertyValue("address:country", place.getCountry());
        doc.setPropertyValue("address:latitude", place.getLat());
        doc.setPropertyValue("address:longitude", place.getLng());
    }
}
