/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

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
    //private CheckBox checkbox;

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
        //this.checkbox = new CheckBox();
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

    public String getTableVisuPosPuits() {
        return tableVisuPosPuits;
    }

    public void setTableVisuPosPuits(String tableVisuPosPuits) {
        this.tableVisuPosPuits = tableVisuPosPuits;
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

    
}
