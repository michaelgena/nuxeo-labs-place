package org.nuxeo.labs.place.test;

import com.google.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.TransactionalFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.place.adapter.PlaceAdapter;
import org.nuxeo.labs.place.model.Place;
import org.nuxeo.labs.place.test.mock.MockPlaceProvider;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

/**
 * Created by Michaël on 18/02/2015.
 */

@RunWith(FeaturesRunner.class)
@Features({AutomationFeature.class, TransactionalFeature.class, CoreFeature.class})
@RepositoryConfig(cleanup = Granularity.METHOD)
@LocalDeploy({
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/place-service-contrib.xml",
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/operations-contrib.xml",
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/address-contrib.xml",
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/address-event-contrib.xml",
        "org.nuxeo.labs.google.place.org.nuxeo.labs.place.test:mock-api-contrib.xml"})
public class TestPlaceEvent {

    @Inject
    CoreSession session;
    
    @Test
    public void testDocumentCreatedEvent() {
        DocumentModel doc = session.createDocumentModel("File");
        doc.addFacet("address");
        PlaceAdapter adapter = doc.getAdapter(PlaceAdapter.class);
        Place place = adapter.getPlace();
        place.setId("test");
        adapter.updatePlace(place);
        doc = session.createDocument(doc);
        Assert.assertEquals(
                MockPlaceProvider.CITY,
                doc.getAdapter(PlaceAdapter.class).getPlace().getCity());
    }


    @Test
    public void testBeforeDocumentModificationEvent() {
        DocumentModel doc = session.createDocumentModel("File");
        
        // create doc
        doc.addFacet("address");
        doc = session.createDocument(doc);
        Assert.assertEquals(
                null,
                doc.getAdapter(PlaceAdapter.class).getPlace().getCity());
        
        // update doc
        doc.setPropertyValue("address:identification","test");
        doc = session.saveDocument(doc);
        Assert.assertEquals(
                MockPlaceProvider.CITY,
                doc.getAdapter(PlaceAdapter.class).getPlace().getCity());
    }
    
}
