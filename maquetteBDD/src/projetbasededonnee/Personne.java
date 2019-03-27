/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

/**
 *
 * @author Ludivine
 */
public class Personne {
    
    private String prenom; 
    private String nom; 
    private String email; 
    private String fonction; 
    
    /**
     * 
     * @param prenom
     * @param nom
     * @param email 
     */
    public Personne(String prenomP, String nomP, String emailP, String fonctionP){
        prenom = prenomP; 
        nom=nomP;
        email=emailP;
        fonction=fonctionP;   
    }
    
    /**
     * 
     * @param prenomP 
     */
    public void setPrenom(String prenomP){
        prenom=prenomP; 
    }
    
    /**
     * 
     * @param nomP 
     */
    public void setNom(String nomP){
        nom=nomP; 
    }
    
    /**
     * 
     * @param EmailP 
     */
    public void setEmail(String EmailP){
        email=EmailP; 
    }
    
    /**
     * 
     * @param fonctionP 
     */
    public void setFonction(String fonctionP){
        fonction=fonctionP; 
    }
    
}
