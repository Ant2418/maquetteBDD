/*
 * Antoine 17/04/2019
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
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
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
    @FXML    private AnchorPane resultatExp;
    @FXML    private VBox titleAcceuil1;
    @FXML    private TableView<projetbasededonnee.Data.Laborantin> tableAccueilLabo;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNumPlaque;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colTypePlaque;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colPuitsDispo;
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
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNumExp;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNomExp;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNbReplicat;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colTypeAna;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colTypeExp;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNbPuits;
    
    @FXML    private TableView<projetbasededonnee.Data.Laborantin> tableExpEnAttente;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNumExp2;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNomExp2;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNbReplicat2;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colTypeAna2;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colTypeExp2;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNbPuits2;
    
    @FXML    private Label erreurAjoutPlaque;
    @FXML    private Button buttonAjoutPlaque;
    @FXML    private Label labelInfoPlaquePuits;
    @FXML    private Button buttonAddAR;
    @FXML    private Button buttonAddEA;
    @FXML    private Label LabelAjoutExpPlaque;
    @FXML    private Label LabelAjoutExpAttentLabel; 
    @FXML    private Button sauvegardePlaque; 
    @FXML    private Button ButtonPlaque;
    @FXML    private Label labelLancePlaque; 
    @FXML    private Button buttonLancerPlaque;
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
    private Integer id_pers; 
    
    //Pour la tableau emplacement plaque
    @FXML    private TableView<projetbasededonnee.Data.Laborantin> tableEmplacementPlaque;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colX;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colY;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colAgentBio;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colQteBio;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colCellule;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colQteCellule;
    @FXML    private Button ButtonRetourComplPlaque;
    
    @FXML    private TableView<projetbasededonnee.Data.AccueilChercheur> tableResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colIdExpResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colNomResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colEtatResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colTypeExpResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colTypeAnaResult;
    
    
    //liste observable
    private ObservableList<projetbasededonnee.Data.Laborantin> dataPlaque;
    private ObservableList<projetbasededonnee.Data.Laborantin> dataExpARenouveler;
    private ObservableList<projetbasededonnee.Data.Laborantin> dataExpEnAttente;
    private ObservableList<projetbasededonnee.Data.Laborantin> dataEmplacementPlaque;
    //L'observable liste pour le tableau des expériences
    private ObservableList<projetbasededonnee.Data.AccueilChercheur> dataExpResult;
    private ArrayList<Integer> listeIdPlaque;
    private ArrayList<Integer> listeIdExp;
    private final ArrayList<Integer> listExp= new ArrayList<>();
    private final ArrayList<Integer> listNbrePuitsParReplicat = new ArrayList<>();
    private final ArrayList<Integer> listNUplet= new ArrayList<>();
    private final ArrayList<Integer> listResNbPuit = new ArrayList<>();
    private ArrayList<Integer> listeIdExpEA;
    private ArrayList<Integer> listeIdUplet;
    private ArrayList<Integer> listeIdUplet1;
    private ArrayList<Integer> listeIdExpValid;
    private ArrayList<Integer> listeIdUpletValid;
    private ArrayList<Integer> listeIdExpValidA;
    private projetbasededonnee.Data.Laborantin maPlaque;
    
     //Label pour l'anchorpane validerResultat
    @FXML    private Label LabelValidExpResult;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accueilLaboPane.setVisible(true);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(false);
        resultatExp.setVisible(false);
        dataPlaque = FXCollections.observableArrayList();
        dataExpResult = FXCollections.observableArrayList();
        listeIdPlaque= new ArrayList();
        setCellTablePlaque();
        buttonAjoutPlaque.setGraphic(new ImageView(new Image(getClass().getResource("checked.png").toExternalForm(), 20, 20, true, true)));
        
    }    

    /**
     * Initialisation des colonnes pour le tableau de la plaque accueil labo
     * Creation du bouton editer et des autres colonnes
     */
    private void setCellTablePlaque(){
  
        tableAccueilLabo.getColumns().clear();
        
        
        colNumPlaque = new TableColumn<>("id_plaque");
        colNumPlaque.setCellValueFactory(new PropertyValueFactory<>("id_plaque"));
        
        colTypePlaque = new TableColumn<>("Type_plaque");
        colTypePlaque.setCellValueFactory(new PropertyValueFactory<>("Type_plaque"));
        
        colPuitsDispo = new TableColumn<>("puits_dispo");
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
                        resultatExp.setVisible(false);

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
                        LabelAjoutExpAttentLabel.setVisible(false);
                        LabelAjoutExpPlaque.setVisible(false);
                        buttonAddAR.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));
                        buttonAddEA.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));
                        setInfoPlaque(maPlaque);
                        labelLancePlaque.setVisible(false);
                
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
        tableAccueilLabo.getColumns().addAll(colNumPlaque,colTypePlaque,colPuitsDispo,editerColonne);
    
  
    }

    /**
     * Initialisation du tableau des plaques dans l'accueil
     */
    public void loadDataPlaque(){
        dataPlaque.clear();
        listeIdPlaque.clear();
        
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            
//A VERIFIER QUAND ON LANCE UNE PLAQUE QUE LA PLAQUE NE SOIT PLUS DANS LE TABLEAU            
            rs=stmt.executeQuery("SELECT distinct id_plaque FROM PLAQUE JOIN PUIT USING(id_plaque) join N_UPLET using(id_n_uplet) join experience using(id_experience) where etat_exp is not '"+"En Cours"+"' ");
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
               
                            
                }catch (SQLException e) {
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
            }catch (SQLException e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
                
        }
    }
   
    /**
     * Initialisation des colonnes pour le tableau des résultats des expériences
     * Creation du bouton détails et des autres colonnes
     */
    public void setCellTableResult(){
        tableResult.getColumns().clear();
        
        colIdExpResult = new TableColumn<>("id_exp");
        colIdExpResult.setCellValueFactory(new PropertyValueFactory<>("id_exp"));
        
        colNomResult = new TableColumn<>("nom_exp");
        colNomResult.setCellValueFactory(new PropertyValueFactory<>("nom_exp"));
        
        colEtatResult = new TableColumn<>("etat_exp");
        colEtatResult.setCellValueFactory(new PropertyValueFactory<>("etat_exp"));
       
        colTypeExpResult = new TableColumn<>("type_exp");
        colTypeExpResult.setCellValueFactory(new PropertyValueFactory<>("type_exp"));
        
        colTypeAnaResult = new TableColumn<>("type_analyse");
        colTypeAnaResult.setCellValueFactory(new PropertyValueFactory<>("type_analyse"));
        
        //Set Edit button column
        TableColumn<projetbasededonnee.Data.AccueilChercheur, Void> detailColonne = new TableColumn("Details");
        
        Callback<TableColumn<projetbasededonnee.Data.AccueilChercheur, Void>, TableCell<projetbasededonnee.Data.AccueilChercheur, Void>> cellFactory = (final TableColumn<projetbasededonnee.Data.AccueilChercheur, Void> param) -> {
            final TableCell<projetbasededonnee.Data.AccueilChercheur, Void> cell = new TableCell<projetbasededonnee.Data.AccueilChercheur, Void>() {
                
                private final Button btnDet = new Button("Résultats");
                {
                    
                    btnDet.setOnAction((ActionEvent event) -> {
                        
                        projetbasededonnee.Data.AccueilChercheur monExp = getTableView().getItems().get(getIndex());
                        LabelValidExpResult.setText("Résultat de l'expérience numéro " + monExp.getId_exp());
                        accueilLaboPane.setVisible(false);
                        LancerPlaquePage.setVisible(false); 
                        EmplacementPlaquePage.setVisible(false);
                        validationPage.setVisible(true);
                        resultatExp.setVisible(false);
                        
                        home.setDisable(true);
                        resultat.setDisable(true);
                        deconnexionIV.setDisable(true);
                
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btnDet);
                        setStyle("-fx-alignment : CENTER;");
                         //Récupération de l'expérience concernée
                        projetbasededonnee.Data.AccueilChercheur experience = getTableView().getItems().get(getIndex());
                        if ("Terminee".equals(experience.getEtat_exp())) {
                            btnDet.setDisable(false);
                        }
                         else{
                            btnDet.setDisable(true);
                        }
                        
                    }
                }
            };
            return cell;
        };
        detailColonne.setCellFactory(cellFactory);
        tableResult.getColumns().addAll(colIdExpResult,colNomResult,colEtatResult,colTypeExpResult,colTypeAnaResult,detailColonne);

    }
    
    /**
     * Initialiser les valeurs de tableau des résultats
     */
    public void loadDataResultExp(){
        dataExpResult.clear();
        tableResult.getItems().clear();  
        
       //Recuperer l'id de la personne connectée
       try{
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE EMAIL = '" + personne.getEmail() + "'");
            while (rs2.next()) { 
                id_pers = rs2.getInt(1);
            }
            rs2.close();
            stmt2.close();
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
      
        //Récuperer les informations des expériences appartenant à la personne connectée
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("select id_experience, nomExp, etat_Exp, type_exp, type_analyse from EXPERIENCE JOIN FAIT USING(id_experience) where id_personne = "+ id_pers+"");
            while(rs.next()){          
                projetbasededonnee.Data.AccueilChercheur exp= new projetbasededonnee.Data.AccueilChercheur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                dataExpResult.add(exp);

            }
            tableResult.setItems(dataExpResult);
        } catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
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
        } catch (SQLException e) {
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
                } catch (SQLException e) {
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
                
        }catch (SQLException e) {
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
        } catch (SQLException e) {
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
                } catch (SQLException e) {
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
                 
   
    /**
     * Creer la combobox pour le choix du type de plaque pour l'accueil
     */
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
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
   }
        
    /**
     * Evenement quand on clique sur le bouton deconnexion
     * Retour à la page de connexion
     * @param event
     * @throws IOException
     * @throws SQLException 
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
        accueilLaboPane.setVisible(true);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(false);
        resultatExp.setVisible(false);
        ComboTypePlaque();
        loadDataPlaque();
        erreurAjoutPlaque.setVisible(false);
    } 
    
    /**
     * Affichage du panel homePageLab, l'accueil du laborantin
     * Ajout d'une nouvelle plaque
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

                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }

                accueilLaboPane.setVisible(false);
                LancerPlaquePage.setVisible(true); 
                EmplacementPlaquePage.setVisible(false);
                validationPage.setVisible(false);
                resultatExp.setVisible(false);
                //maPlaque.setId_plaque(id_plaque);
                //maPlaque.setType_plaque((String) cbTypePlaque.getSelectionModel().getSelectedItem());
                if ("96puits".equals((String)cbTypePlaque.getValue())){
                    //martin.cmaPlaque.setPuits_dispo(96);
                    maPlaque = new projetbasededonnee.Data.Laborantin(id_plaque,(String) cbTypePlaque.getSelectionModel().getSelectedItem(),96);
                    System.out.println("96 plaque");
                }
                else if ("384puits".equals((String) cbTypePlaque.getSelectionModel().getSelectedItem()))
                {   
                    //maPlaque.setPuits_dispo(384);
                    maPlaque = new projetbasededonnee.Data.Laborantin(id_plaque,(String) cbTypePlaque.getSelectionModel().getSelectedItem(),384);
                    System.out.println("384 plaque");
                }
                //Creer une nouvelle plaque
               //labelInfoPlaquePuits.setText("Plaque n° " +maPlaque.getId_plaque()+ ". Il reste "+maPlaque.getPuits_dispo()+" puits dans la plaque");
                setInfoPlaque(maPlaque);
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
                LabelAjoutExpAttentLabel.setVisible(false);
                LabelAjoutExpPlaque.setVisible(false);
                buttonAddAR.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));
                buttonAddEA.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));

                buttonAddAR.setDisable(false);
                buttonAddEA.setDisable(false);
                home.setDisable(true);
                resultat.setDisable(true);
                deconnexionIV.setDisable(true);
                sauvegardePlaque.setDisable(true);
                buttonLancerPlaque.setDisable(true); 
                

            }catch (SQLException e) {
                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
            }
            
            
        }
        else {
            erreurAjoutPlaque.setVisible(true);
        }
    }
    
    /**
     * Ajout d'une nouvelle plaque quand on clique sur la valider avec le clavier
     * @param event 
     */
    public void ajoutPlaqueKeyPresse(KeyEvent event){
        
        if (event.getCode() == ENTER) {
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

                    }catch (SQLException e) {
                        Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                    }

                    accueilLaboPane.setVisible(false);
                    LancerPlaquePage.setVisible(true); 
                    EmplacementPlaquePage.setVisible(false);
                    validationPage.setVisible(false);
                    resultatExp.setVisible(false);
                    //maPlaque.setId_plaque(id_plaque);
                    //maPlaque.setType_plaque((String) cbTypePlaque.getSelectionModel().getSelectedItem());
                    if ("96puits".equals((String)cbTypePlaque.getValue())){
                        //martin.cmaPlaque.setPuits_dispo(96);
                        maPlaque = new projetbasededonnee.Data.Laborantin(id_plaque,(String) cbTypePlaque.getSelectionModel().getSelectedItem(),96);
                        System.out.println("96 plaque");
                    }
                    else if ("384puits".equals((String) cbTypePlaque.getSelectionModel().getSelectedItem()))
                    {   
                        //maPlaque.setPuits_dispo(384);
                        maPlaque = new projetbasededonnee.Data.Laborantin(id_plaque,(String) cbTypePlaque.getSelectionModel().getSelectedItem(),384);
                        System.out.println("384 plaque");
                    }
                    //Creer une nouvelle plaque
                   //labelInfoPlaquePuits.setText("Plaque n° " +maPlaque.getId_plaque()+ ". Il reste "+maPlaque.getPuits_dispo()+" puits dans la plaque");
                    setInfoPlaque(maPlaque);
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
                    LabelAjoutExpAttentLabel.setVisible(false);
                    LabelAjoutExpPlaque.setVisible(false);
                    buttonAddAR.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));
                    buttonAddEA.setGraphic(new ImageView(new Image(getClass().getResource("plus.png").toExternalForm(), 20, 20, true, true)));

                    buttonAddAR.setDisable(false);
                    buttonAddEA.setDisable(false);
                    home.setDisable(true);
                    resultat.setDisable(true);
                    deconnexionIV.setDisable(true);
                    sauvegardePlaque.setDisable(true);
                    buttonLancerPlaque.setDisable(true); 


                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }


            }
            else {
                erreurAjoutPlaque.setVisible(true);
            }
        }
    }
    
    /**
     * Affiche le label du numéro de la plaque et du nombre de puits restants mis à jour
     * @param maPlaque 
     */
    public void setInfoPlaque(projetbasededonnee.Data.Laborantin maPlaque){ 
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
            buttonLancerPlaque.setDisable(false); 
        }
        else{
            home.setDisable(true);
            resultat.setDisable(true);
            deconnexionIV.setDisable(true);
            buttonAddAR.setDisable(false);
            buttonAddEA.setDisable(false);
            sauvegardePlaque.setDisable(false);
            buttonLancerPlaque.setDisable(false); 
            labelLancePlaque.setVisible(false);
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
        resultatExp.setVisible(false);
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
        validationPage.setVisible(false);
        resultatExp.setVisible(true);
        
        home.setDisable(false);
        resultat.setDisable(true);
        deconnexionIV.setDisable(false);
        
        setCellTableResult();
        loadDataResultExp();
        
    }
    
    /**
     * Evenement quand on clique sur l'icone + et que une expérience est selectionnée
     * dans le tableau
     * ajoute l'expérience a la plaque
     * @param event 
     */
    public void AddUpletPlaque(ActionEvent event){
      
        if (tableExpARenouveler.getSelectionModel().getSelectedItem()!=null){
            AjoutXYPlaque(tableExpARenouveler.getSelectionModel().getSelectedItem(),maPlaque);
            setCellTableARenouveler();
            loadDataExpARenouveler();
            setInfoPlaque(maPlaque);
            LabelAjoutExpPlaque.setText("Expérience bien ajoutée");
            LabelAjoutExpPlaque.setVisible(true);
            home.setDisable(true);
          
        }
        else{
            LabelAjoutExpPlaque.setText("Veuillez selectionner une ligne");
            LabelAjoutExpPlaque.setVisible(true);
        }
    }
    
    /**
     * Evenement quand on clique avec ENTER sur le clavier 
     * sur l'icone + et que une expérience est selectionnée
     * dans le tableau
     * @param event 
     */
    public void AddUpletPlaqueKeyPress(KeyEvent event){
        if (event.getCode()==ENTER){
            if (tableExpARenouveler.getSelectionModel().getSelectedItem()!=null){
            AjoutXYPlaque(tableExpARenouveler.getSelectionModel().getSelectedItem(),maPlaque);
            setCellTableARenouveler();
            loadDataExpARenouveler();
            setInfoPlaque(maPlaque);
            LabelAjoutExpPlaque.setText("Expérience bien ajoutée");
            LabelAjoutExpPlaque.setVisible(true);
            home.setDisable(true);

            }
            else{
                LabelAjoutExpPlaque.setText("Veuillez selectionner une ligne");
                LabelAjoutExpPlaque.setVisible(true);
            }
        }
    }
    
    /**
     * Evenement quand on clique sur l'icone +  et que l'on a selectionnner sur 
     * une ligne du tableau des expériences en attente
     * ajoute les n_uplets de l'expérience sur la plaque
     * @param event 
     */
    public void AddUpletPlaqueEnAtt(ActionEvent event){
      
        if(tableExpEnAttente.getSelectionModel().getSelectedItem() != null){
            AjoutXYPlaque(tableExpEnAttente.getSelectionModel().getSelectedItem(),maPlaque);
            setCellTableEnAttente();
            loadDataExpEnAttente();
            setInfoPlaque(maPlaque);
            LabelAjoutExpAttentLabel.setText("Expérience bien ajoutée");
            LabelAjoutExpAttentLabel.setVisible(true);
        }
        else{
            LabelAjoutExpAttentLabel.setText("Veuillez selectionner une ligne");
            LabelAjoutExpAttentLabel.setVisible(true);
        }
        
    }
    
    public void addUpletPlaqueEnAttKeyPress(KeyEvent event){
        if(event.getCode()==ENTER){
            if(tableExpEnAttente.getSelectionModel().getSelectedItem() != null){
                AjoutXYPlaque(tableExpEnAttente.getSelectionModel().getSelectedItem(),maPlaque);
                setCellTableEnAttente();
                loadDataExpEnAttente();
                setInfoPlaque(maPlaque);
                LabelAjoutExpAttentLabel.setText("Expérience bien ajoutée");
                LabelAjoutExpAttentLabel.setVisible(true);
            }
            else{
                LabelAjoutExpAttentLabel.setText("Veuillez selectionner une ligne");
                LabelAjoutExpAttentLabel.setVisible(true);
            }
        }
    }
    /**
     * Methode qui creer les puits de l'expérience avec les X et les Y
     * @param experience
     * @param plaque 
     */
    public void AjoutXYPlaque(projetbasededonnee.Data.Laborantin experience, projetbasededonnee.Data.Laborantin plaque){
        listeIdUplet = new ArrayList(); 
        listeIdUpletValid = new ArrayList();
        listeIdUplet.clear();
        listeIdUpletValid.clear(); 
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
    }catch (SQLException e) {
        Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
    }

    for(Integer idU : listeIdUplet){
        try{
            rs=stmt.executeQuery("SELECT count(*) FROM PUIT WHERE ID_N_UPLET = "+ idU +"");
            rs.next();
            if (rs.getInt(1) ==0){
                listeIdUpletValid.add(idU);
            }
            
        } catch (SQLException e) {
             Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
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
                    for (Integer idUplet : listeIdUpletValid) {
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
                            
                            }catch (SQLException e) {
                                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
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
                        
                    }catch (SQLException e) {
                        Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                    }
                    
                    for (Integer idUplet : listeIdUpletValid) {
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
                            }catch (SQLException e) {
                                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
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

                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                while( nb <= nbreUpletAInserer){
                    System.out.println(nb + " nb");
                    Integer idUplet=listeIdUpletValid.get(nb);
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
                        }catch (SQLException e) {
                            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
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

                   
                    for (Integer idUplet : listeIdUpletValid) {
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
                            
                            }catch (SQLException e) {
                                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
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
                        
                    }catch (SQLException e) {
                        Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                    }
                    
                    for (Integer idUplet : listeIdUpletValid) {
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
                            }catch (SQLException e) {
                                Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
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

                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                while( nb <= nbreUpletAInserer){
                    System.out.println(nb + " nb");
                    Integer idUplet=listeIdUpletValid.get(nb);
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
                        }catch (SQLException e) {
                            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } 
                    nb=nb+1;
                }
            }
        }
    }
    /**
     * permet de lancer une plaque #COMMENTAIRE A MODIFIER
     */
    public void lancerPlaque(){
        con = connex.getCon();
        projetbasededonnee.Data.CurrentDate Date = new projetbasededonnee.Data.CurrentDate();
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
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        // je recupere le nombre de n_uplet de chaque experience et je les ajoute dans la listNUplet
        for (int i=0; i < listExp.size(); i++) {
            listNUplet.clear();
            listResNbPuit.clear();
            // recupere tous les n_uplet de l'experience
            try{
                rs = stmt.executeQuery("select id_n_uplet from N_UPLET where  id_experience = "+ listExp.get(i));
                while (rs.next()) { 
                    listNUplet.add(rs.getInt(1));
                }
                rs.close();
            }catch (SQLException e) {
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
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            System.out.println("Exp "+listExp.get(i)+" listResNbPuit "+listResNbPuit);
            // si la liste contient pas 0 alors passage de l'experience a 'en cours'
            if (listResNbPuit.contains(0)){
                // nothing
            }else{
                try{
                    rs = stmt.executeQuery("update EXPERIENCE set etat_exp = 'En Cours', horodatage_deb = " + Date.getdateFormat().format(Date.getDate())+" where id_experience = "+ listExp.get(i));
                    rs.close();
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                //A VERIFIER
                try{
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE EMAIL = '" + personne.getEmail() + "'");
                    while (rs2.next()) { 
                        id_pers = rs2.getInt(1);
                    }
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                try{
                    rs=stmt.executeQuery("INSERT INTO FAIT VALUES("+id_pers +", "+ idExp+")");
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                
            }
        } 
        
       //dataPlaque.remove(maPlaque);
       
        //Mettre à jour le tableau de la page d'accueil
        loadDataPlaque();
        
        accueilLaboPane.setVisible(true);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(false);
        resultatExp.setVisible(false);
        
        //Remet les setDisable à false pour la page completer plaque
        resultat.setDisable(false);
        deconnexionIV.setDisable(false);
        buttonAddAR.setDisable(false);
        buttonAddEA.setDisable(false);
        sauvegardePlaque.setDisable(false);
        labelLancePlaque.setVisible(false);
       
    }
    
    /**
     * 
     * @param event 
     */
    public void clickOnLancerPlaque(ActionEvent event){
        lancerPlaque();
    }
    
    public void clickOnLancerPlaquePressed(KeyEvent event){
        if(event.getCode()==ENTER){
            lancerPlaque();
        }
    }
    
    
    
    /**
     * Evenement quand on clique sur sauvegarder dans l'anchor pane lancer plaque
     * (retourne sur page d'accueil)
     * @param event 
     */
    public void clickOnSauvegarder(ActionEvent event){
        accueilLaboPane.setVisible(true);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(false);
        resultatExp.setVisible(false);
        resultat.setDisable(false);
        deconnexionIV.setDisable(false);
        loadDataPlaque();
        setCellTablePlaque();
    }
    
    /**
     * Evenement quand on clique sur le ENTER sur le clavier sur sauvegarder dans l'anchor pane lancer plaque
     * (retourne sur page d'accueil)
     * @param event 
     */
    public void clickOnSauvegarderPressed(KeyEvent event){
        if(event.getCode()==ENTER){
            accueilLaboPane.setVisible(true);
            LancerPlaquePage.setVisible(false); 
            EmplacementPlaquePage.setVisible(false);
            validationPage.setVisible(false);
            resultatExp.setVisible(false);
            resultat.setDisable(false);
            deconnexionIV.setDisable(false);
            loadDataPlaque();
            setCellTablePlaque(); 
        }
    }
    
    /**
     * Evenement quand on clique sur le bouton voir la plaque
     * (affiche le tableau avec les X et Y de tous les puits)
     * @param event 
     */
    public void clickOnButtonPlaque(ActionEvent event){
        accueilLaboPane.setVisible(false);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(true);
        validationPage.setVisible(false);
        resultatExp.setVisible(false);
        setCellEmplacementPlaque();
        loadDataEmplacementPlaque(maPlaque);
        
    }
    
    /**
     * Evenement quand on clique sur entrer avec le clavier sur le bouton voir la plaque
     * (affiche le tableau avec les X et Y de tous les puits)
     * @param event 
     */
    public void clickOnButtonPlaquePressed(KeyEvent event){
        if(event.getCode()==ENTER){
            accueilLaboPane.setVisible(false);
            LancerPlaquePage.setVisible(false); 
            EmplacementPlaquePage.setVisible(true);
            validationPage.setVisible(false);
            resultatExp.setVisible(false);
            setCellEmplacementPlaque();
            loadDataEmplacementPlaque(maPlaque);
        }
    }
    
    /**
     * Ajoute les colonnes pour le tableau emplacement plaque
     */
    private void setCellEmplacementPlaque(){
        dataEmplacementPlaque = FXCollections.observableArrayList();

        colX.setCellValueFactory(new PropertyValueFactory<>("x"));
        colY.setCellValueFactory(new PropertyValueFactory<>("y"));
        colAgentBio.setCellValueFactory(new PropertyValueFactory<>("agent_bio"));
        colQteBio.setCellValueFactory(new PropertyValueFactory<>("qte_agent_bio"));
        colCellule.setCellValueFactory(new PropertyValueFactory<>("cellule"));
        colQteCellule.setCellValueFactory(new PropertyValueFactory<>("qte_cellule"));

    }
    
    /**
     * Initialise le tableau pour l'emplacement des plaques
     * @param plaque 
     */
    public void loadDataEmplacementPlaque( projetbasededonnee.Data.Laborantin plaque){
        dataEmplacementPlaque.clear();
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            rs=stmt.executeQuery("SELECT x,y, qteA, nomA, qteC, nomC FROM PUIT JOIN N_UPLET USING(id_n_uplet) JOIN SOLUTION USING(id_solution) JOIN AGENT_BIOLOGIQUE USING(id_agent_bio) JOIN CELLULE USING(id_cell_cancereuse) where id_plaque = "+ plaque.getId_plaque()+"");
            while(rs.next()){ 
                dataEmplacementPlaque.add(new projetbasededonnee.Data.Laborantin(rs.getInt(1),rs.getInt(2), rs.getString(4), rs.getInt(3), rs.getString(6), rs.getInt(5)));
                
            } 
            tableEmplacementPlaque.setItems(dataEmplacementPlaque);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
         
    }
    
    /**
     * Action Event quand on clique sur le bouton retour quand on affiche 
     * emplacement plaque
     * @param event 
     */
    public void clickOnRetour(ActionEvent event){
        accueilLaboPane.setVisible(false);
        LancerPlaquePage.setVisible(true); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(false);
        resultatExp.setVisible(false);
        
        setInfoPlaque(maPlaque);
        setCellTableARenouveler();
        loadDataExpARenouveler();
        setCellTableEnAttente();
        loadDataExpEnAttente();
        labelLancePlaque.setVisible(false);
    }
    
    /**
     * Action Event quand on clique sur ENTER sur le bouton retour quand on affiche 
     * emplacement plaque
     * @param event 
     */
    public void clickOnRetourKeyPress(KeyEvent event){
        if (event.getCode()==ENTER){
            accueilLaboPane.setVisible(false);
            LancerPlaquePage.setVisible(true); 
            EmplacementPlaquePage.setVisible(false);
            validationPage.setVisible(false);
            resultatExp.setVisible(false);

            setInfoPlaque(maPlaque);
            setCellTableARenouveler();
            loadDataExpARenouveler();
            setCellTableEnAttente();
            loadDataExpEnAttente();
            labelLancePlaque.setVisible(false);
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
     * Setter pour la personne connectée
     * @param personneE 
     */
    public void setPersonne(Personne personneE){
        personne=personneE; 
    }
}
