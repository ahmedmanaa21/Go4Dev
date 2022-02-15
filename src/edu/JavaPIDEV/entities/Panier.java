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
public class Panier {
  private int Id_panier ;
  private int Ref_equipement ;
  private int Total_panier;  
  
public Panier(){} 

    public Panier(int Id_panier, int Ref_equipement, int Total_panier) {
        this.Id_panier = Id_panier;
        this.Ref_equipement = Ref_equipement;
        this.Total_panier = Total_panier;
    }

    public Panier(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_panier() {
        return Id_panier;
    }

    public void setId_panier(int Id_panier) {
        this.Id_panier = Id_panier;
    }

    public int getRef_equipement() {
        return Ref_equipement;
    }

    public void setRef_equipement(int Ref_equipement) {
        this.Ref_equipement = Ref_equipement;
    }

    public int getTotal_panier() {
        return Total_panier;
    }

    public void setTotal_panier(int Total_panier) {
        this.Total_panier = Total_panier;
    }

    @Override
    public String toString() {
        return "Panier{" + "Id_panier=" + Id_panier + ", Ref_equipement=" + Ref_equipement + ", Total_panier=" + Total_panier + '}';
    }
    
  
  
}
