/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.JavaPIDEV.utils;

import java.util.regex.Matcher;

/**
 *
 * @author dell
 */
public class equipementutils {
    
  public boolean testNom(String nom) {

        Matcher matcher = cnst.VALID_NAME_REGEX.matcher(nom);
        return matcher.find();

    }
  
  
 
 public boolean testPrix(String prix) {

        Matcher matcher = cnst.VALID_PRIXX_REGEX.matcher(prix);
        return matcher.find();
    }  
   
    
    
    
}
