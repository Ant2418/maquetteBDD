/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Antoine
 */
public class Experience {
    private String numExp;
    private String nomExp;
    private String etat;
    private String lab;
    private String typeExp;
    private String typeAna;
    private String duree;
    private String nuplet;
    private String puit;
    private String totalPuit;
    private CheckBox checkbox;

    private String replicat;
    private String agentBio;
    private String qteAgentBio;
    private String typeCell;
    private String qteCell;
    private String plaque;
    private String photometre;

   private String tableVisuPosPuits;
   private String numPlaque;
   private String numReplicat;
   private String numPuit;
   private String position;
   private String positionLigne;
   private String positionColonne;
   
    public Experience(String numPlaque, String numReplicat, String numPuit, String positionLigne, String positionColonne) {
        this.numPlaque = numPlaque;
        this.numReplicat = numReplicat;
        this.numPuit = numPuit;
        this.positionLigne = positionLigne;
        this.positionColonne = positionColonne;
    }
    
    public Experience(String replicat, String agentBio, String qteAgentBio, String typeCell, String qteCell, String plaque, String photometre) {
        this.replicat = replicat;
        this.agentBio = agentBio;
        this.qteAgentBio = qteAgentBio;
        this.typeCell = typeCell;
        this.qteCell = qteCell;
        this.plaque = plaque;
        this.photometre = photometre;
    }
    
    public Experience(String numExp, String nomExp, String etat, String lab, String typeExp, String typeAna, String duree, String nuplet, String puit, String totalPuit) {
        this.numExp = numExp;
        this.nomExp = nomExp;
        this.etat = etat;
        this.lab = lab;
        this.typeExp = typeExp;
        this.typeAna = typeAna;
        this.duree = duree;
        this.nuplet = nuplet;
        this.puit = puit;
        this.totalPuit = totalPuit;
        this.checkbox = new CheckBox();
    }
    
    
    
}
