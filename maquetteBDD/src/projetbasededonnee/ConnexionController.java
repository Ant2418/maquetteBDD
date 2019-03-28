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
import static javafx.beans.binding.Bindings.or;
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
import javafx.stage.Stage;

/**
 * FXML Controller class de connexion.fxml
 *
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
        if (emailTF.getText().isEmpty() == false || mdpPF.getText().isEmpty() == false) { 
            //A mettre && quand on devra faire avec mail et mot de passe
         
            if ("chercheur".equals(emailTF.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Chercheur.fxml"));
                Parent ajoutParent = (Parent) loader.load();

                Scene ajoutScene = new Scene(ajoutParent);
                    
//                    AcceuilChercheurController ACCo = loader.getController();
//                    ACCo.setMain(main);

                    //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(ajoutScene);
                window.show();
            }
//                else if ("laborantin".equals(res)){
            else if ("laborantin".equals(emailTF.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Laborantin.fxml"));
                Parent ajoutParent = (Parent) loader.load();

//                    LaborantinController LCO = loader.getController();
                Scene ajoutScene = new Scene(ajoutParent);
//                    LCO.setMain(main);
                    
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
        else
        {
            ErreurLabel.setVisible(true);
                  
        }
    }

          
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        }
 
//    }
 
}
