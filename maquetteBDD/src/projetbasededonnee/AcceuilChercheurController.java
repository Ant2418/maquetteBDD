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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
    @FXML    private Spinner frequTextField; 
    @FXML    private Spinner Alpha3Spinner; 
    @FXML    private Spinner Alpha1Spinner; 
    @FXML    private Spinner Alpha2Spinner;
    @FXML    private Spinner dureeSpinner;
    @FXML    private Spinner puitReplicatSpinner;
    @FXML    private RadioButton OuiSuiviButton;
    @FXML    private RadioButton NonSuiviButton;
    @FXML    private ComboBox PlaqueCombo;
    @FXML    private ComboBox TypeExpCombo;
    @FXML    private ComboBox TypeAnalyseCombo;
    @FXML    private Label ErreurExp_Label;
    @FXML    private TextField nomExpTextField1;
  
    
    private Connection con;
    private ConnexionController maCo;
    private String prenom; 
    private String nom,nomPrenom; 
    private ProjetBaseDeDonnee main;
    private Connexion connex; 
    private Personne personne; 
    private Statement stmt, stmt1, stmt2,stmt3,stmt4; 
    private ResultSet rs, rs1,rs2, rs3,rs4;
    private Integer id_pers, id_exp, nbpuit, nbReplicat,nbre,duree, puitReplicat, Alpha1, Alpha2;
    private String nom_exp, etat_exp, type_exp, type_analyse, TypePlaque;
    private Date horo_deb, horo_fin,time;

    
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
    
    public void loadDataAccueilDatabase(){
        dataAccueil.clear();
        try{
        //Ajouter valeur dans tableau
            con = connex.getCon();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            stmt3 = con.createStatement();
            stmt4 = con.createStatement();
              
            rs1 = stmt.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE nom ='" + personne.getNom() + "' and prenom = '" + personne.getPrenom() + "'and email = '" + personne.getEmail() + "'"); 
            while (rs1.next()) {
                id_pers = rs1.getInt(1); 
            }
        } catch (Exception e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
         try{
            
            rs = stmt1.executeQuery("SELECT ID_EXPERIENCE, NOMEXP, ETAT_EXP, TYPE_EXP, TYPE_ANALYSE, NBPUIT, HORODATAGE_DEB, HD_TRANSMISSION_CHERCHEUR FROM EXPERIENCE JOIN Fait using(id_experience) JOIN PERSONNE USING(id_personne) where id_personne= '" + id_pers + "'");
            
            while (rs.next()) {      
               
                id_exp = rs.getInt(1);
                nom_exp =rs.getString(2);
                etat_exp=rs.getString(3);
                type_exp=rs.getString(4);
                type_analyse=rs.getString(5);
                nbpuit= rs.getInt(6);
                horo_deb=rs.getDate(7);
                horo_fin=rs.getDate(8);

                
                try{
                    rs2 = stmt2.executeQuery("SELECT count(*) FROM N_UPLET WHERE ID_EXPERIENCE = '" + id_exp + "'");
                    while (rs2.next()){
                        nbReplicat=rs2.getInt(1);              
                    }
                }catch (Exception e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
        
                try{
                    rs4=stmt4.executeQuery("SELECT count(*) FROM FAIT JOIN PERSONNE using(id_personne) WHERE ID_EXPERIENCE = '" + id_exp + "' and FONCTION = 'laborantin' ");
                    while(rs4.next()){
                        nbre=rs4.getInt(1);
                    }
                    
                    if (nbre==1){
                        rs3 = stmt3.executeQuery("SELECT NOM, PRENOM FROM FAIT JOIN PERSONNE using(id_personne) WHERE ID_EXPERIENCE = '" + id_exp + "' and FONCTION = 'laborantin' ");
                        while (rs3.next()){
                            nom=rs3.getString(1);
                            prenom=rs3.getString(2);             
                    }
                
                    nomPrenom= nom +" " + prenom;
                    }
                    else
                    {
                        nomPrenom="-";
                    }
                }catch (Exception e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
                
                dataAccueil.add(new projetbasededonnee.Data.AccueilChercheur(id_exp, nom_exp,etat_exp,nomPrenom,type_exp, type_analyse, nbReplicat, nbpuit, horo_deb, horo_fin));
                tableViewAccueil.setItems(dataAccueil);
                } 

        } catch (Exception e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
              
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
        PlaqueCombo.getItems().clear(); 
        PlaqueCombo.getItems().addAll("96puits", "384puits");
        TypeExpCombo.getItems().clear(); 
        TypeExpCombo.getItems().addAll("Immunologique", "Toxicologique");
        TypeAnalyseCombo.getItems().clear(); 
        TypeAnalyseCombo.getItems().addAll("Colorimetrique", "Opacimetrique");
        
        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
 
        frequTextField.setValueFactory(valueFactory);
        
        frequTextField.setEditable(true);
        
        TextFormatter formatter=new TextFormatter(valueFactory.getConverter(),valueFactory.getValue());
        frequTextField.getEditor().setTextFormatter(formatter);
        
        valueFactory.valueProperty().bindBidirectional(formatter.valueProperty());
        
        frequTextField.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){
                frequTextField.increment(0);
            }
        });
        // Value factory.
        SpinnerValueFactory<Double> Alpha3SpinnervalueFactory = //
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1.0, 0.0,0.1);
 
        Alpha3Spinner.setValueFactory(Alpha3SpinnervalueFactory);
        
        Alpha3Spinner.setEditable(true);
        
        TextFormatter formatter3=new TextFormatter(Alpha3SpinnervalueFactory.getConverter(),Alpha3SpinnervalueFactory.getValue());
        Alpha3Spinner.getEditor().setTextFormatter(formatter3);
        
        Alpha3SpinnervalueFactory.valueProperty().bindBidirectional(formatter3.valueProperty());
        
        Alpha3Spinner.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){
                Alpha3Spinner.increment(0);
            }
        });
        
        // Value factory.
        SpinnerValueFactory<Integer> Alpha1SpinnervalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 1);
 
        Alpha1Spinner.setValueFactory(Alpha1SpinnervalueFactory);

        Alpha1Spinner.setEditable(true);
        
        TextFormatter formatter1=new TextFormatter(Alpha1SpinnervalueFactory.getConverter(),Alpha1SpinnervalueFactory.getValue());
        Alpha1Spinner.getEditor().setTextFormatter(formatter1);
        
        Alpha1SpinnervalueFactory.valueProperty().bindBidirectional(formatter1.valueProperty());
        
        Alpha1Spinner.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){
                Alpha1Spinner.increment(0);
            }
        });
        
        
        // Value factory.
        SpinnerValueFactory<Integer> Alpha2SpinnervalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 1);
 
        Alpha2Spinner.setValueFactory(Alpha2SpinnervalueFactory);
        
        Alpha2Spinner.setEditable(true);
        
        TextFormatter formatter2=new TextFormatter(Alpha2SpinnervalueFactory.getConverter(),Alpha2SpinnervalueFactory.getValue());
        Alpha2Spinner.getEditor().setTextFormatter(formatter2);
        
        Alpha2SpinnervalueFactory.valueProperty().bindBidirectional(formatter2.valueProperty());
        
        Alpha2Spinner.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){
                Alpha2Spinner.increment(0);
            }
        });
        

        // Value factory.
        SpinnerValueFactory<Integer> dureeSpinnervalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 1);
 
        dureeSpinner.setValueFactory(dureeSpinnervalueFactory);
        dureeSpinner.setEditable(true);
        
        TextFormatter formatterd=new TextFormatter(dureeSpinnervalueFactory.getConverter(),dureeSpinnervalueFactory.getValue());
        dureeSpinner.getEditor().setTextFormatter(formatterd);
        
        dureeSpinnervalueFactory.valueProperty().bindBidirectional(formatterd.valueProperty());
        
        dureeSpinner.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){
                dureeSpinner.increment(0);
            }
        });
        
        
         // Value factory.
        SpinnerValueFactory<Integer> puitReplicatSpinnervalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
 
        puitReplicatSpinner.setValueFactory(puitReplicatSpinnervalueFactory);
        puitReplicatSpinner.setEditable(true);
        
        TextFormatter formatterpr=new TextFormatter(puitReplicatSpinnervalueFactory.getConverter(),puitReplicatSpinnervalueFactory.getValue());
        puitReplicatSpinner.getEditor().setTextFormatter(formatterpr);
        
        puitReplicatSpinnervalueFactory.valueProperty().bindBidirectional(formatterpr.valueProperty());
        
        puitReplicatSpinner.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){
                puitReplicatSpinner.increment(0);
            }
        });
       
    }
    
    /**
     * Event quand l'utilisateur clique sur oui dans si l'expérience est suivi dans le temps
     * @param event 
     */
    public void OuiSuiviButtonEvent(ActionEvent event){
        frequTextField.setVisible(true);
        FreqLabel.setVisible(true);
        Alpha3Label.setVisible(true);
        Alpha3Spinner.setVisible(true);
        NonSuiviButton.setSelected(false);
    }
    
    /**
     * Event quand l'utilisateur clique sur nondupont pour savoir si l'expérience est suivi dans le temps
     * @param event 
     */
    public void NonSuiviButtonEvent(ActionEvent event){
        frequTextField.setVisible(false);
        FreqLabel.setVisible(false);
        Alpha3Label.setVisible(false);
        Alpha3Spinner.setVisible(false);
        OuiSuiviButton.setSelected(false);
    }
    
    /**
     * Event si l'utilisateur clique sur ajouter un uplet
     * @param event
     * @throws IOException 
     */
    public void AddUpletEvent(MouseEvent event) throws IOException{

        ErreurExp_Label.setText("Veuillez remplir tous les champs");
        ErreurExp_Label.setVisible(false);
        nomExpTextField1.setStyle(null);
        dureeSpinner.setStyle(null);
        puitReplicatSpinner.setStyle(null);
        TypeExpCombo.setStyle(null);
        TypeAnalyseCombo.setStyle(null);
        PlaqueCombo.setStyle(null);
        Alpha1Spinner.setStyle(null);
        Alpha2Spinner.setStyle(null);
        Alpha3Spinner.setStyle(null);
        NonSuiviButton.setStyle(null);
        OuiSuiviButton.setStyle(null);
        frequTextField.setStyle(null);

        duree=(Integer) dureeSpinner.getValue();
        puitReplicat=(Integer) puitReplicatSpinner.getValue();
        Alpha1= (Integer) Alpha1Spinner.getValue();
        Alpha2= (Integer) Alpha2Spinner.getValue();
        
        if(nomExpTextField1.getText().isEmpty()==false && duree!=0 && dureeSpinner.getValue()!=null && puitReplicatSpinner.getValue()!=null && TypeExpCombo.getSelectionModel().getSelectedItem()!= null && PlaqueCombo.getSelectionModel().getSelectedItem()!= null && TypeAnalyseCombo.getSelectionModel().getSelectedItem()!=null && Alpha1Spinner.getValue()!= null && Alpha1 != 0 && Alpha2Spinner.getValue()!=null && Alpha2!=0){
            TypePlaque= (String) PlaqueCombo.getSelectionModel().getSelectedItem();
            if (NonSuiviButton.isSelected()) {

                    projetbasededonnee.Data.CurrentDate Date = new projetbasededonnee.Data.CurrentDate();

                    con = connex.getCon();

                    try{
                    stmt = con.createStatement();

                    rs= stmt.executeQuery("INSERT INTO EXPERIENCE(NOMEXP, ETAT_EXP, DUREE, NBPUIT, TYPE_EXP, TYPE_ANALYSE, ALPHA1, ALPHA2, HD_DEMANDE_CHERCHEUR, UPLETTERMINE)VALUES('"+ nomExpTextField1.getText() + "', 'En Attente', "+ dureeSpinner.getValue() + ", "+ puitReplicatSpinner.getValue() + ", '"+ TypeExpCombo.getSelectionModel().getSelectedItem() + "', '"+ TypeAnalyseCombo.getSelectionModel().getSelectedItem() + "', "+ Alpha1Spinner.getValue() + ", "+ Alpha2Spinner.getValue() + ", '"+ Date.getdateFormat().format(Date.getDate()) + "', "+ 0 + ")");
                    
                    try{
                        stmt1 = con.createStatement();

                        rs1 = stmt1.executeQuery("SELECT MAX(id_experience) FROM EXPERIENCE");
                        while (rs1.next()) { 
                            id_exp = rs1.getInt(1);
                        }
                    }catch (Exception e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }

                    try{
                        stmt2 = con.createStatement();
                        rs2 = stmt2.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE EMAIL = '" + personne.getEmail() + "'");
                        while (rs2.next()) { 
                            id_pers = rs2.getInt(1);
                        }
                    }catch (Exception e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }

                    try{           
                        stmt3 = con.createStatement();
                        rs3 = stmt3.executeQuery("INSERT INTO FAIT VALUES("+ id_pers + ", " + id_exp  + ")");
                    }catch (Exception e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    
                    homePageChercheur.setVisible(false);
                    ajoutExpPage.setVisible(false);
                    AddUpletPage.setVisible(true);
                    
                    nomExpTextField1.clear();
                    dureeSpinner.getValueFactory().setValue(1);
                    frequTextField.getValueFactory().setValue(1);
                    puitReplicatSpinner.getValueFactory().setValue(1);
                    TypeExpCombo.getItems().clear(); 
                    TypeAnalyseCombo.getItems().clear(); 
                    PlaqueCombo.getItems().clear(); 
                    Alpha1Spinner.getValueFactory().setValue(1);
                    Alpha2Spinner.getValueFactory().setValue(1);
                    Alpha3Spinner.getValueFactory().setValue(0.0);
                    NonSuiviButton.setSelected(false);
                    OuiSuiviButton.setSelected(false);
                    
                    }catch (SQLException e) {
                         System.out.println("Exception SQL : ");
                        while (e != null) {
                            String message = e.getMessage();
                            int errorCode = e.getErrorCode();
                            if (errorCode == 984) {
                                ErreurExp_Label.setText("");
                                ErreurExp_Label.setText("Une valeur saisie est incorrecte.");
                                ErreurExp_Label.setVisible(true);
                                
                            } else if (errorCode == 2290) {
                                ErreurExp_Label.setText("");
                                ErreurExp_Label.setText("La valeur du Biais 1 doit être inférieure ou égale à celle du Bais 2.");
                                ErreurExp_Label.setVisible(true);
                                Alpha1Spinner.setStyle("-fx-border-color: red");
                                Alpha2Spinner.setStyle("-fx-border-color: red");
                                 
                            }
                            e = e.getNextException();
                        }
                    }

            }
            else if (OuiSuiviButton.isSelected()) {
                
                if (frequTextField.getValue()!=null && Alpha3Spinner.getValue()!=null){
                    
                projetbasededonnee.Data.CurrentDate Date = new projetbasededonnee.Data.CurrentDate();

                    con = connex.getCon();

                    try{
                    stmt = con.createStatement();

                    rs= stmt.executeQuery("INSERT INTO EXPERIENCE(NOMEXP, ETAT_EXP, DUREE, FREQUENCE, NBPUIT, TYPE_EXP, TYPE_ANALYSE, ALPHA1, ALPHA2, ALPHA3, HD_DEMANDE_CHERCHEUR, UPLETTERMINE)VALUES('"+ nomExpTextField1.getText() + "', 'En Attente', "+ dureeSpinner.getValue() + ", "+ frequTextField.getValue() + ", "+ puitReplicatSpinner.getValue() + ", '"+ TypeExpCombo.getSelectionModel().getSelectedItem() + "', '"+ TypeAnalyseCombo.getSelectionModel().getSelectedItem() + "', "+ Alpha1Spinner.getValue() + ", "+ Alpha2Spinner.getValue() + ", "+ Alpha3Spinner.getValue() + ", '"+ Date.getdateFormat().format(Date.getDate()) + "', "+ 0 + ")");
                        try{
                        stmt1 = con.createStatement();

                        rs1 = stmt1.executeQuery("SELECT MAX(id_experience) FROM EXPERIENCE");
                        while (rs1.next()) { 
                            id_exp = rs1.getInt(1);
                        }
                  
                            try{
                                stmt2 = con.createStatement();
                                rs2 = stmt2.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE EMAIL = '" + personne.getEmail() + "'");
                                while (rs2.next()) { 
                                    id_pers = rs2.getInt(1);
                                }

                                try{           
                                    stmt3 = con.createStatement();
                                    rs3 = stmt3.executeQuery("INSERT INTO FAIT VALUES("+ id_pers + ", " + id_exp  + ")");
                                }catch (Exception e) {
                                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                                }   
                            }catch (Exception e) {
                            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        
                        }catch (Exception e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                        }
                        homePageChercheur.setVisible(false);
                        ajoutExpPage.setVisible(false);
                        AddUpletPage.setVisible(true);
                    
                    }catch (SQLException e) {
                         System.out.println("Exception SQL : ");
                        while (e != null) {
                            String message = e.getMessage();
                            int errorCode = e.getErrorCode();
                            if (errorCode == 984) {
                                ErreurExp_Label.setText("");
                                ErreurExp_Label.setText("Une valeur saisie est incorrecte.");
                                ErreurExp_Label.setVisible(true);
                            } else if (errorCode == 2290) {
                                if(Alpha1>Alpha2){
                                ErreurExp_Label.setText("");
                                ErreurExp_Label.setText("La valeur du Biais 1 doit être inférieure ou égale à celle du Bais 2.");
                                ErreurExp_Label.setVisible(true);
                                Alpha1Spinner.setStyle("-fx-border-color: red");
                                Alpha2Spinner.setStyle("-fx-border-color: red");
                                }
                                double a3 = (Double) Alpha3Spinner.getValue();
                                if(a3>1 || a3<0){
                                    ErreurExp_Label.setText("");
                                    ErreurExp_Label.setText("La valeur du Biais 3 doit être compris entre 0 et 1.");
                                    ErreurExp_Label.setVisible(true);
                                    Alpha3Spinner.setStyle("-fx-border-color: red");
                                   
                                }
                            e = e.getNextException();
                        }
                    }
                    }
                    

                }
                else
                {
                    if(frequTextField.getValue()==null){
                        frequTextField.setStyle("-fx-border-color: red");
                    }
                    if(Alpha3Spinner.getValue()==null){
                    Alpha3Spinner.setStyle("-fx-border-color: red");
                    }
                }
            }
            else{
                
                //Si le bouton oui ou le bouton non n'est pas coché
                OuiSuiviButton.setStyle("-fx-border-color: red"); 
                NonSuiviButton.setStyle("-fx-border-color: red");
                ErreurExp_Label.setVisible(true);
                homePageChercheur.setVisible(false);
                ajoutExpPage.setVisible(true);
                AddUpletPage.setVisible(false);
            }

        }                
        else
            { 
                //Affiche le message d'erreur
                ErreurExp_Label.setVisible(true);
                
                if(nomExpTextField1.getText().isEmpty()){
                    nomExpTextField1.setStyle("-fx-border-color: red"); 
                }
                if(dureeSpinner.getValue()==null || duree==0){
                    dureeSpinner.setStyle("-fx-border-color: red");
                }
                if(puitReplicatSpinner.getValue()==null){
                    puitReplicatSpinner.setStyle("-fx-border-color: red");
                }
                if(TypeExpCombo.getSelectionModel().getSelectedItem()== null){
                    TypeExpCombo.setStyle("-fx-border-color: red");
                }
                if(TypeAnalyseCombo.getSelectionModel().getSelectedItem()==null){
                    TypeAnalyseCombo.setStyle("-fx-border-color: red");
                }
                if(PlaqueCombo.getSelectionModel().getSelectedItem()== null){
                    PlaqueCombo.setStyle("-fx-border-color: red");
                }
                if(Alpha1Spinner.getValue()==null || Alpha1==0){
                    Alpha1Spinner.setStyle("-fx-border-color: red");
                }
                if(Alpha2Spinner.getValue()==null || Alpha2==0){
                    Alpha2Spinner.setStyle("-fx-border-color: red");
                }
                if(OuiSuiviButton.isSelected()==false && NonSuiviButton.isSelected()==false){
                    OuiSuiviButton.setStyle("-fx-border-color: red");
                    NonSuiviButton.setStyle("-fx-border-color: red");
                }
                else if (OuiSuiviButton.isSelected()){
                    if(frequTextField.getValue()==null){
                        frequTextField.setStyle("-fx-border-color: red");
                    }
                    if(Alpha3Spinner.getValue()==null){
                    Alpha3Spinner.setStyle("-fx-border-color: red");
                    }
                }
                

                //Continue d'afficher la page ajout exp
                homePageChercheur.setVisible(false);
                ajoutExpPage.setVisible(true);
                AddUpletPage.setVisible(false);
                }
                      

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


 class MyConverter extends StringConverter<Integer> {
 
       @Override
       public String toString(Integer object) {
           return object + "";
       }
 
       @Override
       public Integer fromString(String string) {
           return Integer.parseInt(string);
       }
 
   }
