/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projetbasededonnee.Data.Experience;

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
    @FXML    private TableView<Experience> tableAccueil;
    @FXML    private TableColumn<Experience, String> numExpCol;
    @FXML    private TableColumn<Experience, String> nomExpCol;
    @FXML    private TableColumn<Experience, String> etatCol;
    @FXML    private TableColumn<Experience, String> labCol;
    @FXML    private TableColumn<Experience, String> typeExpCol;
    @FXML    private TableColumn<Experience, String> typeAnaCol;
    @FXML    private TableColumn<Experience, String> dureeCol;
    @FXML    private TableColumn<Experience, String> nupletCol;
    @FXML    private TableColumn<Experience, String> puitCol;
    @FXML    private TableColumn<Experience, String> totalPuitCol;
    @FXML    private TableColumn<Experience, String> checkCol;
    
    // Page des expériences du laborantin 
    @FXML    private AnchorPane expLabPage;
    @FXML    private VBox titleAcceuil2;
    @FXML    private Tab tableARenouveller;
    @FXML    private TableView<Experience> tableLabAR;
    @FXML    private TableColumn<Experience, String> numExpLabARCol;
    @FXML    private TableColumn<Experience, String> nomExpLabARCol;
    @FXML    private TableColumn<Experience, String> chercheurLabARCol;
    @FXML    private TableColumn<Experience, String> typeExpLabARCol;
    @FXML    private TableColumn<Experience, String> typeAnaLabARCol;
    @FXML    private TableColumn<Experience, String> dureeLabARCol;
    @FXML    private TableColumn<Experience, String> nupletLabARCol;
    @FXML    private TableColumn<Experience, String> puitLabARCol;
    @FXML    private TableColumn<Experience, String> totalPuitLabARCol;
    @FXML    private TableColumn<Experience, String> checkLabARCol;
    
    @FXML    private Tab tableEnAttente;
    @FXML    private TableView<?> tableLabEA;
    @FXML    private TableColumn<Experience, String> numExpLabEACol;
    @FXML    private TableColumn<Experience, String> nomExpLabEACol;
    @FXML    private TableColumn<Experience, String> chercheurLabEACol;
    @FXML    private TableColumn<Experience, String> typeExpLabEACol;
    @FXML    private TableColumn<Experience, String> typeAnaLabEACol;
    @FXML    private TableColumn<Experience, String> dureeLabEACol;
    @FXML    private TableColumn<Experience, String> nupletLabEACol;
    @FXML    private TableColumn<Experience, String> puitLabEACol;
    @FXML    private TableColumn<Experience, String> totalPuitLabEACol;
    @FXML    private TableColumn<Experience, String> checkLabCol1;
    @FXML    private Tab tableEnCours;
    @FXML    private TableView<?> tableLabEC;
    @FXML    private TableColumn<Experience, String> numExpLabECCol;
    @FXML    private TableColumn<Experience, String> nomExpLabECCol1;
    @FXML    private TableColumn<Experience, String> chercheurLabECCol1;
    @FXML    private TableColumn<Experience, String> typeExpLabECCol1;
    @FXML    private TableColumn<Experience, String> typeAnaLabECCol1;
    @FXML    private TableColumn<Experience, String> dureeLabECCol1;
    @FXML    private TableColumn<Experience, String> nupletLabECCol1;
    @FXML    private TableColumn<Experience, String> puitLabECCol1;
    @FXML    private TableColumn<Experience, String> totalPuitLabECCol1;
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
    @FXML    private TableView<Experience> tableNUplet;
    @FXML    private TableColumn<Experience, String> replicatCol;
    @FXML    private TableColumn<Experience, String> agentBioCol;
    @FXML    private TableColumn<Experience, String> qteAgentBioCol;
    @FXML    private TableColumn<Experience, String> typeCellCol;
    @FXML    private TableColumn<Experience, String> qteCellCol;
    @FXML    private TableColumn<Experience, String> plaqueCol;
    @FXML    private TableColumn<Experience, String> photometreCol;
    
    // Page de validation des résultats
    @FXML    private AnchorPane validationPage;
    @FXML    private VBox titleAcceuil11;
    @FXML    private ImageView refuserIV;
    @FXML    private ImageView validerResultat;
    @FXML    private TableView<?> tableResultat;
    @FXML    private TableColumn<Experience, String> resultExpCol;
    @FXML    private TableColumn<Experience, String> resultatReplicatCol;
    @FXML    private TableColumn<Experience, String> decisionCol;
    @FXML    private TableColumn<Experience, String> couleurCol;
    @FXML    private TableColumn<Experience, String> moyCOl;
    @FXML    private TableColumn<Experience, String> moyRougeCol;
    @FXML    private TableColumn<Experience, String> moyVertCol;
    @FXML    private TableColumn<Experience, String> moyBleuCol;
    @FXML    private TableColumn<Experience, String> moyTransCol;
    @FXML    private TableColumn<Experience, String> sdCol;
    @FXML    private TableColumn<Experience, String> sdRougeCol1;
    @FXML    private TableColumn<Experience, String> sdVertCol1;
    @FXML    private TableColumn<Experience, String> sdBleuCol1;
    @FXML    private TableColumn<Experience, String> sdTransCol1;
    @FXML    private TableColumn<Experience, String> resultExpCol1;
    
    @FXML    private Button lancerExpButton;
    
    private ProjetBaseDeDonnee main;
    private ObservableList<projetbasededonnee.Data.Experience> expListe =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "Dosage BSA", "En attente", " - ", "Toxicité", "Colorimétrique", "10", "3", "3", "9"),
            new projetbasededonnee.Data.Experience("1", "Dosage PAL", "En Cours", " - ", "Toxicité", "Colorimétrique", "20", "3", "5", "15"),
            new projetbasededonnee.Data.Experience("1", "Dosage Hmg", "Terminé", " - ", "Toxicité", "Colorimétrique", "15", "3", "2", "6")
        );
    
    private ObservableList<projetbasededonnee.Data.Experience> lancerExpListe =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "Befiradol", "100", "mamalienne", "200", "1", "1"),
            new projetbasededonnee.Data.Experience("2", "Befiradol", "120", "mamalienne", "200", "1", "1"),
            new projetbasededonnee.Data.Experience("3", "Befiradol", "140", "mamalienne", "200", "2", "1")
        );
    
    private ObservableList<projetbasededonnee.Data.Experience> puitListe1 =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "1", "1", "1", "1"),
            new projetbasededonnee.Data.Experience("1", "1", "2", "1", "2"),
            new projetbasededonnee.Data.Experience("1", "1", "3", "1", "3")    
        );
    
    private ObservableList<projetbasededonnee.Data.Experience> puitListe2 =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "1", "1", "1", "4"),
            new projetbasededonnee.Data.Experience("1", "1", "2", "1", "5"),
            new projetbasededonnee.Data.Experience("1", "1", "3", "1", "6")    
        );
    
    private ObservableList<projetbasededonnee.Data.Experience> puitListe3 =
        FXCollections.observableArrayList(
            new projetbasededonnee.Data.Experience("1", "1", "1", "2", "3"),
            new projetbasededonnee.Data.Experience("1", "1", "2", "2", "4"),
            new projetbasededonnee.Data.Experience("1", "1", "3", "6", "6")    
        );
    

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
        
        numExpCol.setCellValueFactory(new PropertyValueFactory<>("numExp"));
        numExpCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        nomExpCol.setCellValueFactory(new PropertyValueFactory<>("nomExp"));
        nomExpCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        etatCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        labCol.setCellValueFactory(new PropertyValueFactory<>("lab"));
        labCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        typeExpCol.setCellValueFactory(new PropertyValueFactory<>("typeExp"));
        typeExpCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        typeAnaCol.setCellValueFactory(new PropertyValueFactory<>("typeAna"));
        typeAnaCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        dureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
        dureeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        nupletCol.setCellValueFactory(new PropertyValueFactory<>("nuplet"));
        nupletCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        puitCol.setCellValueFactory(new PropertyValueFactory<>("puit"));
        puitCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        totalPuitCol.setCellValueFactory(new PropertyValueFactory<>("totalPuit"));
        totalPuitCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        checkCol.setCellValueFactory(new PropertyValueFactory<>("check"));
        checkCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        tableAccueil.setItems(expListe);


    }      

    /**
     * Affichage de scène connexion.fxml
     * @param event
     * @throws IOException 
     */
    @FXML
    public void deconnexionEvent(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
        Parent ajoutParent = (Parent) loader.load();

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
        
        replicatCol.setCellValueFactory(new PropertyValueFactory<>("replicat"));
        replicatCol.setCellFactory(TextFieldTableCell.forTableColumn());
        agentBioCol.setCellValueFactory(new PropertyValueFactory<>("agentBio"));
        agentBioCol.setCellFactory(TextFieldTableCell.forTableColumn());
        qteAgentBioCol.setCellValueFactory(new PropertyValueFactory<>("qteAgentBio"));
        qteAgentBioCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCellCol.setCellValueFactory(new PropertyValueFactory<>("typeCell"));
        typeCellCol.setCellFactory(TextFieldTableCell.forTableColumn());
        qteCellCol.setCellValueFactory(new PropertyValueFactory<>("qteCell"));
        qteCellCol.setCellFactory(TextFieldTableCell.forTableColumn());
        plaqueCol.setCellValueFactory(new PropertyValueFactory<>("plaque"));
        plaqueCol.setCellFactory(TextFieldTableCell.forTableColumn());
        photometreCol.setCellValueFactory(new PropertyValueFactory<>("photometre"));
        photometreCol.setCellFactory(TextFieldTableCell.forTableColumn());
       
        
        tableNUplet.setItems(lancerExpListe);
        
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
       
    
}
