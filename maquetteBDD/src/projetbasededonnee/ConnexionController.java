/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class de connexion.fxml
 * @version 28/03/2019
 * @author Ludivine and Antoine
 */
public class ConnexionController implements Initializable {

    @FXML    private TextField emailTF;
    @FXML    private PasswordField mdpPF;
    @FXML    private Button connexionButton;
    @FXML    private Label ErreurLabel; 
   
    private Statement stmt; 
    private ResultSet rs;
    private String prenom; 
    private String nom; 
    private String email;
    private Connection con;
    private Connexion connexion; 
    private Personne personne; 
    private Integer present;


    /**
     * Initializes the contrProjetBaseDeDonneeoller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    /**
     * Permet de se connecter par le bais d'un profil, un chercheur ou un 
     * laborantin s'il existe dans la BDD
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    public void connexionButton(ActionEvent event) throws  IOException, SQLException
    {
       ErreurLabel.setVisible(false);
        ErreurLabel.setText("Veuillez remplir tous les champs");
        if (emailTF.getText().isEmpty() == false && mdpPF.getText().isEmpty() == false) { 
            try{
                con = connexion.getCon();          
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT count(*) FROM PERSONNE WHERE email ='" + emailTF.getText() + "' AND mot_de_passe = '" + mdpPF.getText() + "'");
            while (rs.next()) {
               present=rs.getInt(1);            
            }   
            }catch (Exception e) {
                System.out.println(e);
            }
            
            if(present>0){
            
                try { 
                con = connexion.getCon();          
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT FONCTION, PRENOM, NOM, EMAIL FROM PERSONNE WHERE email ='" + emailTF.getText() + "' AND mot_de_passe = '" + mdpPF.getText() + "'");
                while (rs.next()) {
                    String res=rs.getString(1); 
                    prenom= rs.getString(2);
                    nom=rs.getString(3);
                    email=rs.getString(4);

    //              ajout le nom, prénom, email et fonction à la personne connecté
                    personne.setPrenom(prenom);
                    personne.setNom(nom);
                    personne.setEmail(email);
                    personne.setFonction(res);
//                    System.out.println(res);
                    if ("chercheur".equals(res)) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chercheur.fxml"));
                        Parent ajoutParent = (Parent) loader.load();

                        AcceuilChercheurController ACCo = loader.getController();
                        Scene ajoutScene = new Scene(ajoutParent);
                        ACCo.setConnection(connexion);
                        ACCo.setPersonne(personne);
                        ACCo.loadDataAccueilDatabase();

                        //This line gets the Stage information
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                        window.setScene(ajoutScene);
                        window.show();
                    }
                    else if ("laborantin".equals(res)){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Laborantin_1.fxml"));
                        Parent ajoutParent = (Parent) loader.load();

                        LaborantinController1 LCO = loader.getController();
                        Scene ajoutScene = new Scene(ajoutParent);
                        LCO.setConnection(connexion);
                        LCO.setPersonne(personne);
                        LCO.loadDataPlaque();
                        LCO.ComboTypePlaque();

                        //This line gets the Stage information
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                        window.setScene(ajoutScene);
                        window.show();
                    }
                    else
                    {
                    ErreurLabel.setText("Vous n'êtes pas inscrit");
                    ErreurLabel.setVisible(true);       
                    }     
                }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            else{
                ErreurLabel.setText("Vous n'êtes pas inscrit");
                ErreurLabel.setVisible(true);    
            }
        }
        else
        {
            ErreurLabel.setVisible(true);
                  
        }
    }
    
    /**
     * Evenement quand on clique avec le clavier sur ENTER sur connexion
     * @param event 
     */
    public void keyPressed(KeyEvent event) {
        if (event.getCode() == ENTER) {
            ErreurLabel.setVisible(false);
            ErreurLabel.setText("Veuillez remplir tous les champs");
        if (emailTF.getText().isEmpty() == false && mdpPF.getText().isEmpty() == false) { 
            try {
              
            con = connexion.getCon();          
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT FONCTION, PRENOM, NOM, EMAIL FROM PERSONNE WHERE email ='" + emailTF.getText() + "' AND mot_de_passe = '" + mdpPF.getText() + "'");
            while (rs.next()) {
                String res=rs.getString(1); 
                prenom= rs.getString(2);
                nom=rs.getString(3);
                email=rs.getString(4);
//                ajout le nom, prénom, email et fonction à la personne connecté
                personne.setPrenom(prenom);
                personne.setNom(nom);
                personne.setEmail(email);
                personne.setFonction(res);
//                System.out.println(res);
                if ("chercheur".equals(res)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Chercheur.fxml"));
                    Parent ajoutParent = (Parent) loader.load();
                 
                    AcceuilChercheurController ACCo = loader.getController();
                    Scene ajoutScene = new Scene(ajoutParent);
                    ACCo.setConnection(connexion);
                    ACCo.setPersonne(personne);
                    ACCo.loadDataAccueilDatabase();
                    
                    //This line gets the Stage information
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(ajoutScene);
                    window.show();
                }
                else if ("laborantin".equals(res)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Laborantin_1.fxml"));
                    Parent ajoutParent = (Parent) loader.load();

                    LaborantinController1 LCO = loader.getController();
                    Scene ajoutScene = new Scene(ajoutParent);
                    LCO.setConnection(connexion);
                    LCO.setPersonne(personne);
                    LCO.loadDataPlaque();
                    LCO.ComboTypePlaque();
                       
                    //This line gets the Stage information
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(ajoutScene);
                    window.show();
                }
                else 
                {
                ErreurLabel.setText("Vous n'êtes pas inscrit");
                ErreurLabel.setVisible(true);       
                }
            }
      
            } catch (Exception e) {
            System.out.println(e);
            }
        }
        else
        {
            ErreurLabel.setVisible(true);
                  
        }
        }
    }

    /**
     * Setter pour la connexion
     * @param conect 
     */
    public void setConnexion(Connexion conect){
        connexion = conect; 
   
    }
    
    /**
     * Setter pour la personne
     * @param personneE 
     */
    public void setPersonne(Personne personneE){
        personne=personneE;
    }
}
