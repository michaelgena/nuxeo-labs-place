package org.nuxeo.labs.place.providers.google.model;

import org.nuxeo.labs.place.model.Place;

import java.util.List;

/**
 * Created by Michaël on 16/02/2015.
 */
public class GooglePlace {
    
    public Result result;
    public String status;
    
    
    public class Result {
        public List<AddressComponent> address_components;
        public String place_id;
        public String formatted_address;
        public Geometry geometry;
    }
    
    public class AddressComponent {
        public String long_name;
        public String short_name;
        public List<String> types;
    }

    public class Geometry {
        public Location location;
    }
    
    public class Location {
        public double lat;
        public double lng;
    }
    
    public Place getPlace() {
        Place place = new Place();
        place.setId(result.place_id);
        place.setFormatedAddress(result.formatted_address);
        for (AddressComponent addressComponent : result.address_components) {
            List<String> types = addressComponent.types;
            if (types.contains("street_number")) {
                place.setStreetNumber(addressComponent.long_name);
            } else if (types.contains("route")) {
                place.setStreet(addressComponent.long_name);
            } else if (types.contains("locality") || types.contains("sublocality")) {
                place.setCity(addressComponent.long_name);
            } else if (types.contains("country")) {
                place.setCountry(addressComponent.long_name);
            }else if (types.contains("postal_code")) {
                place.setZip(addressComponent.long_name);
            }else if (types.contains("administrative_area_level_1")) {
                place.setAdministrativeLevel1(addressComponent.long_name);
            }else if (types.contains("administrative_area_level_2")) {
                place.setAdministrativeLevel2(addressComponent.long_name);
            }
        }
        
        if (result.geometry!=null && result.geometry.location!=null) {
            Location location = result.geometry.location;
            place.setLat(location.lat);
            place.setLng(location.lng);
        }
        return place;
    }
}
