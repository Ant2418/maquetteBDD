/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquetteBDD;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class de Chercheur.fxml
 *
 * @author Ludivine and Antoine
 */
public class AcceuilChercheurController implements Initializable {

    @FXML    private VBox menuVBox;
    @FXML    private ImageView deconnexionIV;
    @FXML    private ImageView home;
    
    // Page d'accueil du chercheur
    @FXML    private AnchorPane homePageChercheur;
    @FXML    private TableView<String> tableViewAccueil; 
    @FXML    private VBox titleAcceuil;
    @FXML    private TableColumn<?, ?> numExpCol;
    @FXML    private TableColumn<?, ?> nomExpCol;
    @FXML    private TableColumn<?, ?> etatCol;
    @FXML    private TableColumn<?, ?> labCol;
    @FXML    private TableColumn<?, ?> typeExpCol;
    @FXML    private TableColumn<?, ?> nupletCol;
    @FXML    private TableColumn<?, ?> puitCol;
    
    @FXML    private AnchorPane ajoutExpPage;
    
    //¨Page pour ajouter des Uplets à une expérience
    @FXML    private AnchorPane AddUpletPage;
    @FXML    private Label typePlaqueLabel;
    @FXML    private Label dureeLabel;
    @FXML    private Label suiviLabel;
    @FXML    private Label frequenceLabel;
    @FXML    private Label nbPuitReplicatLabel;
    @FXML    private Label typeExpLabel;
    @FXML    private Label typeAnaLabel;
    @FXML    private Label alpha1Label;
    @FXML    private Label alpha2Label;
    @FXML    private Label alpha3Label;
    @FXML    private Label debutExpLabel;
    @FXML    private TableView<?> tableNUplet;
    @FXML    private TableColumn<?, ?> replicatCol;
    @FXML    private TableColumn<?, ?> agentBioCol;
    @FXML    private TableColumn<?, ?> qteAgentBioCol;
    @FXML    private TableColumn<?, ?> typeCellCol;
    @FXML    private TableColumn<?, ?> qteCellCol;
    @FXML    private TableColumn<?, ?> plaqueCol;
    @FXML    private TableColumn<?, ?> photometreCol;
    
    private Connection con;
    private ConnexionController maCo;
    private String prenom; 
    private String nom; 
    private ProjetBaseDeDonnee main;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homePageChercheur.setVisible(true);
        ajoutExpPage.setVisible(false); 
        AddUpletPage.setVisible(false);
    }      

    
    /**
     * Affichage de la page de connexion (connexion.fxml)
     * @param event
     * @throws IOException 
     */
    public void deconnexionEvent(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        Parent ajoutParent = FXMLLoader.load(getClass().getResource("connexion.fxml"));
        Scene ajoutSceneConn = new Scene(ajoutParent);
        
//        ConnexionController CCO= loader.getController();
//        CCO.setMain(main);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ajoutSceneConn);
        window.show();
    }

    /**
     * Affichage du panel homePageChercheur, l'accueil du chercheur
     * @param event
     * @throws IOException 
     */
    public void homeEvent(MouseEvent event) throws IOException {
        homePageChercheur.setVisible(true);
        ajoutExpPage.setVisible(false); 
        AddUpletPage.setVisible(false);
    } 

    /**
     * Affichage du panel AddUpletPage, la page permettant d'ajouter des 
     * réplicats (uplet) à une expérience
     * @param event
     * @throws IOException 
     */
    public void AddExpEvent(MouseEvent event) throws IOException {
        homePageChercheur.setVisible(false);
        ajoutExpPage.setVisible(true); 
        AddUpletPage.setVisible(false);
    }
    
    public void AddUpletEvent(MouseEvent event) throws IOException{
        homePageChercheur.setVisible(false);
        ajoutExpPage.setVisible(false);
        AddUpletPage.setVisible(true);
    }
    
    
    /**
     * Setter pour modifier le main
     * @param mainPBD 
     */
    public void setMain(ProjetBaseDeDonnee mainPBD)
    {
        this.main = mainPBD;
    }
}
