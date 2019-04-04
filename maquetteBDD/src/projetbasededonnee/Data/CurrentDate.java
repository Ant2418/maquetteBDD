/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Antoine
 */
public class CurrentDate {
    private java.util.Date date;
    private SimpleDateFormat dateFormat;
    public CurrentDate(){
		//get current date time using java.util.Date class
		 date = new java.util.Date();

                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                 //HH:mm:ss
		//formatting date time
		System.out.println(dateFormat.format(date));
	}
    public java.util.Date getDate(){
        return date;
    }
    
    public SimpleDateFormat getdateFormat(){
        return dateFormat;
    }
    
}
