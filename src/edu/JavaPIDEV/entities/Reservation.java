/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.entities;

import java.sql.Date;

/**
 *
 * @author Jasser BOUKRAYA
 */
public class Reservation {
    private int id_res;
    private int cin;
    private Date date_res;
    private int id_zoneCamping;
    private int nrbPersonne;

    public Reservation() {
    }

    public Reservation(int id_res ,Date date_res, int id_zoneCamping, int nrbPersonne) {
        this.id_res = id_res;
        this.date_res = date_res;
        this.id_zoneCamping = id_zoneCamping;
        this.nrbPersonne = nrbPersonne;
    }


    public Reservation(int id_res, int cin, Date date_res, int id_zoneCamping, int nrbPersonne) {
        this.id_res = id_res;
        this.cin = cin;
        this.date_res = date_res;
        this.id_zoneCamping = id_zoneCamping;
        this.nrbPersonne = nrbPersonne;
    }
    

    

    public Reservation(int id_res, Date date_res, int nrbPersonne) {
        this.id_res = id_res;
        this.date_res = date_res;
        this.nrbPersonne = nrbPersonne;
    }

    public Reservation(int id_res) {
        this.id_res = id_res;
    }


    
    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Date getDate_res() {
        return date_res;
    }

    public void setDate_res(Date date_res) {
        this.date_res = date_res;
    }

    public int getId_zoneCamping() {
        return id_zoneCamping;
    }

    public void setId_zoneCamping(int id_zoneCamping) {
        this.id_zoneCamping = id_zoneCamping;
    }

    public int getNrbPersonne() {
        return nrbPersonne;
    }

    public void setNrbPersonne(int nrbPersonne) {
        this.nrbPersonne = nrbPersonne;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_res=" + id_res + ", cin=" + cin + ", date_res=" + date_res + ", id_zoneCamping=" + id_zoneCamping + ", nrbPersonne=" + nrbPersonne + '}';
    }

   

  
    
        
    
}
