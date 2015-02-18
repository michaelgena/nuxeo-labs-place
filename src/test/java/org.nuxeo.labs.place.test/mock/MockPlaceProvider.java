package org.nuxeo.labs.place.test.mock;

import org.nuxeo.labs.place.model.Place;
import org.nuxeo.labs.place.model.PlaceSuggestion;
import org.nuxeo.labs.place.providers.PlaceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaël on 17/02/2015.
 */
public class MockPlaceProvider implements PlaceProvider {

    public static final String ID = "abcdef";
    public static final String CITY = "Paris";

    
    public MockPlaceProvider(String string) {
        //do nothing
    }

    
    @Override
    public List<PlaceSuggestion> getSuggestions(String query) {
        PlaceSuggestion suggestion = new PlaceSuggestion() {
            @Override
            public String getId() {
                return ID;
            }

            @Override
            public String getDescription() {
                return CITY;
            }
        };
        List<PlaceSuggestion> suggestions = new ArrayList<>();
        suggestions.add(suggestion);
        return suggestions;
    }

    @Override
    public Place getPlaceDetail(String query) {
        Place place = new Place();
        place.setId(query);
        place.setCity(query==null || query.length()==0 ? null : CITY);
        return place;
    }
}
