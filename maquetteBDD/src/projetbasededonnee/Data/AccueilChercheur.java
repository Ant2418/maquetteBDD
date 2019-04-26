/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

import java.sql.Date;

/**
 *
 * Classe accueilChercheur qui permet de faire des tableaux
 * @author Ludivine et Antoine
 * @version 24/04/2019
 */
public class AccueilChercheur {
    
    private Integer idExp; 
    private String nomExp; 
    private String etatExp; 
    private String nom; 
    private String prenom; 
    private String typeExp; 
    private String typeAnalyse; 
    private Integer nbPuit; 
    private Date horoDeb; 
    private Date horoFin;
    private Integer nbReplicat;
    private Integer idUplet; 
    private String nomAgentBio;
    private String nomCellule;
    private Integer qteAgentBio;
    private Integer qteCellule;


    /**
     * Constructeur pour dataUpletTotal / tableNUplet
     * @param idUplet identifiant de l'uplet
     * @param nomAgentBio nom de l'agent biologique
     * @param nomCellule nom de la cellule
     * @param quantiteAgentBio quantitee de d'agent biologique
     * @param quantiteCellule  quantitee de cellule
     */
    public AccueilChercheur(Integer idUplet, String nomAgentBio, String nomCellule, Integer quantiteAgentBio, Integer quantiteCellule) {
        this.idUplet = idUplet;
        this.nomAgentBio = nomAgentBio;
        this.nomCellule = nomCellule;
        this.qteAgentBio = quantiteAgentBio;
        this.qteCellule = quantiteCellule;
    }
    
    /**
     * Constructeur pour 
     * @see projetbasededonnee.AcceuilChercheurController#dataAccueil
     * @see tableAccueilChercheur
     * @param idExp identifiant de l'experience
     * @param nomExp nom de l'experience
     * @param etatExp etat de l'experience "En Cours", "Terminee", "En Attente", "Facturee", "A renouveler"
     * @param nom nom de l'experience
     * @param typeExp type d'experience "Immunologique", "Toxicologique"
     * @param typeAnalyse type d'analyse "Colorimetrique", "Opacimetrique"
     * @param nbReplicat nombre d'uplet
     * @param nbPuits nombre puits
     * @param horoDeb date de debut de l'experience format DD/MM/YYYY
     * @param horoFin date de fin de l'experience format DD/MM/YYYY
     */
    public AccueilChercheur(Integer idExp, String nomExp, String etatExp, String nom, String typeExp, String typeAnalyse, Integer nbReplicat, Integer nbPuits, Date horoDeb, Date horoFin) {
        this.idExp = idExp; 
        this.nomExp = nomExp;
        this.etatExp = etatExp;
        this.nom = nom;
        this.typeExp = typeExp;
        this.typeAnalyse = typeAnalyse;
        this.nbReplicat = nbReplicat;
        this.nbPuit = nbPuits;
        this.horoDeb = horoDeb;
        this.horoFin = horoFin;
        
    }

    /**
     * Constructeur qui permet de faire le tableau pour les expériences
     * @param idExp
     * @param nomExp
     * @param etatExp
     * @param typeExp
     * @param typeAnalyse 
     */
    public AccueilChercheur(Integer idExp, String nomExp, String etatExp, String typeExp, String typeAnalyse) {
        this.idExp = idExp;
        this.nomExp = nomExp;
        this.etatExp = etatExp;
        this.typeExp = typeExp;
        this.typeAnalyse = typeAnalyse;
    }
    
    /**
     * Setter du nom de l'agent biologique
     * @param nomAgentBio nom de l'agent biologique
     */
    public void setNomAgentBio(String nomAgentBio) {
        this.nomAgentBio = nomAgentBio;
    }

    /**
     * Setter du nom de la cellule
     * @param nomCellule nom de la cellule
     */
    public void setNomCellule(String nomCellule) {
        this.nomCellule = nomCellule;
    }

    /**
     *  setter
     * @param qteAgentBio quantitee d'agent biologique 
     */
    public void setQteAgentBio(Integer qteAgentBio) {
        this.qteAgentBio = qteAgentBio;
    }

    /**
     * setter
     * @param qteCellule quantitee de cellule
     */
    public void setQteCellule(Integer qteCellule) {
        this.qteCellule = qteCellule;
    }

    /**
     * getter
     * @return nomAgentBio
     */
    public String getNomAgentBio() {
        return nomAgentBio;
    }

    /**
     * getter
     * @return nomCellule
     */
    public String getNomCellule() {
        return nomCellule;
    }

    /**
     * getter
     * @return qteAgentBio
     */
    public Integer getQteAgentBio() {
        return qteAgentBio;
    }

    /**
     * getter
     * @return qteCellule
     */
    public Integer getQteCellule() {
        return qteCellule;
    }
    
    /**
     * setter
     * @param idUplet identifiant de l'uplet
     */
    public void setIdUplet(Integer idUplet) {
        this.idUplet = idUplet;
    }

    /**
     * getter
     * @return idUplet
     */
    public Integer getIdUplet() {
        return idUplet;
    }

    /**
     * setter 
     * @param nbReplicat nombre de replicat
     */
    public void setNbReplicat(Integer nbReplicat) {
        this.nbReplicat = nbReplicat;
    }

    /**
     * getter
     * @return nbReplicat
     */
    public Integer getNbReplicat() {
        return nbReplicat;
    }

    /**
     * setter 
     * @param idExp identifiant de l'experience
     */
    public void setIdExp(Integer idExp) {
        this.idExp = idExp;
    }

    /**
     * setter
     * @param nomExp nom de l'experience
     */
    public void setNomExp(String nomExp) {
        this.nomExp = nomExp;
    }

    /**
     * setter 
     * @param etatExp etat de l'experience "En Cours", "Terminee", "En Attente", "Facturee", "A renouveler"
     */
    public void setEtatExp(String etatExp) {
        this.etatExp = etatExp;
    }

    /**
     * setter 
     * @param nom de la personne qui fait l'experience
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * setter
     * @param prenom de la personne qui fait l'experience
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * setter
     * @param typeExp type d'experience "Colorimetrique", "Opacimetrique"
     */
    public void setTypeExp(String typeExp) {
        this.typeExp = typeExp;
    }

    /**
     * setter
     * @param typeAnalyse type d'analyse "Immunologique", "Toxicologique"
     */
    public void setTypeAnalyse(String typeAnalyse) {
        this.typeAnalyse = typeAnalyse;
    }

    /**
     * setter
     * @param nbPuit nombre de puit
     */
    public void setNbPuit(Integer nbPuit) {
        this.nbPuit = nbPuit;
    }

    /**
     * setter
     * @param horoDeb date de debut de l'experience Format DD/MM/YYYY
     */
    public void setHoroDeb(Date horoDeb) {
        this.horoDeb = horoDeb;
    }

    /**
     * setter
     * @param horoFin date de fin de l'experience Format DD/MM/YYYY
     */
    public void setHoroFin(Date horoFin) {
        this.horoFin = horoFin;
    }

    /**
     * getter
     * @return idExp
     */
    public Integer getIdExp() {
        return idExp;
    }

    /**
     * getter
     * @return nomExp
     */
    public String getNomExp() {
        return nomExp;
    }

    /**
     * getter
     * @return etatExp
     */
    public String getEtatExp() {
        return etatExp;
    }

    /**
     * getter
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * getter
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * getter
     * @return typeExp
     */
    public String getTypeExp() {
        return typeExp;
    }

    /**
     * getter
     * @return typeAnalyse
     */
    public String getTypeAnalyse() {
        return typeAnalyse;
    }

    /**
     * getter
     * @return nbPuit
     */
    public Integer getNbPuit() {
        return nbPuit;
    }

    /**
     * getter
     * @return horoDeb
     */
    public Date getHoroDeb() {
        return horoDeb;
    }

    /**
     * getter
     * @return horoFin
     */
    public Date getHoroFin() {
        return horoFin;
    }
    
}
