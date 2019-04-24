/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

/**
 * Classe qui permet de créer des expériences nécessaires pour le laborantin
 * Elle peut contenir des expériences ou des uplets
 * @author Antoine et Ludivine
 * @version 24/04/2019
 */
public class Laborantin {
    
    private Integer idPlaque; 
    private String typePlaque;
    private Integer puitsDispo;
 
    private Integer idExp;
    private String nomExp;
    private Integer nbUplet;
    private String typeAna;
    private String typeExp;
    private Integer nbPuits;
    
    private Integer x; 
    private Integer y; 
    private String agentBio; 
    private Integer qteAgentBio; 
    private String cellule; 
    private Integer qteCellule; 
    

    /**
     * Constructeur d'un laborantin qui permet créer un n_uplet avec sa position
     * son agent biologique et les cellules
     * @param x posiiton x sur la plaque
     * @param y position y sur la plaque
     * @param agentBio nom de l'agent bio
     * @param qteAgentBio quantité de l'agent bio
     * @param cellule nom des cellules
     * @param qteCellule quantité des cellules
     */
    public Laborantin(Integer x, Integer y, String agentBio, Integer qteAgentBio, String cellule, Integer qteCellule) {
        this.x = x;
        this.y = y;
        this.agentBio = agentBio;
        this.qteAgentBio = qteAgentBio;
        this.cellule = cellule;
        this.qteCellule = qteCellule;
    }

    
    
    /**
     * Constructeur d'un laborantin qui permet d'ajouter dans le tableau plaque
     * Permet de créer des plaques
     * @param idPlaque identifiant de la plaque
     * @param typePlaque type de plaque
     * @param puitsDispo puit disponible sur la plaque
     */
    public Laborantin(Integer idPlaque, String typePlaque, Integer puitsDispo) {
        this.idPlaque = idPlaque;
        this.typePlaque = typePlaque;
        this.puitsDispo = puitsDispo;
    }

    /**
     * Constructeur pour le tableau expérience à renouveler ou en attente du laborantin
     * @param idExp identification de l'expérience  
     * @param nomExp nom de l'expérience
     * @param nbUplet nombre de uplet
     * @param typeAna type d'analyse
     * @param typeExp type d'expérience
     * @param nbPuits nombre de puit par uplet
     */
    public Laborantin(Integer idExp, String nomExp, Integer nbUplet, String typeAna, String typeExp, Integer nbPuits) {
        this.idExp = idExp;
        this.nomExp = nomExp;
        this.nbUplet = nbUplet;
        this.typeAna = typeAna;
        this.typeExp = typeExp;
        this.nbPuits = nbPuits;
    }
    
    /**
     * Getter
     * @return x 
     */
    public Integer getX() {
        return x;
    }

    /**
     * Setter de la position x
     * @param x 
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Getter
     * @return y
     */
    public Integer getY() {
        return y;
    }

    /**
     * Setter pour la position Y 
     * @param y 
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * Getter pour le nom de l'agent bio
     * @return agentBio
     */
    public String getAgentBio() {
        return agentBio;
    }

    /**
     * Setter pour le nom de l'agent bio
     * @param agentBio 
     */
    public void setAgentBio(String agentBio) {
        this.agentBio = agentBio;
    }

    /**
     * Getter 
     * @return qteAgentBio
     */
    public Integer getQteAgentBio() {
        return qteAgentBio;
    }

    /**
     * Setter pour la quantité d'agentbio
     * @param qteAgentBio 
     */
    public void setQteAgentBio(Integer qteAgentBio) {
        this.qteAgentBio = qteAgentBio;
    }

    /**
     * Getter pour le nom de cellule
     * @return cellule
     */
    public String getCellule() {
        return cellule;
    }

    /**
     * Setter pour le nom de la cellule
     * @param cellule 
     */
    public void setCellule(String cellule) {
        this.cellule = cellule;
    }

    /**
     * Getter
     * @return qteCellule
     */
    public Integer getQteCellule() {
        return qteCellule;
    }

    /**
     * Setter pour la quantité de cellule
     * @param qteCellule 
     */
    public void setQteCellule(Integer qteCellule) {
        this.qteCellule = qteCellule;
    }

    /**
     * Setter pour l'identifiant de la plaque
     * @param idPlaque 
     */
    public void setIdPlaque(Integer idPlaque) {
        this.idPlaque = idPlaque;
    }

    /**
     * Setter pour le type de la plaque
     * @param typePlaque 
     */
    public void setTypePlaque(String typePlaque) {
        this.typePlaque = typePlaque;
    }

    /**
     * Setter pour le nombre de puits disponibles
     * @param puitsDispo 
     */
    public void setPuitsDispo(Integer puitsDispo) {
        this.puitsDispo = puitsDispo;
    }

    /**
     * Getter
     * @return idPlaque 
     */
    public Integer getIdPlaque() {
        return idPlaque;
    }

    /**
     * Getter
     * @return typePlaque 
     */
    public String getTypePlaque() {
        return typePlaque;
    }

    /**
     * Getter
     * @return puitsDispo 
     */
    public Integer getPuitsDispo() {
        return puitsDispo;
    }
    
    /**
     * Getter
     * @return nbUplet 
     */
    public Integer getNbUplet() {
        return nbUplet;
    }

    /**
     * Setter pour le nombre de Uplet par expérience
     * @param nbUplet 
     */
    public void setNbUplet(Integer nbUplet) {
        this.nbUplet = nbUplet;
    }
    
    /**
     * Getter pour l'identifiant de l'expérience
     * @return idExp
     */
    public Integer getIdExp() {
        return idExp;
    }

    /**
     * Setter pour l'identifiant de l'expérience
     * @param idExp 
     */
    public void setIdExp(Integer idExp) {
        this.idExp = idExp;
    }

    /**
     * Getter 
     * @return nomExp
     */
    public String getNomExp() {
        return nomExp;
    }

    /**
     * Setter pour le nom de l'expérience
     * @param nomExp 
     */
    public void setNomExp(String nomExp) {
        this.nomExp = nomExp;
    }

    /**
     * Getter pour le type d'analyse
     * @return typeAna
     */
    public String getTypeAna() {
        return typeAna;
    }

    /**
     * Setter pour le type d'analyse
     * @param typeAna 
     */
    public void setTypeAna(String typeAna) {
        this.typeAna = typeAna;
    }

    /**
     * Getter
     * @return typeExp 
     */
    public String getTypeExp() {
        return typeExp;
    }

    /**
     * Setter pour le type d'expérience
     * @param typeExp 
     */
    public void setTypeExp(String typeExp) {
        this.typeExp = typeExp;
    }

    /**
     * Getter pour le nombre de puits
     * @return nbPuits
     */
    public Integer getNbPuits() {
        return nbPuits;
    }

    /**
     * Setter pour le nombre de puits
     * @param nbPuits 
     */
    public void setNbPuits(Integer nbPuits) {
        this.nbPuits = nbPuits;
    }
    

}
