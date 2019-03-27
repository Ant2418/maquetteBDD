/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class de Laborantin.fxml
 *
 * @author Antoine and Ludivine
 */
public class LaborantinController implements Initializable {

    // Attributs du menu du laborantin
    @FXML    private VBox menuVBox;
    @FXML    private ImageView deconnexionIV;
    @FXML    private ImageView home;
    @FXML    private ImageView experience;
    @FXML    private ImageView resultat;
    
    // Page d'accueil du laborantin
    @FXML    private AnchorPane homePageLab;
    @FXML    private VBox titleAcceuil;
    @FXML    private TableView<?> tableAccueil;
    @FXML    private TableColumn<?, ?> numExpCol;
    @FXML    private TableColumn<?, ?> nomExpCol;
    @FXML    private TableColumn<?, ?> etatCol;
    @FXML    private TableColumn<?, ?> labCol;
    @FXML    private TableColumn<?, ?> typeExpCol;
    @FXML    private TableColumn<?, ?> typeAnaCol;
    @FXML    private TableColumn<?, ?> dureeCol;
    @FXML    private TableColumn<?, ?> nupletCol;
    @FXML    private TableColumn<?, ?> puitCol;
    @FXML    private TableColumn<?, ?> totalPuitCol;
    @FXML    private TableColumn<?, ?> checkCol;
    
    // Page des expériences du laborantin 
    @FXML    private AnchorPane expLabPage;
    @FXML    private VBox titleAcceuil2;
    @FXML    private Tab tableARenouveller;
    @FXML    private TableView<?> tableLabAR;
    @FXML    private TableColumn<?, ?> numExpLabARCol;
    @FXML    private TableColumn<?, ?> nomExpLabARCol;
    @FXML    private TableColumn<?, ?> chercheurLabARCol;
    @FXML    private TableColumn<?, ?> typeExpLabARCol;
    @FXML    private TableColumn<?, ?> typeAnaLabARCol;
    @FXML    private TableColumn<?, ?> dureeLabARCol;
    @FXML    private TableColumn<?, ?> nupletLabARCol;
    @FXML    private TableColumn<?, ?> puitLabARCol;
    @FXML    private TableColumn<?, ?> totalPuitLabARCol;
    @FXML    private TableColumn<?, ?> checkLabARCol;
    @FXML    private Tab tableEnAttente;
    @FXML    private TableView<?> tableLabEA;
    @FXML    private TableColumn<?, ?> numExpLabEACol;
    @FXML    private TableColumn<?, ?> nomExpLabEACol;
    @FXML    private TableColumn<?, ?> chercheurLabEACol;
    @FXML    private TableColumn<?, ?> typeExpLabEACol;
    @FXML    private TableColumn<?, ?> typeAnaLabEACol;
    @FXML    private TableColumn<?, ?> dureeLabEACol;
    @FXML    private TableColumn<?, ?> nupletLabEACol;
    @FXML    private TableColumn<?, ?> puitLabEACol;
    @FXML    private TableColumn<?, ?> totalPuitLabEACol;
    @FXML    private TableColumn<?, ?> checkLabCol1;
    @FXML    private Tab tableEnCours;
    @FXML    private TableView<?> tableLabEC;
    @FXML    private TableColumn<?, ?> numExpLabECCol;
    @FXML    private TableColumn<?, ?> nomExpLabECCol1;
    @FXML    private TableColumn<?, ?> chercheurLabECCol1;
    @FXML    private TableColumn<?, ?> typeExpLabECCol1;
    @FXML    private TableColumn<?, ?> typeAnaLabECCol1;
    @FXML    private TableColumn<?, ?> dureeLabECCol1;
    @FXML    private TableColumn<?, ?> nupletLabECCol1;
    @FXML    private TableColumn<?, ?> puitLabECCol1;
    @FXML    private TableColumn<?, ?> totalPuitLabECCol1;
    @FXML    private Tab TableTermine;
    @FXML    private Tab TableFacture;
    
    // Page pour lancer une expérience
    @FXML    private AnchorPane visuExpPage;
    @FXML    private ImageView validerIV;
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
    
    // Page de validation des résultats
    @FXML    private AnchorPane validationPage;
    @FXML    private VBox titleAcceuil11;
    @FXML    private ImageView refuserIV;
    @FXML    private ImageView validerResultat;
    @FXML    private TableView<?> tableResultat;
    @FXML    private TableColumn<?, ?> resultExpCol;
    @FXML    private TableColumn<?, ?> resultatReplicatCol;
    @FXML    private TableColumn<?, ?> decisionCol;
    @FXML    private TableColumn<?, ?> couleurCol;
    @FXML    private TableColumn<?, ?> moyCOl;
    @FXML    private TableColumn<?, ?> moyRougeCol;
    @FXML    private TableColumn<?, ?> moyVertCol;
    @FXML    private TableColumn<?, ?> moyBleuCol;
    @FXML    private TableColumn<?, ?> moyTransCol;
    @FXML    private TableColumn<?, ?> sdCol;
    @FXML    private TableColumn<?, ?> sdRougeCol1;
    @FXML    private TableColumn<?, ?> sdVertCol1;
    @FXML    private TableColumn<?, ?> sdBleuCol1;
    @FXML    private TableColumn<?, ?> sdTransCol1;
    @FXML    private TableColumn<?, ?> resultExpCol1;
    
    @FXML    private Button lancerExpButton;
    
    private ProjetBaseDeDonnee main;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homePageLab.setVisible(true);
        expLabPage.setVisible(false); 
        visuExpPage.setVisible(false);
        validationPage.setVisible(false);
//        lancerExpButton.setDisable(true);
    
    

    }      

    /**
     * Affichage de scène connexion.fxml
     * @param event
     * @throws IOException 
     */
    @FXML
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
     * Affichage du panel homePageLab, l'accueil du laborantin
     * @param event
     * @throws IOException 
     */
    @FXML
    public void homeEvent(MouseEvent event) throws IOException {
        homePageLab.setVisible(true);
        expLabPage.setVisible(false); 
        visuExpPage.setVisible(false);
        validationPage.setVisible(false);
    } 

    /**
     * Affichage du panel expLabPage, la page où sont affiché les 
     * expérience de laborantin
     * @param event
     * @throws IOException 
     */
    @FXML
    public void experienceLabEvent(MouseEvent event) throws IOException {
        homePageLab.setVisible(false);
        expLabPage.setVisible(true); 
        visuExpPage.setVisible(false);
        validationPage.setVisible(false);
    } 
    
    /**
     * Affichage du panel visuExpPage, qui permet de lancer une expérience
     * @param event
     * @throws IOException 
     */
    public void lancerExpEvent(ActionEvent event)throws IOException {
        homePageLab.setVisible(false);
        expLabPage.setVisible(false); 
        visuExpPage.setVisible(true);
        validationPage.setVisible(false);
        
    }
    
    /**
     * Affichage du panel validationPage, qui permet de valider une expérience
     * @param event
     * @throws IOException 
     */
    @FXML
    public void validationEvent(MouseEvent event)throws IOException {
        homePageLab.setVisible(false);
        expLabPage.setVisible(false); 
        visuExpPage.setVisible(false);
        validationPage.setVisible(true);
    }
        
    public void setMain(ProjetBaseDeDonnee mainPBD)
    {
        main = mainPBD;
    }
    
}
