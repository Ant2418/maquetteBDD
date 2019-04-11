/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee.Data;

import javafx.scene.control.Button;

/**
 *
 * @author Antoine
 */
public class Laborantin {
    
    private Integer id_plaque; 
    private String Type_plaque;
    private Integer puits_dispo;
 
    private Integer id_exp;
    private String nom_exp;
    private Integer nb_n_uplet;
    private String type_ana;
    private String type_exp;
    private Integer nb_puits;
    
    
    /**
     * 
     */
    
    public Laborantin(Integer id_plaque, String Type_plaque, Integer puits_dispo) {
        this.id_plaque = id_plaque;
        this.Type_plaque = Type_plaque;
        this.puits_dispo = puits_dispo;
    }

    public void setId_plaque(Integer id_plaque) {
        this.id_plaque = id_plaque;
    }

    public void setType_plaque(String Type_plaque) {
        this.Type_plaque = Type_plaque;
    }

    public void setPuits_dispo(Integer puits_dispo) {
        this.puits_dispo = puits_dispo;
    }


    public Integer getId_plaque() {
        return id_plaque;
    }

    public String getType_plaque() {
        return Type_plaque;
    }

    public Integer getPuits_dispo() {
        return puits_dispo;
    }
    
    /**
     * 
     */
    
    /**
     * Constructeur pour le tableau expérience à renouveler du laborantin
     * @param id_exp
     * @param nom_exp
     * @param id_n_uplet
     * @param type_ana
     * @param type_exp
     * @param nb_puits
     */
    public Laborantin(Integer id_exp, String nom_exp, Integer id_n_uplet, String type_ana, String type_exp, Integer nb_puits) {
        this.id_exp = id_exp;
        this.nom_exp = nom_exp;
        this.nb_n_uplet = id_n_uplet;
        this.type_ana = type_ana;
        this.type_exp = type_exp;
        this.nb_puits = nb_puits;
    }
    
    public Integer getNb_n_uplet() {
        return nb_n_uplet;
    }

    public void setNb_n_uplet(Integer nb_n_uplet) {
        this.nb_n_uplet = nb_n_uplet;
    }
    
    public Integer getId_exp() {
        return id_exp;
    }

    public void setId_exp(Integer id_exp) {
        this.id_exp = id_exp;
    }

    public String getNom_exp() {
        return nom_exp;
    }

    public void setNom_exp(String nom_exp) {
        this.nom_exp = nom_exp;
    }

    public Integer getId_n_uplet() {
        return nb_n_uplet;
    }

    public void setId_n_uplet(Integer id_n_uplet) {
        this.nb_n_uplet = id_n_uplet;
    }

    public String getType_ana() {
        return type_ana;
    }

    public void setType_ana(String type_ana) {
        this.type_ana = type_ana;
    }

    public String getType_exp() {
        return type_exp;
    }

    public void setType_exp(String type_exp) {
        this.type_exp = type_exp;
    }

    public Integer getNb_puits() {
        return nb_puits;
    }

    public void setNb_puits(Integer nb_puits) {
        this.nb_puits = nb_puits;
    }
    

}
