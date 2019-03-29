/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class de Chercheur.fxml
 * @version 18/03/2019
 * @author Ludivine and Antoine
 */
public class AcceuilChercheurController implements Initializable {

    @FXML    private VBox menuVBox;
    @FXML    private ImageView deconnexionIV;
    @FXML    private ImageView home;
    
    // Page d'accueil du chercheur
    @FXML    private AnchorPane homePageChercheur;
    @FXML    private TableView<projetbasededonnee.Data.AccueilChercheur> tableViewAccueil; 
    @FXML    private VBox titleAcceuil;
    @FXML    private TableColumn<?, ?> numExpCol;
    @FXML    private TableColumn<?, ?> nomExpCol;
    @FXML    private TableColumn<?, ?> etatCol;
    @FXML    private TableColumn<?, ?> labCol;
    @FXML    private TableColumn<?, ?> typeExpCol;
    @FXML    private TableColumn<?, ?> nupletCol;
    @FXML    private TableColumn<?, ?> puitCol;
    @FXML    private TableColumn<?, ?> TypeAnaCol;
    @FXML    private TableColumn<?, ?> debCol;
    @FXML    private TableColumn<?, ?> finCol;
    
    @FXML    private AnchorPane ajoutExpPage;
    
    //¨Page pour ajouter des Uplets à une expérience
    @FXML    private AnchorPane AddUpletPage;
    @FXML    private Label typePlaqueLabel;
    @FXML    private Label dureeLabel;
    @FXML    private Label suiviLabel;
    @FXML    private Label FreqLabel;
    @FXML    private Label nbPuitReplicatLabel;
    @FXML    private Label typeExpLabel;
    @FXML    private Label typeAnaLabel;
    @FXML    private Label alpha1Label;
    @FXML    private Label alpha2Label;
    @FXML    private Label Alpha3Label;
    @FXML    private Label debutExpLabel;
    @FXML    private TableView<?> tableNUplet;
    @FXML    private TableColumn<?, ?> replicatCol;
    @FXML    private TableColumn<?, ?> agentBioCol;
    @FXML    private TableColumn<?, ?> qteAgentBioCol;
    @FXML    private TableColumn<?, ?> typeCellCol;
    @FXML    private TableColumn<?, ?> qteCellCol;
    @FXML    private TableColumn<?, ?> plaqueCol;
    @FXML    private TableColumn<?, ?> photometreCol;
    @FXML    private TextField frequTextField; 
    @FXML    private TextField Alpha3TextField; 
    @FXML    private TextField Alpha1TextField; 
    @FXML    private TextField Alpha2TextField;
    @FXML    private TextField dureeTextfield;
    @FXML    private TextField puitReplicatTextField;
    @FXML    private RadioButton OuiSuiviButton;
    @FXML    private RadioButton NonSuiviButton;
    @FXML    private ComboBox PlaqueCombo;
    @FXML    private ComboBox TypeExpCombo;
    @FXML    private ComboBox TypeAnalyseCombo; 
    
    private Connection con;
    private ConnexionController maCo;
    private String prenom; 
    private String nom,nomPrenom; 
    private ProjetBaseDeDonnee main;
    private Connexion connex; 
    private Personne personne; 
    private Statement stmt, stmt1, stmt2; 
    private ResultSet rs, rs1,rs2;
    private Integer id_pers, id_exp, nbpuit, nb_replicat;
    private String nom_exp, etat_exp, type_exp, type_analyse;
    private Date horo_deb, horo_fin; 
    
    //liste observable
    private ObservableList<projetbasededonnee.Data.AccueilChercheur> dataAccueil;
   
    /**I
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homePageChercheur.setVisible(true);
        ajoutExpPage.setVisible(false); 
        AddUpletPage.setVisible(false);
        dataAccueil = FXCollections.observableArrayList();
        setCellTableAccueil();

    }      

    private void setCellTableAccueil(){
        
        numExpCol.setCellValueFactory(new PropertyValueFactory<>("id_exp"));
        nomExpCol.setCellValueFactory(new PropertyValueFactory<>("nom_exp"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat_exp"));
        labCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeExpCol.setCellValueFactory(new PropertyValueFactory<>("type_exp"));
        TypeAnaCol.setCellValueFactory(new PropertyValueFactory<>("type_analyse"));
        nupletCol.setCellValueFactory(new PropertyValueFactory<>("nb_replicat"));
        puitCol.setCellValueFactory(new PropertyValueFactory<>("nb_puit"));
        debCol.setCellValueFactory(new PropertyValueFactory<>("horo_deb"));
        finCol.setCellValueFactory(new PropertyValueFactory<>("horo_fin"));
        
    }
    
    private void loadDataAccueilDatabase(){
         try{
        //Ajouter valeur dans tableau
            con = connex.getCon();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            rs1 = stmt.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE nom ='" + personne.getNom() + "' and prenom = '" + personne.getPrenom() + "'and email = '" + personne.getEmail() + "'"); 
             while (rs1.next()) {
                id_pers = rs1.getInt(1); 
             }
                  } catch (Exception e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
         try{
            
            rs = stmt1.executeQuery("SELECT ID_EXPERIENCE, NOMEXP, ETAT_EXP, NOM, PRENOM, TYPE_EXP, TYPE_ANALYSE, NBPUIT, HORODATAGE_DEB, HORODATAGE_FIN FROM EXPERIENCE JOIN Fait using(id_experience) JOIN PERSONNE USING(id_personne) where id_personne= '" + id_pers + "'");
            
            while (rs.next()) {      
               
                id_exp = rs.getInt(1);
                nom_exp =rs.getString(2);
                etat_exp=rs.getString(3);
                nom=rs.getString(4);
                prenom=rs.getString(5);
                type_exp=rs.getString(6);
                type_analyse=rs.getString(7);
                nbpuit= rs.getInt(8);
                horo_deb=rs.getDate(9);
                horo_fin=rs.getDate(10);
               
                } 

        } catch (Exception e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
            
         try{
             rs2 = stmt2.executeQuery("SELECT count(*) FROM N_UPLET WHERE ID_EXPERIENCE = '" + id_exp + "'");
                while (rs2.next()){
                    nb_replicat=rs2.getInt(1);              
            }
         }catch (Exception e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }

        nomPrenom= nom +" " + prenom;
        dataAccueil.add(new projetbasededonnee.Data.AccueilChercheur(id_exp, nom_exp,etat_exp,nomPrenom,type_exp, type_analyse, nb_replicat, nbpuit, horo_deb, horo_fin));
        tableViewAccueil.setItems(dataAccueil);

        }
    /**
     * Affichage de la page de connexion (connexion.fxml)
     * @param event
     * @throws IOException 
     */
    public void deconnexionEvent(MouseEvent event) throws IOException, SQLException {
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
        Parent ajoutParent = (Parent) loader.load();

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
     * Affichage du panel homePageChercheur, l'accueil du chercheur
     * @param event
     * @throws IOException 
     */
    public void homeEvent(MouseEvent event) throws IOException {
        homePageChercheur.setVisible(true);
        ajoutExpPage.setVisible(false); 
        AddUpletPage.setVisible(false);
        loadDataAccueilDatabase();
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
        
        // initialisation des listes deroulante (comboBox)
        PlaqueCombo.getItems().addAll("96 puits", "384 puits");
        TypeExpCombo.getItems().addAll("Immunologique", "Toxicité");
        TypeAnalyseCombo.getItems().addAll("Colorimétrique", "Opacimétrique"); 
    }
    
    /**
     * Event quand l'utilisateur clique sur oui dans si l'expérience est suivi dans le temps
     * @param event 
     */
    public void OuiSuiviButtonEvent(ActionEvent event){
        frequTextField.setVisible(true);
        FreqLabel.setVisible(true);
        Alpha3Label.setVisible(true);
        Alpha3TextField.setVisible(true);
        NonSuiviButton.setSelected(false);
    }
    
    /**
     * Event quand l'utilisateur clique sur non pour savoir si l'expérience est suivi dans le temps
     * @param event 
     */
    public void NonSuiviButtonEvent(ActionEvent event){
        frequTextField.setVisible(false);
        FreqLabel.setVisible(false);
        Alpha3Label.setVisible(false);
        Alpha3TextField.setVisible(false);
        OuiSuiviButton.setSelected(false);
    }
    

    
    /**
     * Event si l'utilisateur clique sur ajouter un uplet
     * @param event
     * @throws IOException 
     */
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
    
    public void setConnection(Connexion cone)
    {
        connex = cone;
    }
    
    public void setPersonne(Personne personneE){
        personne=personneE; 
    }
}

