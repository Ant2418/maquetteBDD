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
import javafx.scene.control.Button;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
/**
 * FXML Controller class de Chercheur.fxml
 * @version 18/03/2019
 * @author Ludivine and Antoine
 */
public class AcceuilChercheurController implements Initializable {

    // Menu -------------------------------------------------------------------
    @FXML    private ImageView deconnexionIV;
    @FXML    private ImageView home;
    @FXML    private ImageView newExp;
    
    // Page d'accueil du chercheur --------------------------------------------
    @FXML    private AnchorPane homePageChercheur;
    @FXML    private TableView<projetbasededonnee.Data.AccueilChercheur> tableViewAccueil; 
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
    
     // Page pour ajouter une experience ---------------------------------------
    @FXML    private AnchorPane ajoutExpPage;
    @FXML    private Label FreqLabel;
    @FXML    private Label labelBiais3;
    @FXML    private Label Alpha3Label;
    @FXML    private Label labelFrequence;
    @FXML    private Spinner frequTextField; 
    @FXML    private Spinner Alpha3Spinner; 
    @FXML    private Spinner Alpha1Spinner; 
    @FXML    private Spinner Alpha2Spinner;
    @FXML    private Spinner dureeSpinner;
    @FXML    private Spinner puitReplicatSpinner;
    @FXML    private RadioButton OuiSuiviButton;
    @FXML    private RadioButton NonSuiviButton;
    @FXML    private ComboBox TypeExpCombo;
    @FXML    private ComboBox TypeAnalyseCombo;
    @FXML    private Label ErreurExp_Label;
    @FXML    private TextField nomExpTextField1;
    @FXML    private Button ValiderExp;
    @FXML    private Button ButtonPlusReplicat;
    @FXML    private Button ButtonValideReplicat; 
  
    
    // Page pour ajouter des Uplets à une experience --------------------------
    @FXML    private AnchorPane AddUpletPage;
    // * partie information*
    @FXML   private Label nomExpLableUplet; 
    @FXML   private Label typeExpLabelUplet; 
    @FXML   private Label dureeLabelUplet; 
    @FXML   private Label suiviLabelUplet; 
    @FXML   private Label typeAnaLabelUplet; 
    @FXML   private Label nbPuitReplicatLabelUplet; 
    @FXML   private Label alpha1LabelUplet; 
    @FXML   private Label alpha2LabelUplet; 
    @FXML   private Label frequenceLabelUplet; 
    @FXML   private Label alpha3LabelUplet; 
    
    // * Partie ajout d'un nuplet *
    @FXML   private ComboBox AgentBioCombo;
    @FXML   private ComboBox CelluleCombo;
    @FXML   private Spinner AgentBioSpinner;
    @FXML   private Spinner CelluleSpinner; 
    @FXML   private Label Erreur_Ajout_Uplet;
    @FXML   private Label ErreurReplicatValider; 
    
    // * Partie visualisation *
    @FXML    private TableView<projetbasededonnee.Data.AccueilChercheur> tableNUplet;
    @FXML    private TableColumn<?, ?> replicatCol;
    @FXML    private TableColumn<?, ?> agentBioCol;
    @FXML    private TableColumn<?, ?> qteAgentBioCol;
    @FXML    private TableColumn<?, ?> typeCellCol;
    @FXML    private TableColumn<?, ?> qteCellCol;
    
    // Attributs interne a la classe ------------------------------------------
    private Integer nb_agentbio,id_agent_bio, nb_cellule, id_cellule,nb_solution,id_solution, id_uplet, quantiteAgent_bio, quantiteCellule;
    private Connection con;
    private ConnexionController maCo;
    private String prenom; 
    private String nom,nomPrenom; 
    private Connexion connex; 
    private Personne personne; 
    private Statement stmt, stmt1, stmt2,stmt3,stmt4; 
    private ResultSet rs, rs1,rs2, rs3,rs4;
    private Integer id_pers, id_exp, nbpuit, nbReplicat,nbre,duree, puitReplicat;
    private String nom_exp, etat_exp, type_exp, type_analyse,nomAgent_bio,nomCellule,nomExp;
    private Date horo_deb, horo_fin,time;
    private Double  Alpha1, Alpha2;

    
    //liste observable -------------------------------------------------------
    /**
     * liste des experiences pour le tableAccueilChercheur depuis projetbasededonnee.Data.AccueilChercheur
     */
    private ObservableList<projetbasededonnee.Data.AccueilChercheur> dataAccueil;
    /**
     * liste des uplets d'une experience depuis projetbasededonnee.Data.AccueilChercheur
     */
    private ObservableList<projetbasededonnee.Data.AccueilChercheur> dataUplet;
   
    /**
     * Initialisation de la classe controlleur </br>
     *  - Page d'accueil du chercheur est visible </br>
     *  - Page pour ajouter une experience, ajouter des nuplet sont invisibles </br>
     *  - instantiation de l'observableList dataUplet </br>
     *  - instantiation d'une ArrayList dataUpletTotal </br>
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

    /**
     * Instantiation du tableau tableAccueilChercheur de la page d'accueil 
     * avec toutes les experiences du chercheur </br>
     * Les colonnes sont : </br>
     * <ul>
     *  <li> numero de l'experience </li>
     *  <li> nom de l'experience </li>
     *  <li> etat de l'experience </li>
     *  <li> nom et prenom du laborantin (peut etre <code>null</code>) </li>
     *  <li> type de l'experience </li>
     *  <li> type d'analyse </li>
     *  <li> nombre de replicat (appeles aussi nuplet) </li>
     *  <li> nombre de puits par replicat </li>
     *  <li> date de debut </li>
     *  <li> date de fin </li>
     * </ul>
     */
    private void setCellTableAccueil(){
       
        numExpCol.setCellValueFactory(new PropertyValueFactory<>("idExp"));
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
    
    /**
     * Instantiation du tableau tableNUplet pour ajouter les n_uplets a une experience </br>
     * <ul>
     *  <li> numero du replicat </li>
     *  <li> nom de l'agent biologique </li>
     *  <li> quantite d'agent biologique </li>
     *  <li> nom de la cellule et son type </li>
     *  <li> quantite de cellule </li>
     * </ul>
     */
    private void setCellTableUplet(){
        
        dataUplet = FXCollections.observableArrayList();
        
        replicatCol.setCellValueFactory(new PropertyValueFactory<>("id_uplet"));
        agentBioCol.setCellValueFactory(new PropertyValueFactory<>("nom_agent_bio"));
        qteAgentBioCol.setCellValueFactory(new PropertyValueFactory<>("qte_agent_bio"));
        typeCellCol.setCellValueFactory(new PropertyValueFactory<>("nom_cellule"));
        qteCellCol.setCellValueFactory(new PropertyValueFactory<>("qte_cellule"));
    }
    
    /**
     * Initialisation du tableau tableNUplet pour les n_uplets, remplissage du tableau avec la liste dataUplet
     * @param idExpL identifiant de l'experience
     */
    public void loadDataUplet(Integer idExpL){
        dataUplet.clear();
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("SELECT id_n_uplet, nomA, nomC, QteA, qteC FROM N_UPLET JOIN SOLUTION USING(id_solution) JOIN AGENT_BIOLOGIQUE USING(id_agent_bio) JOIN CELLULE USING(id_cell_cancereuse) WHERE id_experience = "+ idExpL +"");
            while(rs.next()){
                id_uplet=rs.getInt(1);
                nomAgent_bio=rs.getString(2);
                nomCellule=rs.getString(3);
                quantiteAgent_bio=rs.getInt(4);
                quantiteCellule=rs.getInt(5);
                
                dataUplet.add(new projetbasededonnee.Data.AccueilChercheur(id_uplet, nomAgent_bio, nomCellule, quantiteAgent_bio, quantiteCellule));
                
            }  
            tableNUplet.setItems(dataUplet);
        } catch (SQLException e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    /**
     * Permet de remplir le tableau tableAccueilChercheur du pane pageHomeChercheur avec la liste dataAccueil
     */
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
              
            //Trouve l'id de la personne qui est connectée
            rs1 = stmt.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE nom ='" + personne.getNom() + "' and prenom = '" + personne.getPrenom() + "'and email = '" + personne.getEmail() + "'"); 
            while (rs1.next()) {
                id_pers = rs1.getInt(1); 
            }
        } catch (SQLException e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
         try{
            //Selectionne les expériences qui appartiennent au chercheur connecté
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
                    //Selectionne le nombre de uplet pour l'expérience
                    rs2 = stmt2.executeQuery("SELECT count(*) FROM N_UPLET WHERE ID_EXPERIENCE = '" + id_exp + "'");
                    while (rs2.next()){
                        nbReplicat=rs2.getInt(1);              
                    }
                }catch (SQLException e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
        
                try{
                    //regarde si un laborantin est associé à l'expérience
                    rs4=stmt4.executeQuery("SELECT count(*) FROM FAIT JOIN PERSONNE using(id_personne) WHERE ID_EXPERIENCE = '" + id_exp + "' and FONCTION = 'laborantin' ");
                    while(rs4.next()){
                        nbre=rs4.getInt(1);
                    }
                    
                    if (nbre==1){
                        //Trouve le nom et la personne de laborantin en charge de l'expérience
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
                }catch (SQLException e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
                
                //Ajoute à l'observable liste l'expérience
                dataAccueil.add(new projetbasededonnee.Data.AccueilChercheur(id_exp, nom_exp,etat_exp,nomPrenom,type_exp, type_analyse, nbReplicat, nbpuit, horo_deb, horo_fin));
            } 
            //Ajotue à la table la liste d'expérience
            tableViewAccueil.setItems(dataAccueil);

        } catch (SQLException e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
              
    }
    
    /**
     * Methode qui permet de deconnecter la personne. 
     * Renvoie sur la page de connexion (connexion.fxml)
     * @param event onClicked buttonDeconnexion
     * @throws IOException 
     * @throws java.sql.SQLException 
     */
    public void deconnexionEvent(MouseEvent event) throws IOException, SQLException {
          
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
        Parent ajoutParent = (Parent) loader.load();

        ConnexionController CCO= loader.getController();
        
        Scene ajoutSceneConn = new Scene(ajoutParent);
        
        //envoie à la connexionController la connexion et la personne connectée
        CCO.setConnexion(connex);
        CCO.setPersonne(personne); 

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ajoutSceneConn);
        window.show();
    }

    /**
     * Methode qui appelle la methode de validation d'uplet
     * @see #validationUplet()
     * @param event onClicked "buttonValiderReplicat"
     * @throws IOException 
     */
    public void ValidationAddUplet(MouseEvent event) throws IOException {
        ValidationUplet(); 
    }
    
    /**
     * Evenement quand on clique sur le bouton pour ajouter un n_uplet
     * @see #validationUplet() 
     * @param e event on key pressed "enter"
     */
    public void keyPressedAddUpletVal(KeyEvent e) {
        if (e.getCode() == ENTER) {
            ValidationUplet(); 
        }
    }

    /**
     * Methode qui permet de valider une experience s'il y a au moins un ajout d'un un n_uplet.
     * Les boutons de deconnexion, home et d'ajout d'une nouvelle experience sont inutilisable. </br>
     * Le label d'erreur du replicat valider n'est plus visible
     * Colorisation de paneHome en <code>#A1102A</code> et de paneNewExp en <code>#f4f4f4</code> </br>
     * Remise a zero du formulaire de l'experience </br>
     * @see #addExpEvent(javafx.scene.input.MouseEvent) 
     * Renvoie d'un message d'erreur dans le cas contraire </br>
     * @see #labelErreurReplicatValider
     * @see #buttonDeconnexion
     * @see #buttonHome
     * @see #buttonNewExp
     */
    public void ValidationUplet(){
        ErreurReplicatValider.setVisible(false);
        deconnexionIV.setDisable(false);
        home.setDisable(false);
        newExp.setDisable(false);
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            //Compte le nombre de uplet pour l'expérience
            rs = stmt.executeQuery("SELECT count(*) FROM N_UPLET WHERE ID_EXPERIENCE = '" + id_exp + "'");
            while (rs.next()){
                nbReplicat=rs.getInt(1);              
            }
        }catch (SQLException e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        if (nbReplicat >0) {
            //Si supérieur à 0 alors on retourne à la page d'accueil
            homePageChercheur.setVisible(true);
            ajoutExpPage.setVisible(false); 
            AddUpletPage.setVisible(false);
            Erreur_Ajout_Uplet.setVisible(false);
            loadDataAccueilDatabase();
        }
        else{
            
            ErreurReplicatValider.setVisible(true);
        }
    }

    /**
     * Affichage du panel pageHomeChercheur, l'accueil du chercheur. </br>
     * Les panel pageAjoutExp et pageAddUplet sont invisible. </br>
     * paneHome est colorise avec <code>#A1102A</code>
     * @param event onClicked buttonHome
     * @throws IOException 
     */
    public void homeEvent(MouseEvent event) throws IOException {
        
        homePageChercheur.setVisible(true);
        ajoutExpPage.setVisible(false); 
        AddUpletPage.setVisible(false);
        
    }
    
    

    /**
     * Methode qui permet de créer l'expérience saisie. </br>
     * Affichage du panel pageAjoutExp, la page permettant d'ajouter une experience
     * Initialisation des listes déroultantes et des spinners
     * @param event onClicked buttonNewExp
     * @throws IOException 
     */
    public void AddExpEvent(MouseEvent event) throws IOException {
        homePageChercheur.setVisible(false);
        ajoutExpPage.setVisible(true); 
        AddUpletPage.setVisible(false);     
        
        // initialisation des listes deroulante (comboBox)
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
        // Alpha3SpinnervalueFactory.
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
        
        // Alpha1SpinnervalueFactory.
        SpinnerValueFactory<Double> Alpha1SpinnervalueFactory = //
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5, 1000.0, 1.0,0.5);
 
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
        
        
        // Alpha2SpinnervalueFactory.
        SpinnerValueFactory<Double> Alpha2SpinnervalueFactory = //
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5, 1000.0, 1.0, 0.5);
 
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
        

        // dureeSpinnervalueFactory.
        SpinnerValueFactory<Integer> dureeSpinnervalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
 
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
        
        
         // puitReplicatSpinnervalueFactory.
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
        
        ValiderExp.setGraphic(new ImageView(new Image(getClass().getResource("checked.png").toExternalForm(), 40, 40, true, true)));
       
    }
    
    /**
     * Evenement quand l'utilisateur clique sur "oui" pour savoir si l'experience est suivi dans le temps
     * @param event onClicked buttonOuiSuivi
     */
    public void OuiSuiviButtonEvent(ActionEvent event){
        frequTextField.setVisible(true);
        FreqLabel.setVisible(true);
        Alpha3Label.setVisible(true);
        Alpha3Spinner.setVisible(true);
        NonSuiviButton.setSelected(false);
    }
    
    /**
     * Evenement quand on clique sur buttonOuiSuivi avec le clavier
     * @param e on key pressed "enter"
     */
    public void keyPressedOuiSuivi(KeyEvent e) {
        if (e.getCode() == ENTER) {
            frequTextField.setVisible(true);
            FreqLabel.setVisible(true);
            Alpha3Label.setVisible(true);
            Alpha3Spinner.setVisible(true);
            NonSuiviButton.setSelected(false);  
            OuiSuiviButton.setSelected(true);
        }
    }
    
    /**
     * Evenement quand l'utilisateur clique sur "non" pour savoir si l'experience est suivi dans le temps
     * @param event onClick buttonNonSuivi
     */
    public void NonSuiviButtonEvent(ActionEvent event){
        frequTextField.setVisible(false);
        FreqLabel.setVisible(false);
        Alpha3Label.setVisible(false);
        Alpha3Spinner.setVisible(false);
        OuiSuiviButton.setSelected(false);
    }
    
    /**
     * Evenement quand on clique sur buttonNonSuivi avec le clavier
     * @param e on key pressed "enter"
     */
    public void keyPressedNonSuivi(KeyEvent e) {
        if (e.getCode() == ENTER) {
            frequTextField.setVisible(false);
            FreqLabel.setVisible(false);
            Alpha3Label.setVisible(false);
            Alpha3Spinner.setVisible(false);
            OuiSuiviButton.setSelected(false);
            NonSuiviButton.setSelected(true);
        }
    }
    
    /**
     * Quand l'utilisateur clique sur le buttonValiderExp, ajout de l'experience </br>
     * Fait reference a la methode addExp()
     * @see #addExp() 
     * @param event onClicked buttonValiderExp
     * @throws IOException 
     */
    public void AddUpletEvent(MouseEvent event) throws IOException{
        AddExp(); 
    }
    
     /**
     * Quand l'utilisateur clique sur le buttonValiderExp via la touche "entree" du clavier, ajout de l'experience </br>
     * @see #addExp() 
     * @param e on key pressed "enter"
     */
    public void AddUpletKeyPressed(KeyEvent e) {
        if (e.getCode() == ENTER) {
            AddExp();
        }
    }
    
    /**
     * Methode qui permet d'ajouter une nouvelle experience dans la liste apres controle du formulaire </br>
     * Remise a zero du formulaire au debut (initialisation) et affichage de la page d'ajout de n uplet </br>
     * En cas d'erreur, affichage de plusieurs messages personnalises et mise en evidence du ou des champs vides. </br>
     * Cette methode appelle trois autres methodes : </br>
     * affichage des informations de l'experience avec displayLabels
     * Initialisation des comboBox et des spinners de la page n uplet avec solutionChoice
     * Initialisation du tableau de n_uplet avec la methode setCellTableUplet
     * @see #displayLabels(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Integer)     
     * @see #solutionChoice()
     * @see #setCellTableUplet()
     */
    public void AddExp(){
        deconnexionIV.setDisable(true);
        home.setDisable(true);
        newExp.setDisable(true);
        ErreurExp_Label.setText("Veuillez remplir tous les champs");
        ErreurExp_Label.setVisible(false);
        nomExpTextField1.setStyle(null);
        dureeSpinner.setStyle(null);
        puitReplicatSpinner.setStyle(null);
        TypeExpCombo.setStyle(null);
        TypeAnalyseCombo.setStyle(null);
        Alpha1Spinner.setStyle(null);
        Alpha2Spinner.setStyle(null);
        Alpha3Spinner.setStyle(null);
        NonSuiviButton.setStyle(null);
        OuiSuiviButton.setStyle(null);
        frequTextField.setStyle(null);

        duree=(Integer) dureeSpinner.getValue();
        puitReplicat=(Integer) puitReplicatSpinner.getValue();
        Alpha1= (Double) Alpha1Spinner.getValue();
        Alpha2= (Double) Alpha2Spinner.getValue();
        
        if(nomExpTextField1.getText().isEmpty()==false && duree!=0 && dureeSpinner.getValue()!=null && puitReplicatSpinner.getValue()!=null && TypeExpCombo.getSelectionModel().getSelectedItem()!= null && TypeAnalyseCombo.getSelectionModel().getSelectedItem()!=null && Alpha1Spinner.getValue()!= null && Alpha1 != 0.0 && Alpha2Spinner.getValue()!=null && Alpha2!=0.0){
            
            nomExp=nomExpTextField1.getText();
            String TypeExp=(String) TypeExpCombo.getSelectionModel().getSelectedItem(); 
            String TypeAna =(String)TypeAnalyseCombo.getSelectionModel().getSelectedItem();
            Integer dureeExp=(Integer) dureeSpinner.getValue();
            Integer puitReplicat=(Integer) puitReplicatSpinner.getValue();             
          
            //Si on selectionne le bouton non pas suivi dans le temps
            if (NonSuiviButton.isSelected()) {

                    projetbasededonnee.Data.CurrentDate Date = new projetbasededonnee.Data.CurrentDate();

                    con = connex.getCon();

                    try{
                    stmt = con.createStatement();

                    //INSERTION DE L'EXPERIENCE SAISIE DANS LA BASE DE DONNEES
                    rs= stmt.executeQuery("INSERT INTO EXPERIENCE(NOMEXP, ETAT_EXP, DUREE, NBPUIT, TYPE_EXP, TYPE_ANALYSE, ALPHA1, ALPHA2, HD_DEMANDE_CHERCHEUR, UPLETTERMINE)VALUES('"+ nomExpTextField1.getText() + "', 'En Attente', "+ dureeSpinner.getValue() + ", "+ puitReplicatSpinner.getValue() + ", '"+ TypeExpCombo.getSelectionModel().getSelectedItem() + "', '"+ TypeAnalyseCombo.getSelectionModel().getSelectedItem() + "', "+ Alpha1Spinner.getValue() + ", "+ Alpha2Spinner.getValue() + ", '"+ Date.getdateFormat().format(Date.getDate()) + "', "+ 0 + ")");
                    
                    try{
                        stmt1 = con.createStatement();

                        //Trouve l'id de l'experience qui vient d'être inséré
                        rs1 = stmt1.executeQuery("SELECT MAX(id_experience) FROM EXPERIENCE");
                        while (rs1.next()) { 
                            id_exp = rs1.getInt(1);
                        }
                    }catch (SQLException e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }

                    try{
                        stmt2 = con.createStatement();
                        //Selectionne l'id de la personne connectée
                        rs2 = stmt2.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE EMAIL = '" + personne.getEmail() + "'");
                        while (rs2.next()) { 
                            id_pers = rs2.getInt(1);
                        }
                    }catch (SQLException e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }

                    try{           
                        stmt3 = con.createStatement();
                        //Insertion dans fait de la personne et du chercheur
                        rs3 = stmt3.executeQuery("INSERT INTO FAIT VALUES("+ id_pers + ", " + id_exp  + ")");
                    }catch (SQLException e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    
                    //Permet d'afficher la page pour ajouter des uplets
                    homePageChercheur.setVisible(false);
                    ajoutExpPage.setVisible(false);
                    DisplayLabels(nomExp, TypeExp, TypeAna, dureeExp,"non", puitReplicat, Alpha1,Alpha2, 0.0, 0);    
                    SolutionChoice();
                    setCellTableUplet();
                    loadDataUplet(id_exp);
                    AddUpletPage.setVisible(true);                   
                   
                    //Remise à zero des valeurs du formulaire
                    ErreurReplicatValider.setVisible(false);
                    nomExpTextField1.clear();
                    dureeSpinner.getValueFactory().setValue(1);
                    frequTextField.getValueFactory().setValue(1);
                    puitReplicatSpinner.getValueFactory().setValue(1);
                    TypeExpCombo.getItems().clear(); 
                    TypeAnalyseCombo.getItems().clear();  
                    Alpha1Spinner.getValueFactory().setValue(1.0);
                    Alpha2Spinner.getValueFactory().setValue(1.0);
                    Alpha3Spinner.getValueFactory().setValue(0.0);
                    NonSuiviButton.setSelected(false);
                    OuiSuiviButton.setSelected(false);
                    frequTextField.setVisible(false); 
                    Alpha3Spinner.setVisible(false); 
                    FreqLabel.setVisible(false);
                    Alpha3Label.setVisible(false);
                    
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
                //Si on selectionne le bouton oui
                
                if (frequTextField.getValue()!=null && Alpha3Spinner.getValue()!=null){
                    
                    Integer frequence = (Integer) frequTextField.getValue();
                    Double Alpha3 =(Double) Alpha3Spinner.getValue(); 
                    System.out.println("moi je suis la " + Alpha3); 
                    projetbasededonnee.Data.CurrentDate Date = new projetbasededonnee.Data.CurrentDate();

                    con = connex.getCon();

                    try{
                    stmt = con.createStatement();

                    //INSERTION DE L'EXPERIENCE SAISIE DANS LA BASE DE DONNEES
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
                                }catch (SQLException e) {
                                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                                }   
                            }catch (SQLException e) {
                            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        
                        }catch (SQLException e) {
                            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                        }
                        //Permet d'afficher la page pour ajouter des uplets
                        homePageChercheur.setVisible(false);
                        ajoutExpPage.setVisible(false);
                        DisplayLabels(nomExp,TypeExp, TypeAna, dureeExp,"oui", puitReplicat, Alpha1,Alpha2, Alpha3, frequence);    
                        SolutionChoice();
                        setCellTableUplet();
                        loadDataUplet(id_exp);
                        System.out.println("c'est encore moi : "+ id_exp);
                        AddUpletPage.setVisible(true);
                        
                        
                        //Reinitialisation
                        ErreurReplicatValider.setVisible(false);
                        nomExpTextField1.clear();
                        dureeSpinner.getValueFactory().setValue(1);
                        frequTextField.getValueFactory().setValue(1);
                        puitReplicatSpinner.getValueFactory().setValue(1);
                        TypeExpCombo.getItems().clear(); 
                        TypeAnalyseCombo.getItems().clear();  
                        Alpha1Spinner.getValueFactory().setValue(1.0);
                        Alpha2Spinner.getValueFactory().setValue(1.0);
                        Alpha3Spinner.getValueFactory().setValue(0.0);
                        NonSuiviButton.setSelected(false);
                        OuiSuiviButton.setSelected(false);
                        frequTextField.setVisible(false); 
                        Alpha3Spinner.setVisible(false); 
                        FreqLabel.setVisible(false);
                        Alpha3Label.setVisible(false);
                                               
                        
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
                                Double a3 = (Double) Alpha3Spinner.getValue();
                                System.out.println("idem " + a3);
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
                if(Alpha1Spinner.getValue()==null || Alpha1==0.0){
                    Alpha1Spinner.setStyle("-fx-border-color: red");
                }
                if(Alpha2Spinner.getValue()==null || Alpha2==0.0){
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
     *  /**
     * Methode qui affiche dans des labels les informations de l'experience qui vient d'etre ajoutee
     * @see #addExp() 
     * @param nomExp nom de l'experience
     * @param typeExp type d'experience : "Immunologique", "Toxicologique"
     * @param typeAna  type d'analyse : "Colorimetrique", "Opacimetrique"
     * @param dureeExp duree de l'experience en minute
     * @param suiviExp "oui", "non"
     * @param puitReplicat nombre de puits par replicat
     * @param biais1 alpha1
     * @param biais2 alpha2
     * @param biais3 alpha3 
     * @param frequExp nombre de fois que l'experience est analyse. 
     * (exemple si freqExp = 2 et duree = 10 alors l'experience est analysee 10/2 = 5 fois)
     */
    public void DisplayLabels(String nomExp, String typeExp, String typeAna, Integer dureeExp, String suiviExp, Integer puitReplicat, Double biais1, Double biais2 ,Double biais3, Integer frequExp){        
        // initialise l'affichage dans le cas ou l'experience n'est pas suivi dans le temps
        labelBiais3.setVisible(false);
        labelFrequence.setVisible(false);
        frequenceLabelUplet.setVisible(false);
        alpha3LabelUplet.setVisible(false);
        
        
        nomExpLableUplet.setText(nomExp);
        typeExpLabelUplet.setText(typeExp); 
        dureeLabelUplet.setText(String.valueOf(dureeExp)); 
        suiviLabelUplet.setText(suiviExp);
        typeAnaLabelUplet.setText(typeAna); 
        nbPuitReplicatLabelUplet.setText(String.valueOf(puitReplicat)); 
        alpha1LabelUplet.setText(String.valueOf(biais1)); 
        alpha2LabelUplet.setText(String.valueOf(biais2)); 
        
        if ("oui".equals(suiviExp)){
            labelBiais3.setVisible(true);
            labelFrequence.setVisible(true);
            frequenceLabelUplet.setVisible(true);
            alpha3LabelUplet.setVisible(true);
            frequenceLabelUplet.setText(String.valueOf(frequExp)); 
            alpha3LabelUplet.setText(String.valueOf(biais3));
        }        
    }
    
    /**
     * Methode qui permet d'initialiser les comboBox comboAgentBio pour le type d'agent bio 
     * et la comboBox comboCellule pour le type de cellules
     */
    public void SolutionChoice(){
        
        //Initialisation des combo-box
        AgentBioCombo.getItems().clear();
        try{
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT DISTINCT(NOMA) FROM AGENT_BIOLOGIQUE");
            while (rs2.next()) { 
                AgentBioCombo.getItems().add(rs2.getString(1));
            }
        }catch (SQLException e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
        CelluleCombo.getItems().clear();
        try{
            stmt3 = con.createStatement();
            rs3 = stmt3.executeQuery("SELECT DISTINCT(NOMC), TYPE_CELLULAIRE FROM CELLULE");
            while (rs3.next()) { 
                CelluleCombo.getItems().add(rs3.getString(1)+ " - " + rs3.getString(2));
            }
        }catch (SQLException e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        //Les spinners
        
         // AgentbioSpinnervalueFactory.
        SpinnerValueFactory<Integer> AgentbioSpinnervalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 100,100);
 
        AgentBioSpinner.setValueFactory(AgentbioSpinnervalueFactory);
        
        AgentBioSpinner.setEditable(true);
        
        TextFormatter formatter2=new TextFormatter(AgentbioSpinnervalueFactory.getConverter(),AgentbioSpinnervalueFactory.getValue());
        AgentBioSpinner.getEditor().setTextFormatter(formatter2);
        
        AgentbioSpinnervalueFactory.valueProperty().bindBidirectional(formatter2.valueProperty());
        
        AgentBioSpinner.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){
                AgentBioSpinner.increment(0);
            }
        });
        
        // CelluleSpinnervalueFactory.
        SpinnerValueFactory<Integer> CelluleSpinnervalueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 100,100);
 
        CelluleSpinner.setValueFactory(CelluleSpinnervalueFactory);
        
        CelluleSpinner.setEditable(true);
        
        TextFormatter formatter3=new TextFormatter(CelluleSpinnervalueFactory.getConverter(),CelluleSpinnervalueFactory.getValue());
        CelluleSpinner.getEditor().setTextFormatter(formatter3);
        
        CelluleSpinnervalueFactory.valueProperty().bindBidirectional(formatter3.valueProperty());
        
        CelluleSpinner.focusedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue){
                CelluleSpinner.increment(0);
            }
        }); 

        ButtonPlusReplicat.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 30, 30, true, true)));
        ButtonValideReplicat.setGraphic(new ImageView(new Image(getClass().getResource("checked.png").toExternalForm(), 40, 40, true, true)));
       
    }
    
    /**
     * Methode qui permet d'ajouter un n_uplet à l'experience et de mettre à jour
     * le tableau tableNUplet quand on clique sur le bouton + pour ajouter un n_uplet
     * @param event onClicked buttonPlusReplicat
     * @throws IOException 
     */
    //Quand on clique sur plus
    public void AddUpletSolution(MouseEvent event) throws IOException{
        AddUplet(id_exp);
        loadDataUplet(id_exp);
        ErreurReplicatValider.setVisible(false);
    }
    
    /**
     * Methode qui permet d'ajouter un n_uplet à l'experience et de mettre à jour
     * le tableau tableNUplet quand on clique sur le bouton + pour ajouter 
     * un n_uplet via la touche entree du clavier
     * @param event on key pressed "enter"
     */
    public void AddUpletSolPressed(KeyEvent event) {
        if (event.getCode() == ENTER) {
            AddUplet(id_exp);
            loadDataUplet(id_exp);
            ErreurReplicatValider.setVisible(false);
        }
    }
    
   /**
     * Methode qui permet d'ajouter un n_uplet à l'experience
     * Recuperation des valeurs saisies dans formulaire pour le n_uplet
     * Affichage de message d'erreur s'il manque des valeurs
     * Ajout du n_uplet dans la liste dataUpletTotal
     * Mise à jour du tableau n_uplet
     * @param id_exp identifiant de l'experience
     */
    public void AddUplet(Integer id_exp){
        
        Erreur_Ajout_Uplet.setVisible(false);
        Erreur_Ajout_Uplet.setText("Veuillez remplir tous les champs");
        CelluleSpinner.setStyle(null);
        AgentBioSpinner.setStyle(null);
        CelluleCombo.setStyle(null);
        AgentBioCombo.setStyle(null);
        
        if(AgentBioSpinner.getValue() != null && (Integer) AgentBioSpinner.getValue()!=0 && CelluleSpinner.getValue()!=null && (Integer) CelluleSpinner.getValue()!=0 && AgentBioCombo.getSelectionModel().getSelectedItem()!=null && CelluleCombo.getSelectionModel().getSelectedItem()!=null ){
            con = connex.getCon();
      
            //Pour agent biologique
            try{
            stmt = con.createStatement();

            //Compte le nombre d'agent biologique avec le meme nom et la meme quantité
            rs= stmt.executeQuery("SELECT count(*) FROM AGENT_BIOLOGIQUE WHERE NOMA = '" + AgentBioCombo.getValue() + "' and qteA = "+ AgentBioSpinner.getValue() + "");
            while (rs.next()) { 
                nb_agentbio=rs.getInt(1);
            }
            
            //Si il n'y a pas d'agent biologique avec le meme nom et la meme qte
            if (nb_agentbio == 0) {
                Double prix= 3.0 * (Integer) AgentBioSpinner.getValue();
           
                try{
                    stmt = con.createStatement();
                    //On insert un nouveau agent bio
                    rs=stmt.executeQuery("INSERT INTO AGENT_BIOLOGIQUE (ID_AGENT_BIO, QTEA, NOMA,PRIXA) VALUES("+ 1 +", "+ AgentBioSpinner.getValue() + ", '" + AgentBioCombo.getValue() + "', " + prix +")");    
                }catch (SQLException e){
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
                try{
                    stmt = con.createStatement();
                    //On retrouve l'id de l'agent bio que l'on vient d'inserer
                    rs=stmt.executeQuery("SELECT MAX(id_agent_bio) from agent_biologique");
                    while (rs.next()){
                        id_agent_bio=rs.getInt(1);
                    }
                }catch (SQLException e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            else //Si l'agent biologique avec le meme qté est deja créer
            {
                try{
                    stmt = con.createStatement();
                    //On selectionne l'identifiant de l'agent deja créée
                    rs=stmt.executeQuery("SELECT ID_AGENT_BIO FROM AGENT_BIOLOGIQUE WHERE NOMA= '" + AgentBioCombo.getSelectionModel().getSelectedItem() + "' and QTEA= "+ AgentBioSpinner.getValue() + "");    
                    while (rs.next()) { 
                    id_agent_bio =rs.getInt(1);
                    }
                }catch (SQLException e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            }catch (SQLException e) {
                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
            }
            
            //Pour cellule
            try{
            stmt = con.createStatement();  
            //Compte le nombre de cellule avec le meme nom et la meme qte
            rs= stmt.executeQuery("SELECT count(*) FROM CELLULE WHERE NOMC = '" + (CelluleCombo.getValue() + "").split(" - ")[0] + "' and qteC = "+ CelluleSpinner.getValue() + " and TYPE_CELLULAIRE='" + (CelluleCombo.getValue() + "").split(" - ")[1] + "' ");
            while (rs.next()) { 
                nb_cellule=rs.getInt(1);
            }
            
            if (nb_cellule ==0) { //Si il n'y a pas de cellule deja creer
                Double prix= 2.0 * (Integer) CelluleSpinner.getValue();
                try{
                    stmt = con.createStatement();
                    //Insertion d'une nouvelle cellule
                    rs=stmt.executeQuery("INSERT INTO CELLULE VALUES("+ 1 +",'" + (CelluleCombo.getValue() + "").split(" - ")[1] + "',  "+ CelluleSpinner.getValue() + ",'" + (CelluleCombo.getValue() + "").split(" - ")[0] + "', " + prix +")");    
                }catch (SQLException e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
                try{
                    stmt = con.createStatement();
                    rs=stmt.executeQuery("SELECT MAX(id_cell_cancereuse) from cellule");
                    while (rs.next()){
                        id_cellule=rs.getInt(1);
                    }
                }catch (SQLException e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            else
            {
                try{
                    stmt = con.createStatement();
                    rs=stmt.executeQuery("SELECT ID_CELL_cancereuse FROM CELLULE WHERE NOMC= '" + (CelluleCombo.getValue() + "").split(" - ")[0] + "' and QTEC= "+ CelluleSpinner.getValue() + " and TYPE_CELLULAIRE = '" + (CelluleCombo.getValue() + "").split(" - ")[1] + "'");    
                    while (rs.next()) { 
                    id_cellule =rs.getInt(1);
                    }
                }catch (SQLException e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            }catch (SQLException e) {
                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
            }
            
            //ON creer la solution 
            try{
                stmt = con.createStatement();                                           
                //Compte le nombre de solution avec l'id de la cellule et de l'id l'agent bio
                rs= stmt.executeQuery("SELECT count(*) FROM SOLUTION WHERE id_cell_cancereuse = " + id_cellule + " and id_agent_bio = "+ id_agent_bio + "");
                while (rs.next()) { 
                    nb_solution=rs.getInt(1);
                }
                
                if (nb_solution==0){ //Si pas de solution avec l'id de la cellule et de l'agent bio
                    try{
                    stmt=con.createStatement();
                    //Insertion d'une nouvelle solution
                    rs=stmt.executeQuery("INSERT INTO SOLUTION (id_solution, id_cell_cancereuse, id_agent_bio) VALUES(" + 1 +", " + id_cellule + ", "+ id_agent_bio +")");
                    }catch (SQLException e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    try{
                    stmt = con.createStatement();
                    rs=stmt.executeQuery("SELECT MAX(id_solution) from solution");
                    while (rs.next()){
                        id_solution=rs.getInt(1);
                    }
                    }catch (SQLException e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                else{
                    try{
                    stmt=con.createStatement();
                    rs=stmt.executeQuery("SELECT ID_SOLUTION FROM SOLUTION WHERE id_cell_cancereuse = " + id_cellule + " and id_agent_bio = "+ id_agent_bio + "");
                    while(rs.next()){
                        id_solution=rs.getInt(1);
                    }
                    }catch (SQLException e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }catch (SQLException e) {
                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
            }
            
            //On a l'id_solution On peut creer le n_uplet
            try{
                stmt=con.createStatement();
                //Creation du n_uplet
                rs=stmt.executeQuery("INSERT INTO N_UPLET VALUES(" + 1 +"," + id_exp + ", "+ id_solution +", " + 0 +", " + 0 +", " + 0 +")");   
                Erreur_Ajout_Uplet.setText("Le réplicat a été ajouté");
                Erreur_Ajout_Uplet.setVisible(true);
                
                //Reinitialisation
                CelluleSpinner.getValueFactory().setValue(0);
                AgentBioSpinner.getValueFactory().setValue(0);
                CelluleCombo.getItems().clear();
                AgentBioCombo.getItems().clear(); 
                SolutionChoice();
                loadDataUplet(id_exp); 

            }catch (SQLException e) {
                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
            }
            
            
            
        }
        else{
            //Message d'erreur
            Erreur_Ajout_Uplet.setVisible(true);
            if(CelluleCombo.getSelectionModel().getSelectedItem()==null){
                CelluleCombo.setStyle("-fx-border-color: red"); 
            }
            if(CelluleSpinner.getValue()==null || (Integer) CelluleSpinner.getValue()==0){
                CelluleSpinner.setStyle("-fx-border-color: red");
            }
            if(AgentBioCombo.getSelectionModel().getSelectedItem()==null){
                AgentBioCombo.setStyle("-fx-border-color: red"); 
            }
            if(AgentBioSpinner.getValue()==null || (Integer)AgentBioSpinner.getValue()==0){
                AgentBioSpinner.setStyle("-fx-border-color: red");
            }
        }
        
     
                
    }
                  
    /**
     * Setter pour la connexion
     * @param cone 
     */
    public void setConnection(Connexion cone)
    {
        connex = cone;
    }
    
    /**
     * Setter pour la personne qui est connectée
     * @param personneE 
     */
    public void setPersonne(Personne personneE){
        personne=personneE; 
    }
    
}

//Classe pour inserer une date
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
