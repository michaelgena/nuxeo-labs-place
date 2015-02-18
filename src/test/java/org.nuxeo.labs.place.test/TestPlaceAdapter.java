package org.nuxeo.labs.place.test;

import com.google.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.place.adapter.PlaceAdapter;
import org.nuxeo.labs.place.model.Place;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

@RunWith(FeaturesRunner.class)
@Features({CoreFeature.class})
@RepositoryConfig(cleanup = Granularity.METHOD)
@LocalDeploy({
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/place-service-contrib.xml",
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/operations-contrib.xml",
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/address-contrib.xml"})
public class TestPlaceAdapter {

    @Inject
    CoreSession session;
    
    
    @Test
    public void testAdapter() {
        DocumentModel doc = session.createDocumentModel("File");
        doc.addFacet("address");
        PlaceAdapter adapter = doc.getAdapter(PlaceAdapter.class);
        Assert.assertNotNull(adapter);
    }


    @Test
    public void testAdapterMethods() {
        DocumentModel doc = session.createDocumentModel("File");
        doc.addFacet("address");
        PlaceAdapter adapter = doc.getAdapter(PlaceAdapter.class);
        Assert.assertNotNull(adapter);
        Place place = adapter.getPlace();
        place.setStreetNumber("3");
        place.setStreet("Place de la nation");
        place.setCity("Paris");
        place.setZip("75011");
        place.setCountry("France");
        
        adapter.updatePlace(place);
        
        Assert.assertEquals(place.getCity(),doc.getPropertyValue("address:city"));
    }
}