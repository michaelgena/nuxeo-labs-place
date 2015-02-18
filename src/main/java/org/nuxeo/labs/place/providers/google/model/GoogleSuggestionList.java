package org.nuxeo.labs.place.providers.google.model;

import org.nuxeo.labs.place.model.PlaceSuggestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaël on 16/02/2015.
 */
public class GoogleSuggestionList {
    
    private List<GoogleSuggestion> predictions;
    
    private String status;
    
    
    public List<PlaceSuggestion> getSuggestions() {
        return new ArrayList<PlaceSuggestion>(predictions);
    }
    
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "GoogleSuggestionList{" +
                "predictions=" + predictions +
                ", status='" + status + '\'' +
                '}';
    }
}
