package org.nuxeo.labs.place.test;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.place.model.Place;
import org.nuxeo.labs.place.model.PlaceSuggestion;
import org.nuxeo.labs.place.providers.google.GoogleMaps;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import java.util.List;

@RunWith(FeaturesRunner.class)
@Features({CoreFeature.class})
@RepositoryConfig(cleanup = Granularity.METHOD)
public class TestGoogleProvider extends TestCase {

    public static final String API_KEY = "";
    
    @Test
    public void testGetSuggestions() throws Exception {
        GoogleMaps maps = new GoogleMaps(API_KEY);
        List<PlaceSuggestion> suggestions = maps.getSuggestions(" 3 Place de la nation");
        Assert.assertNotNull(suggestions);
        Assert.assertTrue(suggestions.size()>0);
        System.out.println("suggestions: "+suggestions+"\n");
    }

    @Test
    public void testGetDetailFR() throws Exception {
        GoogleMaps maps = new GoogleMaps(API_KEY);
        Place place  = maps.getPlaceDetail("EiMzIFBsYWNlIGRlIGxhIE5hdGlvbiwgUGFyaXMsIEZyYW5jZQ");
        Assert.assertNotNull(place);
        Assert.assertEquals("Paris", place.getCity());
        Assert.assertEquals("3",place.getStreetNumber());
        Assert.assertEquals("75011",place.getZip());
        Assert.assertEquals("Place de la Nation",place.getStreet());
        Assert.assertEquals("France",place.getCountry());
        System.out.println("detail: " + place + "\n");
    }

    @Test
    public void testGetDetailUS() throws Exception {
        GoogleMaps maps = new GoogleMaps(API_KEY);
        Place place  = maps.getPlaceDetail("Ei4zIFBlYWNodHJlZSBTdHJlZXQsIEF0bGFudGEsIEdBLCBVbml0ZWQgU3RhdGVz");
        Assert.assertNotNull(place);
        Assert.assertEquals("Atlanta", place.getCity());
        Assert.assertEquals("3",place.getStreetNumber());
        Assert.assertEquals("30303",place.getZip());
        Assert.assertEquals("Peachtree St NE",place.getStreet());
        Assert.assertEquals("Fulton County",place.getAdministrativeLevel2());
        Assert.assertEquals("Georgia",place.getAdministrativeLevel1());
        Assert.assertEquals("United States",place.getCountry());
        System.out.println("detail: "+place+"\n");
    }

}
