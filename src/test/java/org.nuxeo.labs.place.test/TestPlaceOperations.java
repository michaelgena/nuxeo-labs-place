package org.nuxeo.labs.place.test;

import com.google.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationChain;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.TransactionalFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.place.adapter.PlaceAdapter;
import org.nuxeo.labs.place.operation.GetPlaceDetailOp;
import org.nuxeo.labs.place.operation.GetPlaceSuggestionsOp;
import org.nuxeo.labs.place.test.mock.MockPlaceProvider;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

@RunWith(FeaturesRunner.class)
@Features({AutomationFeature.class, TransactionalFeature.class, CoreFeature.class})
@RepositoryConfig(cleanup = Granularity.METHOD)
@LocalDeploy({
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/place-service-contrib.xml",
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/operations-contrib.xml",
        "org.nuxeo.labs.google.place:OSGI-INF/extensions/address-contrib.xml",
        "org.nuxeo.labs.google.place.org.nuxeo.labs.place.test:mock-api-contrib.xml"})
public class TestPlaceOperations {

    @Inject
    CoreSession session;
    
    @Test
    public void testSuggestionOperation() throws Exception {
        AutomationService as = Framework.getService(AutomationService.class);
        OperationContext ctx = new OperationContext();
        OperationChain chain = new OperationChain("TestTGetPlaceSuggestionsOp");
        chain.add(GetPlaceSuggestionsOp.ID).set("searchTerm", "Paris");
        StringBlob blob = (StringBlob) as.run(ctx,chain);
        Assert.assertNotNull(blob.getString());
    }

    @Test
    public void testDetailOperation() throws Exception {
        
        DocumentModel doc = session.createDocumentModel("File");
        doc.addFacet("address");
        
        AutomationService as = Framework.getService(AutomationService.class);
        OperationContext ctx = new OperationContext();
        ctx.setInput(doc);
        OperationChain chain = new OperationChain("TestTGetPlaceDetailOp");
        chain.add(GetPlaceDetailOp.ID).set("id", "abcdef");
        doc = (DocumentModel) as.run(ctx,chain);

        PlaceAdapter adapter = doc.getAdapter(PlaceAdapter.class);
        
        Assert.assertEquals(MockPlaceProvider.CITY,adapter.getPlace().getCity());

    }
}