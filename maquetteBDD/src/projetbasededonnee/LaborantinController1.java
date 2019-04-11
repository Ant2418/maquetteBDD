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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Antoine
 */
public class LaborantinController1 implements Initializable {

    @FXML    private VBox menuVBox;
    @FXML    private ImageView deconnexionIV;
    @FXML    private ImageView home;
    @FXML    private ImageView resultat;
    @FXML    private AnchorPane accueilLaboPane;
    @FXML    private AnchorPane LancerPlaquePage;
    @FXML    private AnchorPane EmplacementPlaquePage; 
    @FXML    private AnchorPane validationPage; 
    @FXML    private VBox titleAcceuil1;
    @FXML    private TableView<projetbasededonnee.Data.Laborantin> tableAccueilLabo;
    @FXML    private TableColumn<?, ?> colNumPlaque;
    @FXML    private TableColumn<?, ?> colTypePlaque;
    @FXML    private TableColumn<?, ?> colPuitsDispo;
    @FXML    private ComboBox cbTypePlaque;
    @FXML    private ImageView validerIV;
    @FXML    private Label labelAjoutPlaque;
    @FXML    private VBox titleAcceuil11;
    @FXML    private VBox titleAcceuil111;
    @FXML    private ComboBox experienceCB;
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

    @FXML    private TableView<projetbasededonnee.Data.Laborantin> tableExpARenouveler;
    @FXML    private TableColumn<?, ?> colNumExp;
    @FXML    private TableColumn<?, ?> colNomExp;
    @FXML    private TableColumn<?, ?> colNbReplicat;
    @FXML    private TableColumn<?, ?> colTypeAna;
    @FXML    private TableColumn<?, ?> colTypeExp;
    @FXML    private TableColumn<?, ?> colNbPuits;
    
    @FXML    private TableView<projetbasededonnee.Data.Laborantin> tableExpEnAttente;
    @FXML    private TableColumn<?, ?> colNumExp2;
    @FXML    private TableColumn<?, ?> colNomExp2;
    @FXML    private TableColumn<?, ?> colNbReplicat2;
    @FXML    private TableColumn<?, ?> colTypeAna2;
    @FXML    private TableColumn<?, ?> colTypeExp2;
    @FXML    private TableColumn<?, ?> colNbPuits2;
    
    @FXML    private Label labelInfoPlaquePuits;
    @FXML    private Button buttonAddAR;
    @FXML    private Button buttonAddEA;
    
    private Button lancerExpButton;
    
    private Connexion connex;
    private Connection con;
    private Statement stmt; 
    private ResultSet rs; 
    private Personne personne; 
    private Integer id_plaque,nb_puit;
    private String type_plaque;
    
    private Integer idExp;
    private String nomExp;
    private String typeAna;
    private String typeExp;
    private Integer nbPuits;
    private Integer nbUpletAR;
    private Integer nbreUplet;
    private Integer nbreUpletTraitee;
    
    
    //liste observable
    private ObservableList<projetbasededonnee.Data.Laborantin> dataPlaque;
    private ObservableList<projetbasededonnee.Data.Laborantin> dataExpARenouveler;
    private ObservableList<projetbasededonnee.Data.Laborantin> dataExpEnAttente;
    private ArrayList<Integer> listeIdPlaque;
    private ArrayList<Integer> listeIdExp;
    private ArrayList<Integer> listeIdExpEA;
    private ArrayList<Integer> listeIdUplet;
    
    private projetbasededonnee.Data.Laborantin maPlaque;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accueilLaboPane.setVisible(true);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(false);
        dataPlaque = FXCollections.observableArrayList();
        listeIdPlaque= new ArrayList();
        setCellTablePlaque();
        
    }    

   
    private void setCellTablePlaque(){
       
        colNumPlaque.setCellValueFactory(new PropertyValueFactory<>("id_plaque"));
        colTypePlaque.setCellValueFactory(new PropertyValueFactory<>("Type_plaque"));
        colPuitsDispo.setCellValueFactory(new PropertyValueFactory<>("puits_dispo"));
           
        //Set Edit button column
        TableColumn<projetbasededonnee.Data.Laborantin, Void> editerColonne = new TableColumn("Editer");
        
        Callback<TableColumn<projetbasededonnee.Data.Laborantin, Void>, TableCell<projetbasededonnee.Data.Laborantin, Void>> cellFactory = (final TableColumn<projetbasededonnee.Data.Laborantin, Void> param) -> {
            final TableCell<projetbasededonnee.Data.Laborantin, Void> cell = new TableCell<projetbasededonnee.Data.Laborantin, Void>() {
                private final Button btn = new Button("Completer");
                {
                    btn.setOnAction((ActionEvent event) -> {
                        
                        maPlaque = getTableView().getItems().get(getIndex());
                        System.out.println(maPlaque);
                        System.out.println(maPlaque);
                        accueilLaboPane.setVisible(false);
                        LancerPlaquePage.setVisible(true); 
                        EmplacementPlaquePage.setVisible(false);
                        validationPage.setVisible(false);
                        
                        setInfoPlaque();
                        listeIdExp = new ArrayList();
                        setCellTableARenouveler();
                        loadDataExpARenouveler();
                        listeIdExpEA = new ArrayList();
                        setCellTableEnAttente();
                        loadDataExpEnAttente();
                        buttonAddAR.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));
                        buttonAddEA.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));
                        
                        // disable button
                        home.
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                        setStyle("-fx-alignment : CENTER;");
                        //btn.setGraphic(new ImageView(new Image(getClass().getResource("baseline_edit_black_48dp.png").toExternalForm(), 20, 20, true, true)));
                    }
                }
            };
            return cell;
        };
        editerColonne.setCellFactory(cellFactory);
        tableAccueilLabo.getColumns().add(editerColonne);
    
  
    }

    
    public void loadDataPlaque(){
        dataPlaque.clear();
        listeIdPlaque.clear();
        
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("SELECT id_plaque FROM PLAQUE");
            while(rs.next()){
                id_plaque=rs.getInt(1);          
                listeIdPlaque.add(id_plaque);
            }  
          
        } catch (Exception e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }

        for (Integer id_plaque : listeIdPlaque) {
 

            try{
                stmt = con.createStatement();
                
                try{
                    rs=stmt.executeQuery("SELECT type_plaque FROM plaque WHERE id_plaque = "+ id_plaque +"");
                while(rs.next()){
                    type_plaque=rs.getString(1);
                }
                            
                }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
                rs=stmt.executeQuery("SELECT count(id_puit) FROM puit WHERE id_plaque = "+ id_plaque +"");
                while(rs.next()){
                    nb_puit=rs.getInt(1);
            
                    if ("96puits".equals(type_plaque)){
                        if (nb_puit < 96) {
                            nb_puit=96-nb_puit;
                            if(nb_puit < 96){
                            dataPlaque.add(new projetbasededonnee.Data.Laborantin(id_plaque, type_plaque, nb_puit));
                            tableAccueilLabo.setItems(dataPlaque);
                            }
                        }
                    }
                    else if ("384puits".equals(type_plaque)){
                        if (nb_puit < 384) {
                            nb_puit=384-nb_puit;
                            if(nb_puit<384){
                            dataPlaque.add(new projetbasededonnee.Data.Laborantin(id_plaque, type_plaque, nb_puit));
                            tableAccueilLabo.setItems(dataPlaque);
                            }
                        }
                    }
                }
            }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
                
        }
    }
   
    /**
     * Methode qui permet d'initialiser le tableau des expériences en Attente
     */
    private void setCellTableARenouveler(){
        dataExpARenouveler = FXCollections.observableArrayList();

        colNumExp.setCellValueFactory(new PropertyValueFactory<>("id_exp"));
        colNomExp.setCellValueFactory(new PropertyValueFactory<>("nom_exp"));
        colNbReplicat.setCellValueFactory(new PropertyValueFactory<>("nb_n_uplet"));
        colTypeAna.setCellValueFactory(new PropertyValueFactory<>("type_ana"));
        colTypeExp.setCellValueFactory(new PropertyValueFactory<>("type_exp"));
        colNbPuits.setCellValueFactory(new PropertyValueFactory<>("nb_puits"));
    }
    /**
     * Methode qui recupere les donnees pour les mettre dans le tableau d'experiences a renouveler
     * Les donnees sont :
     * - numero de l'experience
     * - nom de l'experience
     * - nombre de replicat a renouveler
     * - type d'analyse
     * - type d'experience
     * - nombre de puits total a renouveler
     */
    public void loadDataExpARenouveler(){
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("select distinct id_experience, nomexp, type_analyse, type_exp, nbpuit from EXPERIENCE join N_UPLET using (id_experience) where etat_exp = '"+"A renouveler"+"' and renouveler = "+0+" and terminee = "+ 0+" order by id_experience ASC");
            while(rs.next()){
                idExp=rs.getInt(1);
                nomExp=rs.getString(2);
                typeAna=rs.getString(3);
                typeExp=rs.getString(4);
                nbPuits=rs.getInt(5);
                
                listeIdExp.add(idExp); 
            }
        } catch (Exception e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        
        for (Integer idExperience : listeIdExp) {
            try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("select nomexp, type_analyse, type_exp, nbpuit from EXPERIENCE join N_UPLET using (id_experience) where id_experience = "+ idExperience +" ");
            while(rs.next()){
                nomExp=rs.getString(1);
                typeAna=rs.getString(2);
                typeExp=rs.getString(3);
                nbPuits=rs.getInt(4);
            }
            } catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
            try{
                stmt = con.createStatement();
                rs=stmt.executeQuery("select count(id_n_uplet) from N_UPLET where id_experience = "+ idExperience +"");
                while(rs.next()){
                    nbreUplet=rs.getInt(1);
                }          
            }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
            
            try{
                stmt = con.createStatement();
                rs=stmt.executeQuery("select count(distinct id_n_uplet) from N_Uplet join PUIT using(id_n_uplet) where id_experience = "+ idExperience +"");
                while(rs.next()){
                    nbreUpletTraitee=rs.getInt(1);
                }          
            }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
        nbUpletAR = nbreUplet - nbreUpletTraitee; 
        
        nbPuits = nbPuits * nbUpletAR;
        
        if ((nbPuits/nbUpletAR) <= maPlaque.getPuits_dispo()){
            dataExpARenouveler.add(new projetbasededonnee.Data.Laborantin(idExperience, nomExp, nbUpletAR, typeAna, typeExp, nbPuits));
            tableExpARenouveler.setItems(dataExpARenouveler);
        }
        }
    }
    
    /**
     * Initialisation de la table des experiences en attente
     */
    private void setCellTableEnAttente(){
        dataExpEnAttente = FXCollections.observableArrayList();

        colNumExp2.setCellValueFactory(new PropertyValueFactory<>("id_exp"));
        colNomExp2.setCellValueFactory(new PropertyValueFactory<>("nom_exp"));
        colNbReplicat2.setCellValueFactory(new PropertyValueFactory<>("nb_n_uplet"));
        colTypeAna2.setCellValueFactory(new PropertyValueFactory<>("type_ana"));
        colTypeExp2.setCellValueFactory(new PropertyValueFactory<>("type_exp"));
        colNbPuits2.setCellValueFactory(new PropertyValueFactory<>("nb_puits"));
    }
    
    /**
     * Methode qui recupere les donnees pour les mettre dans le tableau d'experiences en attente
     * Les donnees sont :
     * - numero de l'experience
     * - nom de l'experience
     * - nombre de replicat en attente
     * - type d'analyse
     * - type d'experience
     * - nombre de puits total en attente
     */
    public void loadDataExpEnAttente(){
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("select distinct id_experience, nomexp, type_analyse, type_exp, nbpuit from EXPERIENCE join N_UPLET using (id_experience) where etat_exp = '"+"En Attente"+"' order by id_experience ASC");
            while(rs.next()){
                idExp=rs.getInt(1);
                nomExp=rs.getString(2);
                typeAna=rs.getString(3);
                typeExp=rs.getString(4);
                nbPuits=rs.getInt(5);
                
                listeIdExpEA.add(idExp); 
            }
        } catch (Exception e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        
        for (Integer idExperience2 : listeIdExpEA) {
            try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("select nomexp, type_analyse, type_exp, nbpuit from EXPERIENCE join N_UPLET using (id_experience) where id_experience = "+ idExperience2 +"");
            while(rs.next()){
                nomExp=rs.getString(1);
                typeAna=rs.getString(2);
                typeExp=rs.getString(3);
                nbPuits=rs.getInt(4);
            }
            } catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
            try{
                stmt = con.createStatement();
                rs=stmt.executeQuery("select count(id_n_uplet) from N_UPLET where id_experience = "+ idExperience2 +"");
                while(rs.next()){
                    nbreUplet=rs.getInt(1);
                }          
            }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
            
            try{
                stmt = con.createStatement();
                rs=stmt.executeQuery("select count(distinct id_n_uplet) from N_Uplet join PUIT using(id_n_uplet) where id_experience = "+ idExperience2 +"");
                while(rs.next()){
                    nbreUpletTraitee=rs.getInt(1);
                }          
            }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
        nbUpletAR = nbreUplet - nbreUpletTraitee; 
        
        nbPuits = nbPuits * nbUpletAR;
        
        dataExpEnAttente.add(new projetbasededonnee.Data.Laborantin(idExperience2, nomExp, nbUpletAR, typeAna, typeExp, nbPuits));
        tableExpEnAttente.setItems(dataExpEnAttente);
        }
        
    }
   public void ComboTypePlaque(){
        //Initialisation des combo-box
        cbTypePlaque.getItems().clear();
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT type_plaque FROM TYPE_PLAQUE");
            while (rs.next()) { 
                System.out.println(rs.getString(1));
                cbTypePlaque.getItems().add(rs.getString(1));
            }
        }catch (Exception e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
   }
    
  
            
            
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
        accueilLaboPane.setVisible(true);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(false);
        ComboTypePlaque();
    } 
    
    /**
     * Affichage du panel homePageLab, l'accueil du laborantin
     * @param event
     * @throws IOException 
     */
    @FXML
    public void  CompletePlaque(MouseEvent event) throws IOException {
        
        try{
            stmt = con.createStatement();
            rs= stmt.executeQuery("INSERT INTO PLAQUE (id_plaque, type_plaque, EstRefuse)VALUES("+ 1 + ", '"+ cbTypePlaque.getSelectionModel().getSelectedItem()+ "', "+ 0 +")");
        
            try{
            stmt = con.createStatement();
            rs= stmt.executeQuery("SELECT MAX(id_plaque) FROM PLAQUE");
            while(rs.next()){
                id_plaque=rs.getInt(1); 
            }
        
            }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }

            accueilLaboPane.setVisible(false);
            LancerPlaquePage.setVisible(true); 
            EmplacementPlaquePage.setVisible(false);
            validationPage.setVisible(false);
//            setCellTableARenouveler();
//            loadDataExpARenouveler();
        
        }catch (Exception e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public void setInfoPlaque(){ 
        labelInfoPlaquePuits.setText("Plaque n° " +maPlaque.getId_plaque()+ ". Il reste "+maPlaque.getPuits_dispo()+" puits dans la plaque");
    }
        
    /**
     * Affichage du panel expLabPage, la page où sont affiché les 
     * expérience de laborantin
     * @param event
     * @throws IOException 
     */
    @FXML
    public void experienceLabEvent(MouseEvent event) throws IOException {

        validationPage.setVisible(false);
        
        lancerExpButton.setDisable(true);

        
        
    } 

   /**
     * Affichage du panel validationPage, qui permet de valider une expérience
     * @param event
     * @throws IOException la
     */
    @FXML
    public void validationEvent(MouseEvent event)throws IOException {
        accueilLaboPane.setVisible(false);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(true);
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
    
     public void setConnection(Connexion cone)
    {
        connex = cone;
    } 
    
    public void setPersonne(Personne personneE){
        personne=personneE; 
    }
}
