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
    
    @FXML    private Label erreurAjoutPlaque;
    @FXML    private Button buttonAjoutPlaque;
    @FXML    private Label labelInfoPlaquePuits;
    @FXML    private Button buttonAddAR;
    @FXML    private Button buttonAddEA;
    @FXML    private Label LabelAjoutExpPlaque;
    @FXML    private Label LabelAjoutExpAttentLabel; 
    @FXML    private Button sauvegardePlaque; 
    @FXML    private Label labelLancePlaque; 
    
    private Button lancerExpButton;
    
    private Connexion connex;
    private Connection con;
    private Statement stmt, stmt1, stmt2, stmt3, stmt4; 
    private ResultSet rs, rs1, rs2, rs3, rs4; 
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
    private ArrayList<Integer> listExp= new ArrayList<>();
    private ArrayList<Integer> listNbrePuitsParReplicat = new ArrayList<>();
    private ArrayList<Integer> listNUplet= new ArrayList<>();
    private ArrayList<Integer> listResNbPuit = new ArrayList<>();
    private ArrayList<Integer> listeIdExpEA;
    private ArrayList<Integer> listeIdUplet;
    private ArrayList<Integer> listeIdUplet1;
    private ArrayList<Integer> listeIdExpValid;
    private ArrayList<Integer> listeIdUpletValid;
    private ArrayList<Integer> listeIdExpValidA;
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
        buttonAjoutPlaque.setGraphic(new ImageView(new Image(getClass().getResource("checked.png").toExternalForm(), 20, 20, true, true)));
        
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
                        accueilLaboPane.setVisible(false);
                        LancerPlaquePage.setVisible(true); 
                        EmplacementPlaquePage.setVisible(false);
                        validationPage.setVisible(false);
                        
                        setInfoPlaque();
                        listeIdExp = new ArrayList();
                        listeIdExpValid = new ArrayList(); 
                        listeIdUpletValid = new ArrayList();
                        listeIdUplet = new ArrayList(); 
                        listeIdUplet1 = new ArrayList();
                        setCellTableARenouveler();
                        loadDataExpARenouveler();
                        listeIdExpEA = new ArrayList();
                        listeIdExpValidA = new ArrayList(); 
                        
                        setCellTableEnAttente();
                        loadDataExpEnAttente();
                        buttonAddAR.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));
                        buttonAddEA.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));
                        
                        // disable button
                
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
                try{
                    rs=stmt.executeQuery("SELECT type_plaque FROM plaque WHERE id_plaque = "+ id_plaque +"");
                while(rs.next()){
                    type_plaque=rs.getString(1);
                }
                rs.close();
                            
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
                rs.close();
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
        LabelAjoutExpPlaque.setVisible(false);
       
           try{
            listeIdExp.clear(); 
            listeIdExpValid.clear(); 
            listeIdUplet.clear();
            dataExpARenouveler.clear();
            listeIdUpletValid.clear();
            tableExpARenouveler.getItems().clear();
            
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("select distinct id_experience from EXPERIENCE join N_UPLET using (id_experience) where etat_exp = '"+"A renouveler"+"' and renouveler = "+0+" and terminee = "+ 0+" order by id_experience ASC");
            while(rs.next()){
                idExp=rs.getInt(1);
                listeIdExp.add(idExp); 
            }
        } catch (Exception e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        
        try{
        stmt1 = con.createStatement();
        System.out.println(listeIdExp + "LISTE DES EXPERIENCES");
        for (Integer idExpValid : listeIdExp){
           listeIdUplet.clear(); 
           listeIdUpletValid.clear(); 
           
            rs1=stmt1.executeQuery("select id_n_uplet from N_UPLET WHERE RENOUVELER=0 and TERMINEE=0 and ID_EXPERIENCE = " + idExpValid+"");
            while(rs1.next()){
                int idUplet=rs1.getInt(1);

                    listeIdUplet.add(idUplet);
            } 
            System.out.println(listeIdUplet + " La liste de n_uplet");
            for(Integer idU : listeIdUplet){
                try{
                    rs=stmt.executeQuery("SELECT count(*) FROM PUIT WHERE ID_N_UPLET = "+ idU +"");
                    rs.next();
                    if (rs.getInt(1) ==0){
                        listeIdUpletValid.add(idU);
                    }
                } catch (Exception e) {
                     Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            System.out.println(listeIdUpletValid + " Mes id uplets sont correcte");
            System.out.println(listeIdUpletValid.size() + " Mes id uplets sont correcte taille");
            if(!listeIdUpletValid.isEmpty()){
                rs1=stmt1.executeQuery("select nomexp, type_analyse, type_exp, nbpuit from EXPERIENCE where id_experience = "+ idExpValid +" ");
                while(rs1.next()){
                    System.out.println("Ca rentre");
                    nomExp=rs1.getString(1);
                    typeAna=rs1.getString(2);
                    typeExp=rs1.getString(3);
                    nbPuits=rs1.getInt(4);
                    System.out.println(nbPuits +"nbre de puit");
                }
                Integer nbrePuitUplet = listeIdUpletValid.size()*nbPuits;
                System.out.println(maPlaque.getPuits_dispo() + "plaque");
                System.out.println(nbrePuitUplet + "nbe puit uplet");
                if (nbrePuitUplet <= maPlaque.getPuits_dispo()){
                    System.out.println("PASSE DANS LE IF ");
                    dataExpARenouveler.add(new projetbasededonnee.Data.Laborantin(idExpValid, nomExp, listeIdUpletValid.size(), typeAna, typeExp, nbrePuitUplet));
                    tableExpARenouveler.setItems(dataExpARenouveler);
                    System.out.println(dataExpARenouveler + " LA LISTE OBSERVABLE");
                }
            }
        
        }      
                
        }catch (Exception e) {
        Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
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
        LabelAjoutExpAttentLabel.setVisible(true); 
        con = connex.getCon(); 
        listeIdExpEA.clear();
        listeIdExpValidA.clear(); 
        listeIdUplet.clear();
        dataExpEnAttente.clear();
        listeIdUpletValid.clear();
        tableExpEnAttente.getItems().clear();
        try{
            stmt = con.createStatement();
            rs=stmt.executeQuery("select distinct(id_experience) from EXPERIENCE join N_UPLET using (id_experience) where etat_exp = '"+"En Attente"+"'order by id_experience ASC");
            while(rs.next()){
                idExp=rs.getInt(1);
                System.out.println(idExp + "IDEXPERIENCE");
                listeIdExpEA.add(idExp); 
            }
        } catch (Exception e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        try{
        stmt1 = con.createStatement();
        for (Integer idExpValid : listeIdExpEA){
            listeIdUplet.clear(); 
            listeIdUpletValid.clear();
            
            rs1=stmt1.executeQuery("select id_n_uplet from N_UPLET WHERE TERMINEE=0 and ID_EXPERIENCE = " + idExpValid+"");
            while(rs1.next()){
                int idUplet=rs1.getInt(1);

                    listeIdUplet.add(idUplet);
            }
            for(Integer idU : listeIdUplet){
                try{
                    rs=stmt.executeQuery("SELECT count(*) FROM PUIT WHERE ID_N_UPLET = "+ idU +"");
                    rs.next();
                    if (rs.getInt(1) ==0){
                        listeIdUpletValid.add(idU);
                    }
                } catch (Exception e) {
                     Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if(!listeIdUpletValid.isEmpty()){
       
                rs1=stmt1.executeQuery("select nomexp, type_analyse, type_exp, nbpuit from EXPERIENCE where id_experience = "+ idExpValid +" ");
                while(rs1.next()){
                    System.out.println("Ca rentre");
                    nomExp=rs1.getString(1);
                    typeAna=rs1.getString(2);
                    typeExp=rs1.getString(3);
                    nbPuits=rs1.getInt(4);
                    System.out.println(nbPuits +"nbre de puit");
                }
                Integer nbrePuitUplet = listeIdUpletValid.size()*nbPuits;
                System.out.println(maPlaque.getPuits_dispo() + "plaque");
                System.out.println(nbrePuitUplet + "nbe puit uplet");
                if (nbrePuitUplet <= maPlaque.getPuits_dispo()){
                    System.out.println("PASSE DANS LE IF ");
                    dataExpEnAttente.add(new projetbasededonnee.Data.Laborantin(idExpValid, nomExp, listeIdUpletValid.size(), typeAna, typeExp, nbrePuitUplet));
                    tableExpEnAttente.setItems(dataExpEnAttente);
                    System.out.println(dataExpARenouveler + " LA LISTE OBSERVABLE");
                }
            }
        
        }
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
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
        loadDataPlaque();
        erreurAjoutPlaque.setVisible(false);
    } 
    
    /**
     * Affichage du panel homePageLab, l'accueil du laborantin
     * @param event
     * @throws IOException 
     */
    @FXML
    public void  CompletePlaque(MouseEvent event) throws IOException {
        if (cbTypePlaque.getSelectionModel().getSelectedItem()!= null) {
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

            }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        else {
            erreurAjoutPlaque.setVisible(true);
        }
    }
    
    public void setInfoPlaque(){ 
        Integer nb_puits_plaque=0;
        try{
            stmt = con.createStatement();
            rs= stmt.executeQuery("SELECT count(*) FROM PUIT WHERE ID_PLAQUE= "+ maPlaque.getId_plaque() +"");
            while(rs.next()){
                nb_puits_plaque=rs.getInt(1); 
            }
            if ("96puits".equals(maPlaque.getType_plaque())){
                maPlaque.setPuits_dispo(96-nb_puits_plaque);    
            }
            else
            {
                maPlaque.setPuits_dispo(384-nb_puits_plaque); 
            }
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        labelInfoPlaquePuits.setText("Plaque n° " +maPlaque.getId_plaque()+ ". Il reste "+maPlaque.getPuits_dispo()+" puits dans la plaque");
        if(maPlaque.getPuits_dispo()==0){
            home.setDisable(true);
            resultat.setDisable(true);
            deconnexionIV.setDisable(true);
            buttonAddAR.setDisable(true);
            buttonAddEA.setDisable(true);
            sauvegardePlaque.setDisable(true);
            labelLancePlaque.setVisible(true);
        }
    
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

        erreurAjoutPlaque.setVisible(false);
        
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
    
    public void AddUpletPlaque(ActionEvent event){
      
        AjoutXYPlaque(tableExpARenouveler.getSelectionModel().getSelectedItem(),maPlaque);
        setCellTableARenouveler();
        loadDataExpARenouveler();
        setInfoPlaque();
        LabelAjoutExpPlaque.setVisible(true);
    }
    
    public void AddUpletPlaqueEnAtt(ActionEvent event){
      
        AjoutXYPlaque(tableExpEnAttente.getSelectionModel().getSelectedItem(),maPlaque);
        setCellTableEnAttente();
        loadDataExpEnAttente();
        setInfoPlaque();
        LabelAjoutExpAttentLabel.setVisible(true);
    }
    
    public void AjoutXYPlaque(projetbasededonnee.Data.Laborantin experience, projetbasededonnee.Data.Laborantin plaque){
        listeIdUplet = new ArrayList(); 
        con = connex.getCon();      
    try{
        stmt4 = con.createStatement();
        stmt = con.createStatement();
        rs4 = stmt4.executeQuery("SELECT id_n_uplet FROM N_UPLET WHERE id_experience = " + experience.getId_exp() + " and terminee = "+ 0 + "");
        while (rs4.next()) { 
            
            Integer id_uplet= rs4.getInt(1);
            System.out.println(id_uplet);
            listeIdUplet.add(id_uplet); 
        }
    }catch (Exception e) {
        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
    }
        
        if ("96puits".equals(plaque.getType_plaque())){
            Integer nbrePuit_Uplet= experience.getNb_puits()/experience.getNb_n_uplet();
            Integer puits_dispo=plaque.getPuits_dispo(); 
            Integer puits_exp=experience.getNb_puits(); 
            System.out.println(nbrePuit_Uplet + "nbre puit par upet");
            System.out.println(puits_dispo + "puit dispo" );
            System.out.println(puits_exp +" puit expe total");
            if (puits_dispo >=  puits_exp){
                if (puits_dispo == 96){
                    int X=1;
                    int Y=1;
                    System.out.println("plaque vide");
                    for (Integer idUplet : listeIdUplet) {
                        System.out.println(idUplet);
                        for (int i=1; i <= nbrePuit_Uplet; i++){
                            try{
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+")");   
                            maPlaque.setPuits_dispo(maPlaque.getPuits_dispo()-1);    
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
                else //Si la plaque n'est pas vide
                {   int X=0; 
                    int Y=0; 
                   
                    try{
                       
                        rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getId_plaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                        while (rs.next()) { 
                            X = rs.getInt(1);
                            Y = rs.getInt(2);
                            System.out.println(rs.getInt(1));
                            System.out.println(rs.getInt(2));
                        }
                        System.out.println(X);
                        System.out.println(Y);
                        
                    }catch (Exception e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    
                    for (Integer idUplet : listeIdUplet) {
                        System.out.println(idUplet);
                        for (int i=1; i <= nbrePuit_Uplet; i++){
                            System.out.println(nbrePuit_Uplet);
                            System.out.println(i); 
                            if (Y == 8) {
                                X=X+1;
                                Y=1;
                            }
                            else
                            {
                                Y=Y+1;
                            }
                            
                            try{
                          
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+")");
                            maPlaque.setPuits_dispo(maPlaque.getPuits_dispo()-1);   
                            }catch (Exception e) {
                                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    }     
                }
                    
            }
            else //Je ne peux pas mettre tous les n_uplets sur la plaque
            {
                Integer nbreUpletAInserer = puits_dispo/nbrePuit_Uplet;
                Integer nb=1;
                System.out.println(nbreUpletAInserer + "nbre uplet a inserer");
                int X=0; 
                int Y=0; 
                try{
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getId_plaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                    while (rs.next()) { 
                        X = rs.getInt(1);
                        Y = rs.getInt(2);
                    }
                    System.out.println(X);
                    System.out.println(Y);

                }catch (Exception e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
                while( nb <= nbreUpletAInserer){
                    System.out.println(nb + " nb");
                    Integer idUplet=listeIdUplet.get(nb);
                     for (int i=1; i <= nbrePuit_Uplet; i++){
                        System.out.println(i);
                        if (Y == 8) {
                            X=X+1;
                            Y=1;
                        }
                        else
                        {
                            Y=Y+1;
                        }
                        System.out.println(X);
                        System.out.println(Y);
                        try{
                        rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+")");
                        maPlaque.setPuits_dispo(maPlaque.getPuits_dispo()-1);   
                        System.out.println("INSERTION DANS UN PUIT SUR PLAQUE MAIS PAS TOUTE EXPERIENCE");
                        }catch (Exception e) {
                            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } 
                    nb=nb+1;
                }
            }
            }
        
        
        else if ("384puits".equals(plaque.getType_plaque())){
            
            Integer nbrePuit_Uplet= experience.getNb_puits()/experience.getNb_n_uplet();
            Integer puits_dispo=plaque.getPuits_dispo(); 
            Integer puits_exp=experience.getNb_puits(); 
            System.out.println(nbrePuit_Uplet + "nbre puit par upet");
            System.out.println(puits_dispo + "puit dispo" );
            System.out.println(puits_exp +" puit expe total");
            if (puits_dispo >=  puits_exp){
                if (puits_dispo == 384){
                    int X=1;
                    int Y=1;

                   
                    for (Integer idUplet : listeIdUplet) {
                        System.out.println(idUplet);
                        for (int i=1; i <= nbrePuit_Uplet; i++){
                            try{
                            stmt = con.createStatement();
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+")");
                            maPlaque.setPuits_dispo(maPlaque.getPuits_dispo()-1);   
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
                else //Si la plaque n'est pas vide
                {   int X=0; 
                    int Y=0; 
                    try{
                      
                        rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getId_plaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                        while (rs.next()) { 
                            X = rs.getInt(1);
                            Y = rs.getInt(2);
                        }
                        System.out.println(X);
                        System.out.println(Y);
                        
                    }catch (Exception e) {
                        Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    
                    for (Integer idUplet : listeIdUplet) {
                        System.out.println(idUplet);
                        for (int i=1; i <= nbrePuit_Uplet; i++){
                            System.out.println(nbrePuit_Uplet);
                            System.out.println(i); 
                            if (Y == 16) {
                                X=X+1;
                                Y=1;
                            }
                            else
                            {
                                Y=Y+1;
                            }
                            
                            try{
                            
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+")");
                            maPlaque.setPuits_dispo(maPlaque.getPuits_dispo()-1);   
                            }catch (Exception e) {
                                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    }     
                }
                    
            }
            else //Je ne peux pas mettre tous les n_uplets sur la plaque
            {
                Integer nbreUpletAInserer = puits_dispo/nbrePuit_Uplet;
                Integer nb=1;
                System.out.println(nbreUpletAInserer + "nbre uplet a inserer");
                int X=0; 
                int Y=0; 
                try{
                    
                    rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getId_plaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                    while (rs.next()) { 
                        X = rs.getInt(1);
                        Y = rs.getInt(2);
                    }
                    System.out.println(X);
                    System.out.println(Y);

                }catch (Exception e) {
                    Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                }
                while( nb <= nbreUpletAInserer){
                    System.out.println(nb + " nb");
                    Integer idUplet=listeIdUplet.get(nb);
                     for (int i=1; i <= nbrePuit_Uplet; i++){
                        System.out.println(i);
                        if (Y == 16) {
                            X=X+1;
                            Y=1;
                        }
                        else
                        {
                            Y=Y+1;
                        }
                        System.out.println(X);
                        System.out.println(Y);
                        try{
                    
                        rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getId_plaque() +", "+ idUplet +", "+X+", "+Y+")");
                        maPlaque.setPuits_dispo(maPlaque.getPuits_dispo()-1);   
                        System.out.println("INSERTION DANS UN PUIT SUR PLAQUE MAIS PAS TOUTE EXPERIENCE");
                        }catch (Exception e) {
                            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } 
                    nb=nb+1;
                }
            }
        }
    }
    
    public void lancerPlaque(){
        con = connex.getCon();
        // recuperation de chaque id_experience et le nbpuit [i.e. par replicat] de l'experience de la plaque sur laquelle je suis
        // j'ajoute les id_exp dans la listExp et le nbpuit dans la listNbrePuitsParReplicat
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("select distinct id_experience, nbpuit from EXPERIENCE join N_UPLET using (id_experience) join PUIT using (id_n_uplet) where id_plaque = "+ maPlaque.getId_plaque());
            while (rs.next()) { 
                listExp.add(rs.getInt(1));
               // listNbrePuitsParReplicat.add(rs.getInt(2));
            }
            rs.close();
        }catch (Exception e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        // je recupere le nombre de n_uplet de chaque experience et je les ajoute dans la listNUplet
        for (int i=0; i < listExp.size(); i++) {
            listNUplet.clear();
            listResNbPuit.clear();
            // recupere tous les n_uplet de l'experience
            try{
                rs = stmt.executeQuery("select id_n_uplet from EXPERIENCE join n_uplet using(id_experience) join PUIT using (id_n_uplet) where  id_experience = "+ listExp.get(i));
                while (rs.next()) { 
                    listNUplet.add(rs.getInt(1));
                }
                rs.close();
            }catch (Exception e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
            
            // Recupere le nombre de puit dans l'uplet 
            for (int j=0; j < listNUplet.size(); j++) {
                try{
                    rs = stmt.executeQuery("select count(id_puit) from PUIT where  id_n_uplet = "+ listNUplet.get(j));
                    while (rs.next()) { 
                        listResNbPuit.add(rs.getInt(1));
                    }
                    rs.close();
                }catch (Exception e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            // si la liste contient pas 0 alors passage de l'experience a 'en cours'
            if(!listResNbPuit.contains(0)){
                try{
                    rs = stmt.executeQuery("update EXPERIENCE set etat_exp = 'En Cours' where id_experience = "+ listExp.get(i));
                    rs.close();
                }catch (Exception e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }        
        resultat.setDisable(false);
        deconnexionIV.setDisable(false);
        buttonAddAR.setDisable(false);
        buttonAddEA.setDisable(false);
//        sauvegardePlaque.setDisable(false);
//        labelLancePlaque.setVisible(false);
       
    }
    
     public void setConnection(Connexion cone)
    {
        connex = cone;
    } 
    
    public void setPersonne(Personne personneE){
        personne=personneE; 
    }
}
