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
    /**
     * Liste des experiences de plusieurs chercheur 
     * Permet au laborantin de choisir les expériences qu'il veut ajouter
     * a ses expériences
     */   
    public static ObservableList<projetbasededonnee.Data.Experience> expListe =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "Dosage BSA", "En attente", "SMITH Ludivine ", "Toxicité", "Colorimétrique", "10", "3", "3", "9"),
            new projetbasededonnee.Data.Experience("2", "Dosage PAL", "En attente", "DURANT Bernard ", "Toxicité", "Colorimétrique", "20", "3", "5", "15"),
            new projetbasededonnee.Data.Experience("3", "Dosage Hmg", "A renouveler", "JEAN Lucas ", "Toxicité", "Colorimétrique", "15", "3", "2", "6")
        );
    
    public static ObservableList<projetbasededonnee.Data.Experience> expCherListe =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "Dosage BSA", "En attente", " - ", "Toxicité", "Colorimétrique", "10", "3", "3", "9"),
            new projetbasededonnee.Data.Experience("2", "Dosage PAL", "En attente", " - ", "Toxicité", "Colorimétrique", "20", "3", "5", "15"),
            new projetbasededonnee.Data.Experience("3", "Dosage Hmg", "Terminé", "DUPONT Anaïs", "Toxicité", "Colorimétrique", "15", "3", "2", "6")
        );
    
    /**
     * Liste des experiences du laborantin
     */
    public static ObservableList<projetbasededonnee.Data.Experience> expLabListeAR =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "Dosage Hbu", "Christian LEDU", "Toxicité", "Colorimétrique", "10", "5", "4", "20")
        );
    public static ObservableList<projetbasededonnee.Data.Experience> expLabListeEA =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "Dosage DTT", "Christian LEDU", "Toxicité", "Colorimétrique", "10", "5", "4", "20")
        );
    
    /**
     * Liste des replicats d'une experience
     */
    public static ObservableList<projetbasededonnee.Data.Experience> replicatListe =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "Befiradol", "100", "mamalienne", "200", "1", "1"),
            new projetbasededonnee.Data.Experience("2", "Befiradol", "120", "mamalienne", "200", "", ""),
            new projetbasededonnee.Data.Experience("3", "Befiradol", "140", "mamalienne", "200", "", "")
        );
    
    public static ObservableList<projetbasededonnee.Data.Experience> puitListe1 =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "1", "1", "1", "1"),
            new projetbasededonnee.Data.Experience("1", "1", "2", "1", "2"),
            new projetbasededonnee.Data.Experience("1", "1", "3", "1", "3")    
        );
    
    public static ObservableList<projetbasededonnee.Data.Experience> puitListe2 =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "1", "1", "1", "4"),
            new projetbasededonnee.Data.Experience("1", "1", "2", "1", "5"),
            new projetbasededonnee.Data.Experience("1", "1", "3", "1", "6")    
        );
    
    public static ObservableList<projetbasededonnee.Data.Experience> puitListe3 =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "1", "1", "2", "3"),
            new projetbasededonnee.Data.Experience("1", "1", "2", "2", "4"),
            new projetbasededonnee.Data.Experience("1", "1", "3", "6", "6")    
        );
   
   
}
