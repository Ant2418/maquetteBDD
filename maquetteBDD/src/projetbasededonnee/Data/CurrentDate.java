/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

import java.text.SimpleDateFormat;

/**
 * Permet de créer une date au format bdd
 * @author Antoine et Ludivine
 * @version 24/04/2019
 */
public class CurrentDate {
    private final java.util.Date date;
    private final SimpleDateFormat dateFormat;
    
    /**
     * Constructeur pour la date
     */
    public CurrentDate(){
		//get current date time using java.util.Date class
		 date = new java.util.Date();

                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                 //HH:mm:ss
		//formatting date time
		System.out.println(dateFormat.format(date));
    }
    
    /**
     * Getter pour la date
     * @return date
     */
    public java.util.Date getDate(){
        return date;
    }
    
    /**
     * Getter pour SimpleDateFormat
     * @return dateFormat
     */
    public SimpleDateFormat getdateFormat(){
        return dateFormat;
    }
    
}
