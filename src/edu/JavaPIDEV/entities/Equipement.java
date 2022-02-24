/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

/**
 *
 * @author dell
 */
public class Equipement {
  private int Ref_equipement;
  private String Nom_equipement;
  private String image ;
  private  int Prix_equipement;
  private String Description_equipement ; 

  public Equipement(){} 

    public Equipement(int Ref_equipement, String Nom_equipement,String image, int Prix_equipement, String Description_equipement) {
        this.Ref_equipement = Ref_equipement;
        this.image = image;
        this.Nom_equipement = Nom_equipement;
        this.Prix_equipement = Prix_equipement;
        this.Description_equipement = Description_equipement;
    }

    public int getRef_equipement() {
        return Ref_equipement;
    }

    public void setRef_equipement(int Ref_equipement) {
        this.Ref_equipement = Ref_equipement;
    }

    public String getNom_equipement() {
        return Nom_equipement;
    }

    public void setNom_equipement(String Nom_equipement) {
        this.Nom_equipement = Nom_equipement;
    }

    public  int getPrix_equipement() {
        return Prix_equipement;
    }

    public void setPrix_equipement(int Prix_equipement) {
        this.Prix_equipement = Prix_equipement;
    }

    public String getDescription_equipement() {
        return Description_equipement;
    }

    public void setDescription_equipement(String Description_equipement) {
        this.Description_equipement = Description_equipement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Equipement{" + "Ref_equipement=" + Ref_equipement + ", Nom_equipement=" + Nom_equipement + ", image=" + image + ", Prix_equipement=" + Prix_equipement + ", Description_equipement=" + Description_equipement + '}';
    }
}
