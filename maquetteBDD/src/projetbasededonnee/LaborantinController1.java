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

    @FXML
    private VBox menuVBox;
    @FXML
    private ImageView deconnexionIV;
    @FXML
    private ImageView home;
    @FXML
    private ImageView experience;
    @FXML
    private ImageView resultat;
    @FXML
    private AnchorPane accueilLaboPane;
    @FXML
    private AnchorPane LancerPlaquePage;
    @FXML
    private AnchorPane EmplacementPlaquePage; 
    @FXML
    private AnchorPane validationPage; 
    @FXML
    private VBox titleAcceuil1;
    @FXML
    private TableView<projetbasededonnee.Data.Laborantin> tableAccueilLabo;
    @FXML
    private TableColumn<?, ?> colNumPlaque;
    @FXML
    private TableColumn<?, ?> colTypePlaque;
    @FXML
    private TableColumn<?, ?> colPuitsDispo;
    @FXML
    private ComboBox cbTypePlaque;
    @FXML
    private ImageView validerIV;
    @FXML
    private Label labelAjoutPlaque;
    @FXML
    private VBox titleAcceuil11;
    @FXML
    private VBox titleAcceuil111;
    @FXML
    private ComboBox experienceCB;
    @FXML
    private ImageView refuserIV;
    @FXML
    private ImageView validerResultat;
    @FXML
    private TableView<?> tableResultat;
    @FXML
    private TableColumn<?, ?> resultExpCol;
    @FXML
    private TableColumn<?, ?> resultatReplicatCol;
    @FXML
    private TableColumn<?, ?> decisionCol;
    @FXML
    private TableColumn<?, ?> couleurCol;
    @FXML
    private TableColumn<?, ?> moyCOl;
    @FXML
    private TableColumn<?, ?> moyRougeCol;
    @FXML
    private TableColumn<?, ?> moyVertCol;
    @FXML
    private TableColumn<?, ?> moyBleuCol;
    @FXML
    private TableColumn<?, ?> moyTransCol;
    @FXML
    private TableColumn<?, ?> sdCol;
    @FXML
    private TableColumn<?, ?> sdRougeCol1;
    @FXML
    private TableColumn<?, ?> sdVertCol1;
    @FXML
    private TableColumn<?, ?> sdBleuCol1;
    @FXML
    private TableColumn<?, ?> sdTransCol1;
    @FXML
    private TableColumn<?, ?> resultExpCol1;

    private Button lancerExpButton;
    
    private Connexion connex;
    private Connection con;
    private Statement stmt; 
    private ResultSet rs; 
    private Personne personne; 
    private Integer id_plaque,nb_puit;
    private String type_plaque;
    
    //liste observable
    private ObservableList<projetbasededonnee.Data.Laborantin> dataPlaque;
    
    private ArrayList<Integer> listeIdPlaque;
    
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
                        
                        projetbasededonnee.Data.Laborantin plaque = getTableView().getItems().get(getIndex());
                        System.out.println(plaque);
                        accueilLaboPane.setVisible(false);
                        LancerPlaquePage.setVisible(true); 
                        EmplacementPlaquePage.setVisible(false);
                        validationPage.setVisible(false);
                        
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
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
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
                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
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
                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
            }
                
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
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
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
                Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
            }

            accueilLaboPane.setVisible(false);
            LancerPlaquePage.setVisible(true); 
            EmplacementPlaquePage.setVisible(false);
            validationPage.setVisible(false);
        
        }catch (Exception e) {
            Logger.getLogger(AcceuilChercheurController.class.getName()).log(Level.SEVERE, null, e);
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
    
     public void setConnection(Connexion cone)
    {
        connex = cone;
    } 
    
    public void setPersonne(Personne personneE){
        personne=personneE; 
    }
}
