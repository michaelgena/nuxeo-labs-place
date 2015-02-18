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
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.labs.place.model.PlaceSuggestion;
import org.nuxeo.labs.place.service.PlaceService;
import org.nuxeo.runtime.api.Framework;

import java.util.List;

/**
 * An operation which fetches place suggestions from the place service
 */
@Operation(id= GetPlaceSuggestionsOp.ID, category=Constants.CAT_DOCUMENT, label="Place: get suggestions", description="")
public class GetPlaceSuggestionsOp {

    public static final String ID = "GetPlaceSuggestionsOp";

    @Context
    protected CoreSession session;

    @Context
    protected OperationContext ctx;


    @Param(name = "searchTerm", required = false)
    protected String searchTerm;

    @OperationMethod
    public Blob run() {
        PlaceService service = Framework.getService(PlaceService.class);
        List<PlaceSuggestion> suggestions = service.getProvider().getSuggestions(searchTerm);

        StringBuffer jsonResult = new StringBuffer("[");
        boolean first = true;
        
        for(PlaceSuggestion suggestion: suggestions){
            if (!first)  {
                jsonResult.append(",");
            } else {
                first = false;
            }
            jsonResult.append(
                    "{\"id\":\""+suggestion.getId()+ 
                    "\",\"displayLabel\":\""+ suggestion.getDescription()+"\"}");
        }
        jsonResult.append("]");
        return new StringBlob(jsonResult.toString(), "application/json");
    }

}
