/*
 * (C) Copyright ${year} Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     thibaud
 */

package org.nuxeo.labs.place.operation;

import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.labs.place.adapter.PlaceAdapter;
import org.nuxeo.labs.place.model.Place;
import org.nuxeo.labs.place.service.PlaceService;
import org.nuxeo.runtime.api.Framework;

/**
 * An operation which fetches place suggestions from the place service
 */
@Operation(id= GetPlaceDetailOp.ID, category=Constants.CAT_DOCUMENT, label="Place: get details", description="")
public class GetPlaceDetailOp {

    public static final String ID = "GetPlaceDetailOp";

    @Context
    protected CoreSession session;

    @Context
    protected OperationContext ctx;
    
    @Param(name = "id", required = false)
    protected String id;

    @Param(name = "save", required = false)
    protected boolean save;
    
    @OperationMethod
    public DocumentModel run(DocumentModel doc) {
        PlaceService service = Framework.getService(PlaceService.class);
        Place place = service.getProvider().getPlaceDetail(id);
        PlaceAdapter adapter = doc.getAdapter(PlaceAdapter.class);
        adapter.updatePlace(place);
        if (save) session.saveDocument(doc);
        return doc;
    }

}
