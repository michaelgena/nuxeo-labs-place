package org.nuxeo.labs.place.adapter;

import org.nuxeo.labs.place.model.Place;

/**
 * Created by Michaël on 16/02/2015.
 */
public interface PlaceAdapter {
    
    public Place getPlace();

    public void updatePlace(Place place);
    
}
