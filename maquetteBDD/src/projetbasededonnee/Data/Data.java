/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Antoine
 */
public class Data {
       
    public static ObservableList<projetbasededonnee.Data.Experience> expListe =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "Dosage BSA", "En attente", " - ", "Toxicité", "Colorimétrique", "10", "3", "3", "9"),
            new projetbasededonnee.Data.Experience("1", "Dosage PAL", "En Cours", " - ", "Toxicité", "Colorimétrique", "20", "3", "5", "15"),
            new projetbasededonnee.Data.Experience("1", "Dosage Hmg", "Terminé", " - ", "Toxicité", "Colorimétrique", "15", "3", "2", "6")
        );
    
    public static ObservableList<projetbasededonnee.Data.Experience> lancerExpListe =
        FXCollections.observableArrayList(
//            new projetbasededonnee.Data.Experience("1", "Befiradol", "100", "mamalienne", "200", "1", "1"),
//            new projetbasededonnee.Data.Experience("2", "Befiradol", "120", "mamalienne", "200", "1", "1"),
//            new projetbasededonnee.Data.Experience("3", "Befiradol", "140", "mamalienne", "200", "2", "1")
        );
    
    public static ObservableList<projetbasededonnee.Data.Experience> puitListe1 =
        FXCollections.observableArrayList(
//            new projetbasededonnee.Data.Experience("1", "1", "1", "1", "1"),
//            new projetbasededonnee.Data.Experience("1", "1", "2", "1", "2"),
//            new projetbasededonnee.Data.Experience("1", "1", "3", "1", "3")    
        );
    
    public static ObservableList<projetbasededonnee.Data.Experience> puitListe2 =
        FXCollections.observableArrayList(
//            new projetbasededonnee.Data.Experience("1", "1", "1", "1", "4"),
//            new projetbasededonnee.Data.Experience("1", "1", "2", "1", "5"),
//            new projetbasededonnee.Data.Experience("1", "1", "3", "1", "6")    
        );
    
    public static ObservableList<projetbasededonnee.Data.Experience> puitListe3 =
        FXCollections.observableArrayList(
//            new projetbasededonnee.Data.Experience("1", "1", "1", "2", "3"),
//            new projetbasededonnee.Data.Experience("1", "1", "2", "2", "4"),
//            new projetbasededonnee.Data.Experience("1", "1", "3", "6", "6")    
        );
   
   
}
