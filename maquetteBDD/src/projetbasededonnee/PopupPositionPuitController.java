/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Antoine
 */
public class PopupPositionPuitController implements Initializable {

    @FXML    private TableView<?> tableVisuPosPuits;
    @FXML    private TableColumn<?, ?> numPlaqueCol;
    @FXML    private TableColumn<?, ?> numReplicatCol;
    @FXML    private TableColumn<?, ?> numPuitCol;
    @FXML    private TableColumn<?, ?> positionCol;
    @FXML    private TableColumn<?, ?> positionLigneCol;
    @FXML    private TableColumn<?, ?> positionColonneCol;

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
        // TODO
    }    
}
