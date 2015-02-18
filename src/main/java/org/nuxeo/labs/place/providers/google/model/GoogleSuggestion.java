package org.nuxeo.labs.place.providers.google.model;

import org.nuxeo.labs.place.model.PlaceSuggestion;

/**
 * Created by Michaël on 16/02/2015.
 */
public class GoogleSuggestion implements PlaceSuggestion {
    
    private String place_id;
    
    private String description;

    @Override
    public String getId() {
        return place_id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "GoogleSuggestion{" +
                "place_id='" + place_id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
