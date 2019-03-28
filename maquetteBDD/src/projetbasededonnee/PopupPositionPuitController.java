/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import projetbasededonnee.Data.Experience;

/**
 * FXML Controller class
 *
 * @author Antoine
 */
public class PopupPositionPuitController implements Initializable {

    @FXML    private TableView<Experience> tableVisuPosPuits;
    @FXML    private TableColumn<Experience, String> numPlaqueCol;
    @FXML    private TableColumn<Experience, String> numReplicatCol;
    @FXML    private TableColumn<Experience, String> numPuitCol;
    @FXML    private TableColumn<Experience, String> positionCol;
    @FXML    private TableColumn<Experience, String> positionLigneCol;
    @FXML    private TableColumn<Experience, String> positionColonneCol;

    private Connexion laCo;

    public void setLaCo(Connexion laCo) {
        this.laCo = laCo;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation des comboBox
        ObservableList<String> ligneListe =  FXCollections.observableArrayList();
        for(int i=2019; i>=1980; i--)
        {
            ligneListe.add(Integer.toString(i));
        }
        
        ObservableList<String> colonneListe =  FXCollections.observableArrayList();
        for(int i=2019; i>=1980; i--)
        {
            colonneListe.add(Integer.toString(i));
        }
        
        
        // initialisation des colonnes du tableau
        numPlaqueCol.setCellValueFactory(new PropertyValueFactory<>("numPlaque"));
        numReplicatCol.setCellValueFactory(new PropertyValueFactory<>("numReplicat"));
        numPuitCol.setCellValueFactory(new PropertyValueFactory<>("numPuit"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionLigneCol.setCellValueFactory(new PropertyValueFactory<>("positionLigne"));
        positionColonneCol.setCellValueFactory(new PropertyValueFactory<>("positionColonne"));
        
        // Pour rendre éditable les colonnes
        positionColonneCol.setCellFactory(ComboBoxTableCell.forTableColumn(colonneListe));
        positionLigneCol.setCellFactory(ComboBoxTableCell.forTableColumn(ligneListe));
        
        //Ajout des data a la table
        tableVisuPosPuits.setItems(projetbasededonnee.Data.Data.puitListe1);

        // Permet d'éditer les cellules du tableau
        tableVisuPosPuits.setEditable(true);
    }    
}
