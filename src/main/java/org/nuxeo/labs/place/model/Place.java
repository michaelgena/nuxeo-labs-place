package org.nuxeo.labs.place.model;

/**
 * Created by Michaël on 16/02/2015.
 */
public class Place {

    protected String id;
    protected String formatedAddress;
    protected String streetNumber;
    protected String street;
    protected String city;
    protected String zip;
    protected String administrativeLevel1;
    protected String administrativeLevel2;
    protected String country;
    protected double lat,lng;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormatedAddress() {
        return formatedAddress;
    }

    public void setFormatedAddress(String formatedAddress) {
        this.formatedAddress = formatedAddress;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAdministrativeLevel1() {
        return administrativeLevel1;
    }

    public void setAdministrativeLevel1(String administrativeLevel1) {
        this.administrativeLevel1 = administrativeLevel1;
    }

    public String getAdministrativeLevel2() {
        return administrativeLevel2;
    }

    public void setAdministrativeLevel2(String administrativeLevel2) {
        this.administrativeLevel2 = administrativeLevel2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", formatedAddress='" + formatedAddress + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", administrativeLevel1='" + administrativeLevel1 + '\'' +
                ", administrativeLevel2='" + administrativeLevel2 + '\'' +
                ", country='" + country + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
