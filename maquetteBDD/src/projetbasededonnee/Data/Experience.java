/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Antoine
 */
public class Experience {
    // Pour la table d'acceuil des laborantins
    // liste expListe
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

    // attributs pour la table des experiences du laborantin
    // liste expLabListe
    private String numExpLabAR;
    private String nomExpLabAR;
    private String chercheurLabAR;
    private String typeExpLabAR;
    private String typeAnaLabAR;
    private String dureeLabAR;
    private String nupletLabAR;
    private String puitLabAR;
    private String totalPuitLabAR;
    private String checkLabAR;
    
    // attributs pour la tableNUplet
    // liste lancerExpListe
    private String replicat;
    private String agentBio;
    private String qteAgentBio;
    private String typeCell;
    private String qteCell;
    private String plaque;
    private String photometre;

    //attributs pour la table
    private String numPlaque;
    private String numReplicat;
    private String numPuit;
    private String position;
    private String positionLigne;
    private String positionColonne;
   
    /**
     * Constructeur pour le tableau tableVisuPosPuits
     * Qui est un tableau dans une pop-up
     * @param numPlaque
     * @param numReplicat
     * @param numPuit
     * @param positionLigne
     * @param positionColonne 
     */
    public Experience(String numPlaque, String numReplicat, String numPuit, String positionLigne, String positionColonne) {
        this.numPlaque = numPlaque;
        this.numReplicat = numReplicat;
        this.numPuit = numPuit;
        this.positionLigne = positionLigne;
        this.positionColonne = positionColonne;
    }
    
    /**
     * Constructeur pour le tableau des replicats / n-uplets
     * Tableau pour le laborantin
     * @param replicat
     * @param agentBio
     * @param qteAgentBio
     * @param typeCell
     * @param qteCell
     * @param plaque
     * @param photometre 
     */
    public Experience(String replicat, String agentBio, String qteAgentBio, String typeCell, String qteCell, String plaque, String photometre) {
        this.replicat = replicat;
        this.agentBio = agentBio;
        this.qteAgentBio = qteAgentBio;
        this.typeCell = typeCell;
        this.qteCell = qteCell;
        this.plaque = plaque;
        this.photometre = photometre;
    }
    
    /**
     * Constructeur pour le tableau d'accueil du laborantin
     * @param numExp
     * @param nomExp
     * @param etat
     * @param lab
     * @param typeExp
     * @param typeAna
     * @param duree
     * @param nuplet
     * @param puit
     * @param totalPuit 
     */
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
    
    /**
     * Constructeur pour le tableau des experience 'A Renouveller' pour le laborantin
     * 
     * @param numExpLabAR
     * @param nomExpLabAR
     * @param chercheurLabAR
     * @param typeExpLabAR
     * @param typeAnaLabAR
     * @param dureeLabAR
     * @param nupletLabAR
     * @param puitLabAR
     * @param totalPuitLabAR 
     */
    public Experience(String numExpLabAR, String nomExpLabAR, String chercheurLabAR, String typeExpLabAR, String typeAnaLabAR, String dureeLabAR, String nupletLabAR, String puitLabAR, String totalPuitLabAR) {
        this.numExpLabAR = numExpLabAR;
        this.nomExpLabAR = nomExpLabAR;
        this.chercheurLabAR = chercheurLabAR;
        this.typeExpLabAR = typeExpLabAR;
        this.typeAnaLabAR = typeAnaLabAR;
        this.dureeLabAR = dureeLabAR;
        this.nupletLabAR = nupletLabAR;
        this.puitLabAR = puitLabAR;
        this.totalPuitLabAR = totalPuitLabAR;
        this.checkbox = new CheckBox();
    }
    
    /**
     * =========================================================================
     * 
     * =========================================================================
     */
    
    
    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }
    
      public String getNumExp() {
        return numExp;
    }

    public void setNumExp(String numExp) {
        this.numExp = numExp;
    }

    public String getNomExp() {
        return nomExp;
    }

    public void setNomExp(String nomExp) {
        this.nomExp = nomExp;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getTypeExp() {
        return typeExp;
    }

    public void setTypeExp(String typeExp) {
        this.typeExp = typeExp;
    }

    public String getTypeAna() {
        return typeAna;
    }

    public void setTypeAna(String typeAna) {
        this.typeAna = typeAna;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getNuplet() {
        return nuplet;
    }

    public void setNuplet(String nuplet) {
        this.nuplet = nuplet;
    }

    public String getPuit() {
        return puit;
    }

    public void setPuit(String puit) {
        this.puit = puit;
    }

    public String getTotalPuit() {
        return totalPuit;
    }

    public void setTotalPuit(String totalPuit) {
        this.totalPuit = totalPuit;
    }

    public String getReplicat() {
        return replicat;
    }

    public void setReplicat(String replicat) {
        this.replicat = replicat;
    }

    public String getAgentBio() {
        return agentBio;
    }

    public void setAgentBio(String agentBio) {
        this.agentBio = agentBio;
    }

    public String getQteAgentBio() {
        return qteAgentBio;
    }

    public void setQteAgentBio(String qteAgentBio) {
        this.qteAgentBio = qteAgentBio;
    }

    public String getTypeCell() {
        return typeCell;
    }

    public void setTypeCell(String typeCell) {
        this.typeCell = typeCell;
    }

    public String getQteCell() {
        return qteCell;
    }

    public void setQteCell(String qteCell) {
        this.qteCell = qteCell;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getPhotometre() {
        return photometre;
    }

    public void setPhotometre(String photometre) {
        this.photometre = photometre;
    }
   

    public String getNumPlaque() {
        return numPlaque;
    }

    public void setNumPlaque(String numPlaque) {
        this.numPlaque = numPlaque;
    }

    public String getNumReplicat() {
        return numReplicat;
    }

    public void setNumReplicat(String numReplicat) {
        this.numReplicat = numReplicat;
    }

    public String getNumPuit() {
        return numPuit;
    }

    public void setNumPuit(String numPuit) {
        this.numPuit = numPuit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionLigne() {
        return positionLigne;
    }

    public void setPositionLigne(String positionLigne) {
        this.positionLigne = positionLigne;
    }

    public String getPositionColonne() {
        return positionColonne;
    }

    public void setPositionColonne(String positionColonne) {
        this.positionColonne = positionColonne;
    }

    /**
     * =========================================================================
     * Getter et Setter pour la table des experiences du Laborantin
     * =========================================================================
     */
    public String getNumExpLabAR() {
        return numExpLabAR;
    }

    public void setNumExpLabAR(String numExpLabAR) {
        this.numExpLabAR = numExpLabAR;
    }

    public String getNomExpLabAR() {
        return nomExpLabAR;
    }

    public void setNomExpLabAR(String nomExpLabAR) {
        this.nomExpLabAR = nomExpLabAR;
    }

    public String getChercheurLabAR() {
        return chercheurLabAR;
    }

    public void setChercheurLabAR(String chercheurLabAR) {
        this.chercheurLabAR = chercheurLabAR;
    }

    public String getTypeExpLabAR() {
        return typeExpLabAR;
    }

    public void setTypeExpLabAR(String typeExpLabAR) {
        this.typeExpLabAR = typeExpLabAR;
    }

    public String getTypeAnaLabAR() {
        return typeAnaLabAR;
    }

    public void setTypeAnaLabAR(String typeAnaLabAR) {
        this.typeAnaLabAR = typeAnaLabAR;
    }

    public String getDureeLabAR() {
        return dureeLabAR;
    }

    public void setDureeLabAR(String dureeLabAR) {
        this.dureeLabAR = dureeLabAR;
    }

    public String getNupletLabAR() {
        return nupletLabAR;
    }

    public void setNupletLabAR(String nupletLabAR) {
        this.nupletLabAR = nupletLabAR;
    }

    public String getPuitLabAR() {
        return puitLabAR;
    }

    public void setPuitLabAR(String puitLabAR) {
        this.puitLabAR = puitLabAR;
    }

    public String getTotalPuitLabAR() {
        return totalPuitLabAR;
    }

    public void setTotalPuitLabAR(String totalPuitLabAR) {
        this.totalPuitLabAR = totalPuitLabAR;
    }

    public String getCheckLabAR() {
        return checkLabAR;
    }

    public void setCheckLabAR(String checkLabAR) {
        this.checkLabAR = checkLabAR;
    }
}
