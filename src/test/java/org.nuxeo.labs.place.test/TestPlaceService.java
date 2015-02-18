package org.nuxeo.labs.place.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.labs.place.providers.PlaceProvider;
import org.nuxeo.labs.place.service.PlaceService;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

@RunWith(FeaturesRunner.class)
@Features({CoreFeature.class})
@RepositoryConfig(cleanup = Granularity.METHOD)
@LocalDeploy({
        "org.nuxeo.labs.place:OSGI-INF/extensions/place-service-contrib.xml",
        "org.nuxeo.labs.place:OSGI-INF/extensions/operations-contrib.xml",
        "org.nuxeo.labs.place.test:mock-api-contrib.xml"})
public class TestPlaceService {

    @Test
    public void serviceShouldBeAvailable() {
        PlaceService service = Framework.getService(PlaceService.class);
        Assert.assertNotNull(service);
        PlaceProvider provider = service.getProvider();
        Assert.assertNotNull(provider);
    }
}