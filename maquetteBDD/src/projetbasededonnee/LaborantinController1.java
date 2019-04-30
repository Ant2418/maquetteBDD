/*
 * Controller du laborantin avec connexion
 */
package projetbasededonnee;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 * Controller de la page FXML Laborantin
 * @author Antoine et Ludivine
 * @version 24/04/2019
 */
public class LaborantinController1 implements Initializable {

    @FXML    private Button buttonDeconnexion;
    @FXML    private Button buttonHome;
    @FXML    private Button buttonResultat;
    @FXML    private Button buttonInfo;
    @FXML    private Pane paneHome;
    @FXML    private Pane paneDeco;
    @FXML    private Pane paneResultat;
    @FXML    private Pane paneInfo;
    @FXML    private AnchorPane accueilLaboPane;
    @FXML    private AnchorPane LancerPlaquePage;
    @FXML    private AnchorPane EmplacementPlaquePage; 
    @FXML    private AnchorPane validationPage; 
    @FXML    private AnchorPane resultatExp;
    
    //TableView pour l'accueil de laborantin
    @FXML    private TableView<projetbasededonnee.Data.Laborantin> tableAccueilLabo;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNumPlaque;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colTypePlaque;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colPuitsDispo;
    @FXML    private ComboBox cbTypePlaque;

    @FXML    private ImageView validerResultat;
    
    //TableView pour le tableau de resultats
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

    //TableView pour l'expérience à renouveler
    @FXML    private TableView<projetbasededonnee.Data.Laborantin> tableExpARenouveler;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNumExp;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNomExp;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNbReplicat;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colTypeAna;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colTypeExp;
    @FXML    private TableColumn<projetbasededonnee.Data.Laborantin, ?> colNbPuits;
    
    //TableView pour les expériences en attente
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
    @FXML    private Label labelAjoutExpPlaque;
    @FXML    private Label labelAjoutExpAttentLabel; 
    @FXML    private Button sauvegardePlaque; 
    @FXML    private Button buttonPlaque;
    @FXML    private Label labelLancePlaque; 
    @FXML    private Button buttonLancerPlaque;
    
    //Variables locales
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
    
    //TableView pour le tableau resultat des expériences avec l'état
    @FXML    private TableView<projetbasededonnee.Data.AccueilChercheur> tableResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colIdExpResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colNomResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colEtatResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colTypeExpResult;
    @FXML    private TableColumn<projetbasededonnee.Data.AccueilChercheur, ?> colTypeAnaResult;
    
    //Label pour l'anchorpane validerResultat
    @FXML    private Label LabelValidExpResult;
    
    //liste observable
    private ObservableList<projetbasededonnee.Data.Laborantin> dataPlaque;
    private ObservableList<projetbasededonnee.Data.Laborantin> dataExpARenouveler;
    private ObservableList<projetbasededonnee.Data.Laborantin> dataExpEnAttente;
    private ObservableList<projetbasededonnee.Data.Laborantin> dataEmplacementPlaque;
    
//L'observable liste pour le tableau des expériences
    private ObservableList<projetbasededonnee.Data.AccueilChercheur> dataExpResult;
    
    //ArrayList utilisable en interne
    private ArrayList<Integer> listeIdPlaque;
    private ArrayList<Integer> listePlaqueLancer;
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
    
     
    
    /**
     * Initialisation de la classe controlleur pour le laborantin 
     *  - Page d'accueil du laborantin est visible 
     *  - Page ajouter une plaque, ou la remplir, l'emplacement de la plaque, la validation des résultats sont invisibles 
     *  - instantiation de l'observableList dataPlaque 
     *  - instantiation d'une ArrayList dataExpResult 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accueilLaboPane.setVisible(true);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(false);
        validationPage.setVisible(false);
        resultatExp.setVisible(false);
        paneHome.setStyle("-fx-background-color:#A1102A"); 
        dataPlaque = FXCollections.observableArrayList();
        dataExpResult = FXCollections.observableArrayList();
        listeIdPlaque= new ArrayList();
        listePlaqueLancer= new ArrayList();
        setCellTablePlaque();
        //buttonAjoutPlaque.setGraphic(new ImageView(new Image(getClass().getResource("checked.png").toExternalForm(), 20, 20, true, true)));
        
    }    

    /**
     * Permet de coloriser le bouton et le pane du bouton DECONNEXION 
     *  - <code>#c1b5a9</code> : couleur du fond qui permet de ressortir le bouton
     *  - <code>#A1102A</code> : couleur pourpre 
     */
    public void onMouseEnteredDeconnexion (){
        buttonDeconnexion.setStyle("-fx-background-color:#c1b5a9; -fx-background-radius:0");
        paneDeco.setStyle("-fx-background-color:#A1102A");
    }
    
    /**
     * Permet de reinitialiser la couleur du bouton et du pane du bouton DECONNEXION
     * <code>#d7d7d7</code> : couleur du fond dans les tonds clairs 
     */
    public void onMouseExitedDeconnexion (){
        buttonDeconnexion.setStyle("-fx-background-color:#d7d7d7; -fx-background-radius:0");
        paneDeco.setStyle("-fx-background-color:#d7d7d7");
    }
    
    /**
     * Permet de coloriser le bouton et le pane du bouton HOME 
     * <code>#c1b5a9</code> : couleur du fond qui permet de ressortir le bouton 
     * <code>#A1102A</code> : couleur pourpre 
     */
    public void onMouseEnteredHome (){
        buttonHome.setStyle("-fx-background-color:#c1b5a9; -fx-background-radius:0");
        paneHome.setStyle("-fx-background-color:#A1102A");
    }
    
    /**
     * Permet de reinitialiser la couleur du bouton et du pane du bouton HOME 
     * si pageHomeChercheur est n'est pas visible on change la couleur paneHome 
     * <code>#d7d7d7</code> : couleur du fond dans les tonds clairs 
     */
    public void onMouseExitedHome (){
        buttonHome.setStyle("-fx-background-color:#d7d7d7; -fx-background-radius:0");
        if (accueilLaboPane.isVisible() == true || EmplacementPlaquePage.isVisible() == true || LancerPlaquePage.isVisible() == true){
            //nothing
        }else{
            paneHome.setStyle("-fx-background-color:#d7d7d7");
        }
        
    }
    /**
     * Permet de coloriser le bouton et le pane du bouton NEW EXP 
     * <code>#c1b5a9</code> : couleur du fond qui permet de ressortir le bouton 
     * <code>#A1102A</code> : couleur pourpre 
     */
    public void onMouseEnteredResultat (){
        buttonResultat.setStyle("-fx-background-color:#c1b5a9; -fx-background-radius:0");
        paneResultat.setStyle("-fx-background-color:#A1102A");
    }
    
    /**
     * Permet de reinitialiser la couleur du bouton et du pane du bouton NEW EXP 
     * Si pageAjoutExp ou pageAddUplet ne sont pas visible alors on peut changer
     * la couleur de paneNewExp 
     * <code>#d7d7d7</code> : couleur du fond dans les tonds clairs 
     */
    public void onMouseExitedResultat (){
        buttonResultat.setStyle("-fx-background-color:#d7d7d7; -fx-background-radius:0");
        if (validationPage.isVisible() == true || resultatExp.isVisible() == true){
            // nothing
        }else{
            paneResultat.setStyle("-fx-background-color:#d7d7d7");
        }
    }
    
    /**
     * Permet de coloriser le bouton buttonInfo et le pane du bouton paneInfo 
     *  - <code>#c1b5a9</code> : couleur du fond qui permet de ressortir le bouton
     *  - <code>#A1102A</code> : couleur pourpre 
     */
    public void onMouseEnteredInfo (){
        buttonInfo.setStyle("-fx-background-color:#c1b5a9; -fx-background-radius:0");
        paneInfo.setStyle("-fx-background-color:#A1102A");
    }
    
    /**
     * Permet de reinitialiser la couleur du bouton buttonInfo et du pane du bouton paneInfo
     * <code>#d7d7d7</code> : couleur du fond dans les tonds clairs 
     */
    public void onMouseExitedInfo (){
        buttonInfo.setStyle("-fx-background-color:#d7d7d7; -fx-background-radius:0");
        paneInfo.setStyle("-fx-background-color:#d7d7d7");
    }
    
    /**
     * Methode qui permet d'ouvrir le pdf de documentation
     * @param event on mouse clicked sur buttonInfo
     * @throws IOException 
     */
    public void infoEvent(MouseEvent event) throws IOException {
        //Desktop.getDesktop().open(new File ("D:\\Doc\\NetBeansProjects\\IHMSANSBDD\\IHMSANSBDD\\IHMSANSBDD\\src\\projetbasededonnee\\ReadMeChercheur.pdf"));
        InputStream pdfDansLArchive = getClass().getClassLoader().getResourceAsStream("projetbasededonnee/ReadMeLaborantin.pdf");
        try {
            File pdfCree = new File("ReadMeLaborantin.pdf");
            // Extraction du PDF qui se situe dans l'archive
            FileOutputStream fos = new FileOutputStream(pdfCree);
            while (pdfDansLArchive.available() > 0) {
                  fos.write(pdfDansLArchive.read());
            }   // while (pdfInJar.available() > 0)
            fos.close();
            // Ouverture du PDF
            Desktop.getDesktop().open(pdfCree);
        }   // try
        catch (IOException e) {
            System.out.println("erreur : " + e);
        }   // catch (IOException e)
    }
    
    /**
     *  Initialisation des colonnes pour le tableau de la plaque accueil labo
     * Creation du bouton editer et des autres colonnes 
     * Les colonnes sont : 
     * <ul>
     *  <li> identifiant de la plaque </li>
     *  <li> Type de plaque </li>
     *  <li> Puits disponibles </li>
     * </ul>
     */
    private void setCellTablePlaque(){
  
        tableAccueilLabo.getColumns().clear();
        
        colNumPlaque.setCellValueFactory(new PropertyValueFactory<>("idPlaque"));
        
        colTypePlaque.setCellValueFactory(new PropertyValueFactory<>("typePlaque"));
        
        colPuitsDispo.setCellValueFactory(new PropertyValueFactory<>("puitsDispo"));
       
        
        //Set Edit button column
        TableColumn<projetbasededonnee.Data.Laborantin, Void> editerColonne = new TableColumn("Editer");
        
        Callback<TableColumn<projetbasededonnee.Data.Laborantin, Void>, TableCell<projetbasededonnee.Data.Laborantin, Void>> cellFactory = (final TableColumn<projetbasededonnee.Data.Laborantin, Void> param) -> {
            final TableCell<projetbasededonnee.Data.Laborantin, Void> cell = new TableCell<projetbasededonnee.Data.Laborantin, Void>() {
                private final Button btn = new Button("Completer");
                {
                    btn.setOnAction((ActionEvent event) -> {
                        
                        maPlaque = getTableView().getItems().get(getIndex());
                        //Affiche le panel lancerPlaquePage
                        accueilLaboPane.setVisible(false);
                        LancerPlaquePage.setVisible(true); 
                        EmplacementPlaquePage.setVisible(false);
                        validationPage.setVisible(false);
                        resultatExp.setVisible(false);
                        buttonPlaque.setDisable(false);
                        
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
                        labelAjoutExpAttentLabel.setVisible(false);
                        labelAjoutExpPlaque.setVisible(false);
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
     * 
     */
    public void loadDataPlaque(){
        dataPlaque.clear();
        listeIdPlaque.clear();
        
        try{
            con = connex.getCon();
            stmt = con.createStatement();
            
            //Selectionne les id_plaque qui n'ont pas d'expérience en cours
            rs=stmt.executeQuery("SELECT id_plaque FROM PLAQUE where ESTTERMINE = "+0+"");

            while(rs.next()){
                id_plaque=rs.getInt(1); 
                    listeIdPlaque.add(id_plaque);
            }  
          
        } catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }

        //Pour chaque plaque de la liste d'id plaque
        for (Integer id_plaque : listeIdPlaque) {

            try{                
                try{
                    //Selectionne le type de plaque
                    rs=stmt.executeQuery("SELECT type_plaque FROM plaque WHERE id_plaque = "+ id_plaque +"");
                while(rs.next()){
                    type_plaque=rs.getString(1);
                }
               
                            
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                //Compte le nombre de puit pris
                rs=stmt.executeQuery("SELECT count(id_puit) FROM puit WHERE id_plaque = "+ id_plaque +"");
                while(rs.next()){
                    nb_puit=rs.getInt(1);
            
                    if ("96puits".equals(type_plaque)){
                        if (nb_puit < 96) {
                            nb_puit=96-nb_puit; //nbre de puits dispo
                            if(nb_puit < 96){
                            dataPlaque.add(new projetbasededonnee.Data.Laborantin(id_plaque, type_plaque, nb_puit));
                            //tableAccueilLabo.setItems(dataPlaque);
                            }
                        }
                    }
                    else if ("384puits".equals(type_plaque)){
                        if (nb_puit < 384) {
                            nb_puit=384-nb_puit;
                            if(nb_puit<384){
                            dataPlaque.add(new projetbasededonnee.Data.Laborantin(id_plaque, type_plaque, nb_puit));
                            
                            }
                        }
                    }
                }
                tableAccueilLabo.setItems(dataPlaque);
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
        
        colIdExpResult.setCellValueFactory(new PropertyValueFactory<>("idExp"));
        
        colNomResult.setCellValueFactory(new PropertyValueFactory<>("nomExp"));
        
        colEtatResult.setCellValueFactory(new PropertyValueFactory<>("etatExp"));
       
        colTypeExpResult.setCellValueFactory(new PropertyValueFactory<>("typeExp"));
        
        colTypeAnaResult.setCellValueFactory(new PropertyValueFactory<>("typeAnalyse"));
        
        //Set Edit button column
        TableColumn<projetbasededonnee.Data.AccueilChercheur, Void> detailColonne = new TableColumn("Details");
        
        Callback<TableColumn<projetbasededonnee.Data.AccueilChercheur, Void>, TableCell<projetbasededonnee.Data.AccueilChercheur, Void>> cellFactory = (final TableColumn<projetbasededonnee.Data.AccueilChercheur, Void> param) -> {
            final TableCell<projetbasededonnee.Data.AccueilChercheur, Void> cell = new TableCell<projetbasededonnee.Data.AccueilChercheur, Void>() {
                
                private final Button btnDet = new Button("Résultats");
                {
                    
                    btnDet.setOnAction((ActionEvent event) -> {
                        
                        projetbasededonnee.Data.AccueilChercheur monExp = getTableView().getItems().get(getIndex());
                        LabelValidExpResult.setText("Résultat de l'expérience numéro " + monExp.getIdExp());
                        accueilLaboPane.setVisible(false);
                        LancerPlaquePage.setVisible(false); 
                        EmplacementPlaquePage.setVisible(false);
                        validationPage.setVisible(true);
                        resultatExp.setVisible(false);
                        
                        buttonHome.setDisable(true);
                        buttonResultat.setDisable(true);
                        buttonDeconnexion.setDisable(true);
                
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
                        if ("Terminee".equals(experience.getEtatExp())) {
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

        colNumExp.setCellValueFactory(new PropertyValueFactory<>("idExp"));
        colNomExp.setCellValueFactory(new PropertyValueFactory<>("nomExp"));
        colNbReplicat.setCellValueFactory(new PropertyValueFactory<>("nbUplet"));
        colTypeAna.setCellValueFactory(new PropertyValueFactory<>("typeAna"));
        colTypeExp.setCellValueFactory(new PropertyValueFactory<>("typeExp"));
        colNbPuits.setCellValueFactory(new PropertyValueFactory<>("nbPuits"));
    }
    
    /**
     * Methode qui recupere les données pour les mettre dans 
     * le tableau d'experiences a renouveler
     */
    public void loadDataExpARenouveler(){
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
            for (Integer idExpValid : listeIdExp){
                listeIdUplet.clear(); 
                listeIdUpletValid.clear(); 
           
                rs1=stmt1.executeQuery("select id_n_uplet from N_UPLET WHERE RENOUVELER=0 and TERMINEE=0 and ID_EXPERIENCE = " + idExpValid+"");
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
                        nomExp=rs1.getString(1);
                        typeAna=rs1.getString(2);
                        typeExp=rs1.getString(3);
                        nbPuits=rs1.getInt(4);
                    }
                    Integer nbrePuitUplet = listeIdUpletValid.size()*nbPuits;
                    if (nbrePuitUplet <= maPlaque.getPuitsDispo()){
                        dataExpARenouveler.add(new projetbasededonnee.Data.Laborantin(idExpValid, nomExp, listeIdUpletValid.size(), typeAna, typeExp, nbrePuitUplet));
                        tableExpARenouveler.setItems(dataExpARenouveler);
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

        colNumExp2.setCellValueFactory(new PropertyValueFactory<>("idExp"));
        colNomExp2.setCellValueFactory(new PropertyValueFactory<>("nomExp"));
        colNbReplicat2.setCellValueFactory(new PropertyValueFactory<>("nbUplet"));
        colTypeAna2.setCellValueFactory(new PropertyValueFactory<>("typeAna"));
        colTypeExp2.setCellValueFactory(new PropertyValueFactory<>("typeExp"));
        colNbPuits2.setCellValueFactory(new PropertyValueFactory<>("nbPuits"));
    }
    
    /**
     * Methode qui recupere les donnees pour les mettre dans le tableau d'experiences en attente
     */
    public void loadDataExpEnAttente(){
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
                    nomExp=rs1.getString(1);
                    typeAna=rs1.getString(2);
                    typeExp=rs1.getString(3);
                    nbPuits=rs1.getInt(4);
                }
                Integer nbrePuitUplet = listeIdUpletValid.size()*nbPuits;
                if (nbrePuitUplet <= maPlaque.getPuitsDispo()){
                    dataExpEnAttente.add(new projetbasededonnee.Data.Laborantin(idExpValid, nomExp, listeIdUpletValid.size(), typeAna, typeExp, nbrePuitUplet));
                    tableExpEnAttente.setItems(dataExpEnAttente);
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
            //Selectionne les type de plaques
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
        //CCO.setListePlaqueLancer(listePlaqueLancer);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ajoutSceneConn);
        window.show();
    }

    /**
     * Affichage du panel homePageLab,
     * l'accueil du laborantin 
     * accueilLaboPane est a true
     * 
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
        buttonHome.setDisable(false);
        buttonResultat.setDisable(false);
        buttonDeconnexion.setDisable(false);
        paneResultat.setStyle("-fx-background-color:#d7d7d7");
    } 
    
    /**
     * Affichage du panel homePageLab, l'accueil du laborantin
     * Ajout d'une nouvelle plaque
     * @param event
     * @throws IOException 
     */
    @FXML
    public void  CompletePlaque(MouseEvent event) throws IOException {
        erreurAjoutPlaque.setVisible(false);
        if (cbTypePlaque.getSelectionModel().getSelectedItem()!= null) {
            
            try{
                stmt = con.createStatement();
                //INSERTION d'une plaque
                rs= stmt.executeQuery("INSERT INTO PLAQUE (id_plaque, type_plaque, EstRefuse, ESTTERMINE)VALUES("+ 1 + ", '"+ cbTypePlaque.getSelectionModel().getSelectedItem()+ "', "+ 0 +", "+ 0 +")");

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
                    
                    maPlaque = new projetbasededonnee.Data.Laborantin(id_plaque,(String) cbTypePlaque.getSelectionModel().getSelectedItem(),96);
                }
                else if ("384puits".equals((String) cbTypePlaque.getSelectionModel().getSelectedItem()))
                {   
                    //maPlaque.setPuits_dispo(384);
                    maPlaque = new projetbasededonnee.Data.Laborantin(id_plaque,(String) cbTypePlaque.getSelectionModel().getSelectedItem(),384);
                }
                //Creer une nouvelle plaque
               //labelInfoPlaquePuits.setText("Plaque n° " +maPlaque.getId_plaque()+ ". Il reste "+maPlaque.getPuits_dispo()+" puits dans la plaque");
                setInfoPlaque(maPlaque);
                listeIdExp = new ArrayList();
                listeIdExpValid = new ArrayList(); 
                listeIdUpletValid = new ArrayList();
                listeIdUplet = new ArrayList(); 
                listeIdUplet1 = new ArrayList();
                
                //Ajoute la plaque à la liste de la plaque
                
                dataPlaque.add(maPlaque);
                
                //Mise a jour du tableau a renouveler
                setCellTableARenouveler();
                loadDataExpARenouveler();
                
                
                listeIdExpEA = new ArrayList();
                listeIdExpValidA = new ArrayList();
                
                //Mise a jour du tableau en attente
                setCellTableEnAttente();
                loadDataExpEnAttente();
                
                labelAjoutExpAttentLabel.setVisible(false);
                labelAjoutExpPlaque.setVisible(false);

                buttonAddAR.setDisable(false);
                buttonAddEA.setDisable(false);
                buttonHome.setDisable(true);
                buttonResultat.setDisable(true);
                buttonDeconnexion.setDisable(true);
                sauvegardePlaque.setDisable(true);
                buttonPlaque.setDisable(true);
                buttonLancerPlaque.setDisable(true); 
                
                ComboTypePlaque();
                

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
        erreurAjoutPlaque.setVisible(false);
        if (event.getCode() == ENTER) {
            if (cbTypePlaque.getSelectionModel().getSelectedItem()!= null) {

                try{
                    stmt = con.createStatement();
                    rs= stmt.executeQuery("INSERT INTO PLAQUE (id_plaque, type_plaque, EstRefuse, ESTTERMINE)VALUES("+ 1 + ", '"+ cbTypePlaque.getSelectionModel().getSelectedItem()+ "', "+ 0 +", "+0+")");

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
                    }
                    else if ("384puits".equals((String) cbTypePlaque.getSelectionModel().getSelectedItem()))
                    {   
                        //maPlaque.setPuits_dispo(384);
                        maPlaque = new projetbasededonnee.Data.Laborantin(id_plaque,(String) cbTypePlaque.getSelectionModel().getSelectedItem(),384);
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
                    
                    labelAjoutExpAttentLabel.setVisible(false);
                    labelAjoutExpPlaque.setVisible(false);

                    buttonAddAR.setDisable(false);
                    buttonAddEA.setDisable(false);
                    buttonHome.setDisable(true);
                    buttonResultat.setDisable(true);
                    buttonDeconnexion.setDisable(true);
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
            rs= stmt.executeQuery("SELECT count(*) FROM PUIT WHERE ID_PLAQUE= "+ maPlaque.getIdPlaque() +"");
            while(rs.next()){
                nb_puits_plaque=rs.getInt(1); 
            }
            if ("96puits".equals(maPlaque.getTypePlaque())){
                maPlaque.setPuitsDispo(96-nb_puits_plaque);    
            }
            else
            {
                maPlaque.setPuitsDispo(384-nb_puits_plaque); 
            }
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        labelInfoPlaquePuits.setText("Plaque n° " +maPlaque.getIdPlaque()+ ". Il reste "+maPlaque.getPuitsDispo()+" puits dans la plaque");
        if(maPlaque.getPuitsDispo()==0){
            buttonHome.setDisable(true);
            buttonResultat.setDisable(true);
            buttonDeconnexion.setDisable(true);
            buttonAddAR.setDisable(true);
            buttonAddEA.setDisable(true);
            sauvegardePlaque.setDisable(true);
            labelLancePlaque.setVisible(true);
            buttonLancerPlaque.setDisable(false); 
        }
        else{
            buttonHome.setDisable(true);
            buttonResultat.setDisable(true);
            buttonDeconnexion.setDisable(true);
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
        
        buttonHome.setDisable(false);
        buttonResultat.setDisable(false);
        buttonDeconnexion.setDisable(false);
        paneHome.setStyle("-fx-background-color:#d7d7d7");
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
            labelAjoutExpPlaque.setText("Expérience bien ajoutée");
            labelAjoutExpPlaque.setVisible(true);
            labelAjoutExpAttentLabel.setVisible(false);
            buttonHome.setDisable(true);
            buttonPlaque.setDisable(false);
            
            setCellTableEnAttente();
            loadDataExpEnAttente();
          
        }
        else{
            labelAjoutExpPlaque.setText("Veuillez selectionner une ligne");
            labelAjoutExpPlaque.setVisible(true);
            labelAjoutExpAttentLabel.setVisible(false);
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
            labelAjoutExpPlaque.setText("Expérience bien ajoutée");
            labelAjoutExpPlaque.setVisible(true);
            labelAjoutExpAttentLabel.setVisible(false);
            buttonHome.setDisable(true);
            buttonPlaque.setDisable(false);
            
            setCellTableEnAttente();
            loadDataExpEnAttente();

            }
            else{
                labelAjoutExpPlaque.setText("Veuillez selectionner une ligne");
                labelAjoutExpPlaque.setVisible(true);
                labelAjoutExpAttentLabel.setVisible(false);
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
            labelAjoutExpAttentLabel.setText("Expérience bien ajoutée");
            labelAjoutExpAttentLabel.setVisible(true);
            labelAjoutExpPlaque.setVisible(false);
            buttonPlaque.setDisable(false);
            
            setCellTableARenouveler();
            loadDataExpARenouveler();
        }
        else{
            labelAjoutExpAttentLabel.setText("Veuillez selectionner une ligne");
            labelAjoutExpAttentLabel.setVisible(true);
            labelAjoutExpPlaque.setVisible(false);
        }
        
    }
    
    /**
     * Evenement quand on clique sur le clavier avec enter quand on clique
     * sur l'icone + et que l'on a selectionné une ligne du tableau des expériences
     * en attente
     * @param event 
     */
    public void addUpletPlaqueEnAttKeyPress(KeyEvent event){
        if(event.getCode()==ENTER){
            if(tableExpEnAttente.getSelectionModel().getSelectedItem() != null){
                AjoutXYPlaque(tableExpEnAttente.getSelectionModel().getSelectedItem(),maPlaque);
                setCellTableEnAttente();
                loadDataExpEnAttente();
                setInfoPlaque(maPlaque);
                labelAjoutExpAttentLabel.setText("Expérience bien ajoutée");
                labelAjoutExpAttentLabel.setVisible(true);
                labelAjoutExpPlaque.setVisible(false);
                buttonPlaque.setDisable(false);
                
                setCellTableARenouveler();
                loadDataExpARenouveler();
            }
            else{
                labelAjoutExpAttentLabel.setText("Veuillez selectionner une ligne");
                labelAjoutExpAttentLabel.setVisible(true);
                labelAjoutExpPlaque.setVisible(false);
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
        rs4 = stmt4.executeQuery("SELECT id_n_uplet FROM N_UPLET WHERE id_experience = " + experience.getIdExp() + " and terminee = "+ 0 + "");
        while (rs4.next()) { 
            
            Integer id_uplet= rs4.getInt(1);
            listeIdUplet.add(id_uplet); 
        }
        rs4.close();
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
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
    }
 
        if ("96puits".equals(plaque.getTypePlaque())){
            Integer nbrePuit_Uplet= experience.getNbPuits()/experience.getNbUplet();
            Integer puits_dispo=plaque.getPuitsDispo(); 
            Integer puits_exp=experience.getNbPuits(); 

            if (puits_dispo >=  puits_exp){
                if (puits_dispo == 96){
                    int X=1;
                    int Y=1;
                    for (Integer idUplet : listeIdUpletValid) {
                        for (int i=1; i <= nbrePuit_Uplet; i++){
                            try{
                                rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getIdPlaque() +", "+ idUplet +", "+X+", "+Y+")");   
                                maPlaque.setPuitsDispo(maPlaque.getPuitsDispo()-1);    
                                if (Y == 8) {
                                    X=X+1;
                                    Y=1;
                                }
                                else
                                {
                                    Y=Y+1;
                                }
                                rs.close();
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
                       
                        rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getIdPlaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                        while (rs.next()) { 
                            X = rs.getInt(1);
                            Y = rs.getInt(2);
                        }
                        rs.close();
                    }catch (SQLException e) {
                        Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                    }
                    
                    for (Integer idUplet : listeIdUpletValid) {
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
                          
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getIdPlaque() +", "+ idUplet +", "+X+", "+Y+")");
                            maPlaque.setPuitsDispo(maPlaque.getPuitsDispo()-1);  
                            rs.close();
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
                int X=0; 
                int Y=0; 
                try{
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getIdPlaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                    while (rs.next()) { 
                        X = rs.getInt(1);
                        Y = rs.getInt(2);
                    }
                    rs.close();
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                while( nb <= nbreUpletAInserer){
                    Integer idUplet=listeIdUpletValid.get(nb);
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
                        rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getIdPlaque() +", "+ idUplet +", "+X+", "+Y+")");
                        maPlaque.setPuitsDispo(maPlaque.getPuitsDispo()-1); 
                        rs.close();
                        }catch (SQLException e) {
                            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } 
                    nb=nb+1;
                }
            }
            }
        
        
        else if ("384puits".equals(plaque.getTypePlaque())){
            
            Integer nbrePuit_Uplet= experience.getNbPuits()/experience.getNbUplet();
            Integer puits_dispo=plaque.getPuitsDispo(); 
            Integer puits_exp=experience.getNbPuits(); 
            if (puits_dispo >=  puits_exp){
                if (puits_dispo == 384){
                    int X=1;
                    int Y=1;

                   
                    for (Integer idUplet : listeIdUpletValid) {
                        for (int i=1; i <= nbrePuit_Uplet; i++){
                            try{
                            stmt = con.createStatement();
                            rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getIdPlaque() +", "+ idUplet +", "+X+", "+Y+")");
                            maPlaque.setPuitsDispo(maPlaque.getPuitsDispo()-1);   
                            if (Y == 16) {
                                X=X+1;
                                Y=1;
                            }
                            else
                            {
                                Y=Y+1;
                            }
                            rs.close();
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
                      
                        rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getIdPlaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                        while (rs.next()) { 
                            X = rs.getInt(1);
                            Y = rs.getInt(2);
                        }
                        rs.close();
                    }catch (SQLException e) {
                        Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                    }
                    
                    for (Integer idUplet : listeIdUpletValid) {
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
                            
                                rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getIdPlaque() +", "+ idUplet +", "+X+", "+Y+")");
                                maPlaque.setPuitsDispo(maPlaque.getPuitsDispo()-1);
                                rs.close();
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
                int X=0; 
                int Y=0; 
                try{
                    
                    rs = stmt.executeQuery("SELECT X, Y FROM (SELECT id_puit, x, y from PUIT where id_plaque = "+ plaque.getIdPlaque()  +" ORDER BY ID_PUIT DESC) WHERE ROWNUM = "+1+"");
                    while (rs.next()) { 
                        X = rs.getInt(1);
                        Y = rs.getInt(2);
                    }
                    rs.close();
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                while( nb <= nbreUpletAInserer){
                    Integer idUplet=listeIdUpletValid.get(nb);
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
                        rs = stmt.executeQuery("INSERT INTO PUIT (id_puit, id_plaque, id_n_uplet, x, y) VALUES (" + 1 + ", "+ plaque.getIdPlaque() +", "+ idUplet +", "+X+", "+Y+")");
                        maPlaque.setPuitsDispo(maPlaque.getPuitsDispo()-1);
                        rs.close();
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
     * permet de lancer une plaque
     * Modifie l'etat des expériences présent sur la plaque a 'en cours'
     * Ajout la date de début de l'expérience
     * Ajoute le nom du laborantin en charge de l'expérience
     * 
     */
    public void lancerPlaque(){
        con = connex.getCon();
        projetbasededonnee.Data.CurrentDate Date = new projetbasededonnee.Data.CurrentDate();

        try{
            stmt = con.createStatement();
            //Ajoute à une liste l'ensemble des id_experiences contenant des puits sur la plaque
            rs = stmt.executeQuery("select distinct id_experience from EXPERIENCE join N_UPLET using (id_experience) join PUIT using (id_n_uplet) where id_plaque = "+ maPlaque.getIdPlaque());
            while (rs.next()) { 
                listExp.add(rs.getInt(1));
            }
            rs.close();
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
       
        try{
            stmt2 = con.createStatement();
            //Trouve l'id de la personne connectée
            rs2 = stmt2.executeQuery("SELECT ID_PERSONNE FROM PERSONNE WHERE EMAIL = '" + personne.getEmail() + "'");
            while (rs2.next()) { 
                id_pers = rs2.getInt(1);
            }
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }
        //Pour chaque expérience dans la liste
        for (int i=0; i < listExp.size(); i++) {
            Integer nbFait = null;
            listNUplet.clear();
            listResNbPuit.clear();
            // recupere tous les n_uplet de l'experience
            try{
                rs = stmt.executeQuery("select id_n_uplet from N_UPLET where id_experience = "+ listExp.get(i));
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
            // si la liste contient pas 0 alors passage de l'experience a 'en cours'
            if (listResNbPuit.contains(0)){
                // nothing
            }else{
                try{
                    rs = stmt.executeQuery("update EXPERIENCE set etat_exp = 'En Cours', HD_TRANSMISSION_CHERCHEUR= "+null+",  horodatage_deb = '" + Date.getdateFormat().format(Date.getDate())+"' where id_experience = "+ listExp.get(i));
                    rs.close();
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                
                try{
                    
                    rs=stmt.executeQuery("SELECT count(*) FROM FAIT WHERE ID_PERSONNE= "+id_pers+" and ID_EXPERIENCE = "+listExp.get(i)+"");
                    rs.next();
                        nbFait = rs.getInt(1);
                }catch (SQLException e) {
                    Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                }
                if (nbFait==0){
                    try{
                        rs=stmt.executeQuery("INSERT INTO FAIT VALUES("+id_pers +", "+ listExp.get(i)+")");
                        rs.close();
                    }catch (SQLException e) {
                        Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                
            }
        } 
        //Ajoute l'id de la plaque dans la liste poubelle
//        listePlaqueLancer.add(maPlaque.getIdPlaque());
        //La plaque est terminee
        try{
            rs = stmt.executeQuery("update PLAQUE set ESTTERMINE = "+1+" where id_plaque = "+ maPlaque.getIdPlaque() +"");
            rs.close();
        }catch (SQLException e) {
            Logger.getLogger(LaborantinController1.class.getName()).log(Level.SEVERE, null, e);
        }

       //Mettre à jour le tableau de la page d'accueil
        loadDataPlaque();
        
        accueilLaboPane.setVisible(false);
        LancerPlaquePage.setVisible(false); 
        EmplacementPlaquePage.setVisible(true);
        validationPage.setVisible(false);
        resultatExp.setVisible(false);
        
        setCellEmplacementPlaque();
        loadDataEmplacementPlaque(maPlaque);
        
        
        //Remet les setDisable à false pour la page completer plaque
        buttonResultat.setDisable(true);
        buttonDeconnexion.setDisable(true);
        buttonAddAR.setDisable(false);
        buttonAddEA.setDisable(false);
        sauvegardePlaque.setDisable(false);
        labelLancePlaque.setVisible(false);
       
    }
    
    /**
     * Quand on clique sur le button lancerPlaque
     *
     * @param event 
     */
    public void clickOnLancerPlaque(ActionEvent event){
        lancerPlaque();
    }
    
    
    /**
     * Quand on clique sur lancer la plaque 
     * @param event 
     */
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
        buttonResultat.setDisable(false);
        buttonDeconnexion.setDisable(false);
        buttonHome.setDisable(false);
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
            buttonResultat.setDisable(false);
            buttonDeconnexion.setDisable(false);
            buttonHome.setDisable(false);
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
        colAgentBio.setCellValueFactory(new PropertyValueFactory<>("agentBio"));
        colQteBio.setCellValueFactory(new PropertyValueFactory<>("qteAgentBio"));
        colCellule.setCellValueFactory(new PropertyValueFactory<>("cellule"));
        colQteCellule.setCellValueFactory(new PropertyValueFactory<>("qteCellule"));

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
            
            rs=stmt.executeQuery("SELECT x,y, qteA, nomA, qteC, nomC FROM PUIT JOIN N_UPLET USING(id_n_uplet) JOIN SOLUTION USING(id_solution) JOIN AGENT_BIOLOGIQUE USING(id_agent_bio) JOIN CELLULE USING(id_cell_cancereuse) where id_plaque = "+ plaque.getIdPlaque()+"");
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
        if (dataPlaque.contains(maPlaque)){
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
        else{
            
            accueilLaboPane.setVisible(true);
            LancerPlaquePage.setVisible(false); 
            EmplacementPlaquePage.setVisible(false);
            validationPage.setVisible(false);
            resultatExp.setVisible(false);
            buttonHome.setDisable(false);
            buttonResultat.setDisable(false);
            buttonDeconnexion.setDisable(false);
            loadDataPlaque();
            setCellTablePlaque();
        }
    }
    
    /**
     * Action Event quand on clique sur ENTER sur le bouton retour quand on affiche 
     * emplacement plaque
     * @param event 
     */
    public void clickOnRetourKeyPress(KeyEvent event){
        if (event.getCode()==ENTER){
            if (dataPlaque.contains(maPlaque)){
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
            else{

                accueilLaboPane.setVisible(true);
                LancerPlaquePage.setVisible(false); 
                EmplacementPlaquePage.setVisible(false);
                validationPage.setVisible(false);
                resultatExp.setVisible(false);
                buttonHome.setDisable(false);
                buttonResultat.setDisable(false);
                buttonDeconnexion.setDisable(false);
                loadDataPlaque();
                setCellTablePlaque();
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
     * Setter pour la personne connectée
     * @param personneE 
     */
    public void setPersonne(Personne personneE){
        personne=personneE; 
    }

    /**
     * Setter pour la liste de plaque
     * @param listePlaqueLancer 
     */
    public void setListePlaqueLancer(ArrayList<Integer> listePlaqueLancer) {
        this.listePlaqueLancer = listePlaqueLancer;
    }
    
    
}
