/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

import java.sql.Date;

/**
 *
 * @author Ludivine
 */
public class AccueilChercheur {
    private Integer id_exp; 
    private String nom_exp; 
    private String etat_exp; 
    private String nom; 
    private String prenom; 
    private String type_exp; 
    private String type_analyse; 
    private Integer nb_puit; 
    private Date horo_deb; 
    private Date horo_fin;
    private Integer nb_replicat;

    public void setNb_replicat(Integer nb_replicat) {
        this.nb_replicat = nb_replicat;
    }

    public Integer getNb_replicat() {
        return nb_replicat;
    }

    public AccueilChercheur(Integer id_exp, String nom_exp, String etat_exp, String nom, String type_exp, String type_analyse, Integer nb_replicat, Integer nb_puit, Date horo_deb, Date horo_fin) {
        this.id_exp = id_exp;
        this.nom_exp = nom_exp;
        this.etat_exp = etat_exp;
        this.nom = nom;
        this.type_exp = type_exp;
        this.type_analyse = type_analyse;
        this.nb_replicat = nb_replicat;
        this.nb_puit = nb_puit;
        this.horo_deb = horo_deb;
        this.horo_fin = horo_fin;
        
    }
    
    public void setId_exp(Integer id_exp) {
        this.id_exp = id_exp;
    }

    public void setNom_exp(String nom_exp) {
        this.nom_exp = nom_exp;
    }

    public void setEtat_exp(String etat_exp) {
        this.etat_exp = etat_exp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setType_exp(String type_exp) {
        this.type_exp = type_exp;
    }

    public void setType_analyse(String type_analyse) {
        this.type_analyse = type_analyse;
    }

    public void setNb_puit(Integer nb_puit) {
        this.nb_puit = nb_puit;
    }

    public void setHoro_deb(Date horo_deb) {
        this.horo_deb = horo_deb;
    }

    public void setHoro_fin(Date horo_fin) {
        this.horo_fin = horo_fin;
    }

    public Integer getId_exp() {
        return id_exp;
    }

    public String getNom_exp() {
        return nom_exp;
    }

    public String getEtat_exp() {
        return etat_exp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getType_exp() {
        return type_exp;
    }

    public String getType_analyse() {
        return type_analyse;
    }

    public Integer getNb_puit() {
        return nb_puit;
    }

    public Date getHoro_deb() {
        return horo_deb;
    }

    public Date getHoro_fin() {
        return horo_fin;
    }
    
}
