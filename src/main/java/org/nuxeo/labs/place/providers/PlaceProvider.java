package org.nuxeo.labs.place.providers;

import org.nuxeo.labs.place.model.Place;
import org.nuxeo.labs.place.model.PlaceSuggestion;

import java.util.List;

/**
 * Created by Michaël on 16/02/2015.
 */
public interface PlaceProvider {

    public List<PlaceSuggestion> getSuggestions(String query);

    public Place getPlaceDetail(String query);
    
}
