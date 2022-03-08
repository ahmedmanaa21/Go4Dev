/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

/**
 *
 * @author melki
 */
public class ZoneCamping {
    private int id ;
    private String region ;
    private String delegation ;
    private String nom_centre ; 
    private double latitude ;
    private double longitude ;
    private String description ; 

    public ZoneCamping() {
    }

    public ZoneCamping(int id, String region, String delegation, String nom_centre, String description) {
        this.id = id;
        this.region = region;
        this.delegation = delegation;
        this.nom_centre = nom_centre;
        this.description = description;
    }

    public ZoneCamping(int id, String region, String delegation, String nom_centre, double latitude, double longitude, String description) {
        this.id = id;
        this.region = region;
        this.delegation = delegation;
        this.nom_centre = nom_centre;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public ZoneCamping(String region, String delegation, String nom_centre, double latitude, double longitude, String description) {
        this.region = region;
        this.delegation = delegation;
        this.nom_centre = nom_centre;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public ZoneCamping(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public String getNom_centre() {
        return nom_centre;
    }

    public void setNom_centre(String nom_centre) {
        this.nom_centre = nom_centre;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ZoneCamping{" + "id=" + id + ", region=" + region + ", delegation=" + delegation + ", nom_centre=" + nom_centre + ", latitude=" + latitude + ", longitude=" + longitude + ", description=" + description + '}';
    }
    


    

    
    
    
    
    
    
    
    
}
