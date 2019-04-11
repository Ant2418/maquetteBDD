/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
 * Cette partie permet de gerer les differents affichage de la partie laborantin
 * @version 28/03/2019
 * @author Antoine et Ludivine
 */
public class LaborantinController implements Initializable {

    // Attributs du menu du laborantin
    @FXML    private VBox menuVBox;
    @FXML    private ImageView deconnexionIV;
    @FXML    private ImageView home;
    @FXML    private ImageView experience;
    @FXML    private ImageView resultat;
    
    // Page d'accueil du laborantin
private AnchorPane homePageLab;
    private TableView<Experience> tableAccueil;
    private TableColumn<Experience, String> numExpCol;
    private TableColumn<Experience, String> nomExpCol;
    private TableColumn<Experience, String> etatCol;
    private TableColumn<Experience, String> labCol;
    private TableColumn<Experience, String> typeExpCol;
    private TableColumn<Experience, String> typeAnaCol;
    private TableColumn<Experience, String> dureeCol;
    private TableColumn<Experience, String> nupletCol;
    private TableColumn<Experience, String> puitCol;
    private TableColumn<Experience, String> totalPuitCol;
    private TableColumn<Experience, String> checkCol;
    
    // Page des expériences du laborantin 
    // la page est diviser en plusieurs tab en fonction des status 
    // qui sont 'A Renouveler'(AR), 'En Attente' (EA) etc...
private AnchorPane expLabPage;
    private TableView<Experience> tableLabAR;
    private TableColumn<Experience, String> numExpLabARCol;
    private TableColumn<Experience, String> nomExpLabARCol;
    private TableColumn<Experience, String> chercheurLabARCol;
    private TableColumn<Experience, String> typeExpLabARCol;
    private TableColumn<Experience, String> typeAnaLabARCol;
    private TableColumn<Experience, String> dureeLabARCol;
    private TableColumn<Experience, String> nupletLabARCol;
    private TableColumn<Experience, String> puitLabARCol;
    private TableColumn<Experience, String> totalPuitLabARCol;
    private TableColumn<Experience, String> checkLabARCol;
    
    
    // Page pour lancer une expérience
private AnchorPane visuExpPage;
    @FXML    private ImageView validerIV;
    
    // TABLE DE VISUALISATION DES REPLICATS / N-UPLET
private TableView<Experience> tableNUplet;
    private TableColumn<Experience, String> replicatCol;
    private TableColumn<Experience, String> agentBioCol;
    private TableColumn<Experience, String> qteAgentBioCol;
    private TableColumn<Experience, String> typeCellCol;
    private TableColumn<Experience, String> qteCellCol;
    private TableColumn<Experience, String> plaqueCol;
    private TableColumn<Experience, String> photometreCol;
    
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
    
    private Button lancerExpButton;
    
    private Connexion connex; 
    private Personne personne; 
    @FXML
    private AnchorPane accueilLaboPane;
    @FXML
    private VBox titleAcceuil1;
    @FXML
    private TableView<?> tableAccueilLabo;
    @FXML
    private TableColumn<?, ?> colNumPlaque;
    @FXML
    private TableColumn<?, ?> colTypePlaque;
    @FXML
    private TableColumn<?, ?> colPuitsDispo;
    @FXML
    private TableColumn<?, ?> colButton;
    @FXML
    private ComboBox<?> cbTypePlaque;
    @FXML
    private Label labelAjoutPlaque;
    @FXML
    private VBox titleAcceuil111;
    @FXML
    private ComboBox<?> experienceCB;
    private Statement stmt;
    private Connection con; 
    private ResultSet rs;
    private ArrayList<Integer> listeIdUplet;
    
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
        
        
        //Initialisation des colonnes pour le taleau tableAccueil du laborantin
        numExpCol.setCellValueFactory(new PropertyValueFactory<>("numExp"));
        nomExpCol.setCellValueFactory(new PropertyValueFactory<>("nomExp"));        
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        labCol.setCellValueFactory(new PropertyValueFactory<>("lab"));
        typeExpCol.setCellValueFactory(new PropertyValueFactory<>("typeExp"));
        typeAnaCol.setCellValueFactory(new PropertyValueFactory<>("typeAna"));
        dureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
        nupletCol.setCellValueFactory(new PropertyValueFactory<>("nuplet"));
        puitCol.setCellValueFactory(new PropertyValueFactory<>("puit"));
        totalPuitCol.setCellValueFactory(new PropertyValueFactory<>("totalPuit"));
        checkCol.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        
        // ajout des data au tableau
        tableAccueil.setItems(projetbasededonnee.Data.Data.expListe);


    }      

    /**
     * Affichage de scène connexion.fxml
     * @param event
     * @throws IOException 
     */
    @FXML
    public void deconnexionEvent(MouseEvent event) throws IOException, SQLException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
        Parent ajoutParent = (Parent) loader.load();;
                
        ConnexionController CCO= loader.getController();
        
        Scene ajoutSceneConn = new Scene(ajoutParent);
        
        CCO.setConnexion(connex);
        CCO.setPersonne(personne); 
        
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
        
        lancerExpButton.setDisable(true);
        
        //initialisation tableau 'A Renouveller'
        numExpLabARCol.setCellValueFactory(new PropertyValueFactory<>("numExpLabAR"));
        nomExpLabARCol.setCellValueFactory(new PropertyValueFactory<>("nomExpLabAR"));        
        chercheurLabARCol.setCellValueFactory(new PropertyValueFactory<>("chercheurLabAR"));
        typeExpLabARCol.setCellValueFactory(new PropertyValueFactory<>("typeExpLabAR"));
        typeAnaLabARCol.setCellValueFactory(new PropertyValueFactory<>("typeAnaLabAR"));
        dureeLabARCol.setCellValueFactory(new PropertyValueFactory<>("dureeLabAR"));
        nupletLabARCol.setCellValueFactory(new PropertyValueFactory<>("nupletLabAR"));
        puitLabARCol.setCellValueFactory(new PropertyValueFactory<>("puitLabAR"));
        totalPuitLabARCol.setCellValueFactory(new PropertyValueFactory<>("totalPuitLabAR"));
        checkLabARCol.setCellValueFactory(new PropertyValueFactory<>("checkboxLabAR"));
        
        // ajout des data au tableau
        tableLabAR.setItems(projetbasededonnee.Data.Data.expLabListeAR);
        
        
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
        
        // Initialisation des colonnes
        replicatCol.setCellValueFactory(new PropertyValueFactory<>("replicat"));
        agentBioCol.setCellValueFactory(new PropertyValueFactory<>("agentBio"));
        qteAgentBioCol.setCellValueFactory(new PropertyValueFactory<>("qteAgentBio"));
        typeCellCol.setCellValueFactory(new PropertyValueFactory<>("typeCell"));
        qteCellCol.setCellValueFactory(new PropertyValueFactory<>("qteCell"));
        plaqueCol.setCellValueFactory(new PropertyValueFactory<>("plaque"));
        photometreCol.setCellValueFactory(new PropertyValueFactory<>("photometre"));
        
        // Initialisation de l'edition des colonnes
        plaqueCol.setCellFactory(TextFieldTableCell.forTableColumn());
        photometreCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // ajout des data au tableau
        tableNUplet.setItems(projetbasededonnee.Data.Data.replicatListe);
        
    }
    
    /**
     * Affichage du panel validationPage, qui permet de valider une expérience
     * @param event
     * @throws IOException la
     */
    @FXML
    public void validationEvent(MouseEvent event)throws IOException {
        homePageLab.setVisible(false);
        expLabPage.setVisible(false); 
        visuExpPage.setVisible(false);
        validationPage.setVisible(true);
    }
    
    /**
     * Methode qui deverouille le bouton lorsqu'une ligne est selectionee
     */
    public void ligneSelectionne(){
        this.lancerExpButton.setDisable(false);
    }
    
    public void visuPositionPuitOnClicked(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupPositionPuit.fxml"));
        Parent ajoutParent = (Parent) loader.load();
        
        PopupPositionPuitController popup = loader.getController();
        
        Scene maScene = new Scene(ajoutParent);
        
        Stage newStage = new Stage();
//        newStage.initOwner(primaryStage);
        Stage window = newStage;
        window.setScene(maScene);
        window.show();

    }
    
    public void setConnection(Connexion cone)
    {
        connex = cone;
    } 
    
    public void setPersonne(Personne personneE){
        personne=personneE; 
    }
    
    
    public void AjoutXYPlaque(projetbasededonnee.Data.Laborantin experience, projetbasededonnee.Data.Laborantin plaque){
        listeIdUplet = new ArrayList(); 
           
    try{
        con = connex.getCon();
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT id_n_uplet FROM N_UPLET WHERE id_experience = " + experience.getId_exp() + " and terminee = "+ 0+ "");
        while (rs.next()) { 
            Integer id_uplet= rs.getInt(1);
            listeIdUplet.add(id_uplet); 
        }
    }catch (Exception e) {
        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
    }
        
        if (plaque.getType_plaque() ==  "96puits"){
            Integer nbrePuit_Uplet= experience.getNb_puits()/experience.getNb_n_uplet();
            Integer puits_dispo=plaque.getPuits_dispo(); 
            Integer puits_exp=experience.getNb_puits(); 

            if ((puits_dispo + puits_exp) <= 96 ){
                if (puits_dispo == 96){
                    int X=1;
                    int Y=1;

                    for (Integer idUplet : listeIdUplet) {
                        
                        for (int i=1; i <= nbrePuit_Uplet; i++){
                            try{
                            stmt = con.createStatement();
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) = " + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+"");
                            if (Y == 8) {
                                X=X+1;
                                Y=1;
                            }
                            else
                            {
                                Y=Y+1;
                            }
                            
                            }catch (Exception e) {
                                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    }
                }
                else //Je ne peux pas mettre tous les n_uplets sur la plaque
                {
                    Integer nbreUpletAInserer = puits_dispo/ puits_exp;
                    Integer nb=1;
                    int X=0; 
                    int Y=0; 
                    try{
                        stmt = con.createStatement();
                        rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getId_plaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                        while (rs.next()) { 
                            X = rs.getInt(1);
                            Y = rs.getInt(2);
                        }
                        
                    }catch (Exception e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    while( nb <= nbreUpletAInserer){
                        Integer idUplet=listeIdUplet.get(nb);
                         for (int i=1; i <= nbrePuit_Uplet; i++){

                            if (Y == 8) {
                                X=X+1;
                                Y=1;
                            }
                            else
                            {
                                Y=Y+1;
                            }
                            
                            try{
                            stmt = con.createStatement();
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) = " + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+"");

                            }catch (Exception e) {
                                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        } 
                        nb=nb+1;
                    }
                }
            }
        
        }
        else if (plaque.getType_plaque() ==  "384puits"){
            Integer nbrePuit_Uplet= experience.getNb_puits()/experience.getNb_n_uplet();
            Integer puits_dispo=plaque.getPuits_dispo(); 
            Integer puits_exp=experience.getNb_puits(); 

            if ((puits_dispo + puits_exp) <= 384 ){
                if (puits_dispo == 384){
                    int X=1;
                    int Y=1;

                    for (Integer idUplet : listeIdUplet) {
                        
                        for (int i=1; i <= nbrePuit_Uplet; i++){
                            try{
                            stmt = con.createStatement();
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) = " + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+"");
                            if (Y == 16) {
                                X=X+1;
                                Y=1;
                            }
                            else
                            {
                                Y=Y+1;
                            }
                            
                            }catch (Exception e) {
                                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    }
                }
                else //Je ne peux pas mettre tous les n_uplets sur la plaque
                {
                    Integer nbreUpletAInserer = puits_dispo/ puits_exp;
                    Integer nb=1;
                    int X=0; 
                    int Y=0; 
                    try{
                        stmt = con.createStatement();
                        rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getId_plaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                        while (rs.next()) { 
                            X = rs.getInt(1);
                            Y = rs.getInt(2);
                        }
                        
                    }catch (Exception e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    while( nb <= nbreUpletAInserer){
                        Integer idUplet=listeIdUplet.get(nb);
                         for (int i=1; i <= nbrePuit_Uplet; i++){

                            if (Y == 16) {
                                X=X+1;
                                Y=1;
                            }
                            else
                            {
                                Y=Y+1;
                            }
                            
                            try{
                            stmt = con.createStatement();
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) = " + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+"");

                            }catch (Exception e) {
                                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        } 
                        nb=nb+1;
                    }
                }
            }
          
        
        
    }
}
}
