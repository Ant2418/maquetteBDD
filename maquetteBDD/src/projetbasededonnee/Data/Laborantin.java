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

}
