package org.nuxeo.labs.place.providers.google;

import com.google.gson.Gson;
import org.nuxeo.labs.place.model.Place;
import org.nuxeo.labs.place.model.PlaceSuggestion;
import org.nuxeo.labs.place.providers.PlaceProvider;
import org.nuxeo.labs.place.providers.google.model.GooglePlace;
import org.nuxeo.labs.place.providers.google.model.GoogleSuggestionList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Michaël on 16/02/2015.
 */
public class GoogleMaps implements PlaceProvider{

    protected static final String SUGGESTION_URL =
            "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%s&key=%s";

    protected static final String DETAIL_URL =
            "https://maps.googleapis.com/maps/api/place/details/json?placeid=%s&key=%s";

    protected String key;
    
    private Gson gson = new Gson();

    
    public GoogleMaps(String key) {
        this.key = key;
    }


    public String getKey() {
        return key;
    }

    @Override
    public List<PlaceSuggestion> getSuggestions(String query) {
        try {
            String actualUrl = String.format(
                    SUGGESTION_URL, 
                    URLEncoder.encode(query), 
                    key);
            URL url = new URL(actualUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer sb = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            return gson.fromJson(sb.toString(), GoogleSuggestionList.class).getSuggestions();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Place getPlaceDetail(String id) {
        try {
            String actualUrl = String.format(DETAIL_URL, id,key);
            URL url = new URL(actualUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer sb = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            return gson.fromJson(sb.toString(), GooglePlace.class).getPlace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
