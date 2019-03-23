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
   
    private Connection con; 

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        connectServer();
    }   
    
    /**
     * Permet de se connecter par le bais d'un profil, un chercheur ou un 
     * laborantin s'il existe dans la BDD
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    public void connexionButton(ActionEvent event) throws IOException, SQLException
    {
//        //FAIRE UN LABEL POUR AFFICHER UN MESSAGE D'ERREUR 
//        if (emailTF.getText().isEmpty() == false && mdpPF.getText().isEmpty() == false) {
//        try {
//             Statement stmt = con.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT fonction"
//                + "FROM PERSONNE"
//                + "WHERE email ='" + emailTF.getText() + "' AND mot_de_passe = '" + mdpPF.getText() + "'");
//        while (rs.next()) {
//            String res=rs.getString(1); 
//            System.out.println(res);
//            if ("chercheur".equals(res)) {
//                Parent ajoutParent = FXMLLoader.load(getClass().getResource("Chercheur.fxml"));
//                Scene ajoutScene = new Scene(ajoutParent);
//                    
//                //This line gets the Stage information
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//                window.setScene(ajoutScene);
//                window.show();
//            }
//            else if ("laborantin".equals(res)){
//                Parent ajoutParent = FXMLLoader.load(getClass().getResource("Laborantin.fxml"));
//                Scene ajoutScene = new Scene(ajoutParent);
//                    
//                //This line gets the Stage information
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//                window.setScene(ajoutScene);
//                window.show();
//            }
//        }
//            
//          } catch (SQLException e) {
//                System.out.println(e);
//            }  
//        }
        Parent ajoutParent = FXMLLoader.load(getClass().getResource("Chercheur.fxml"));
        Scene ajoutScene = new Scene(ajoutParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(ajoutScene);
        window.show();
    }
    
    /**
     * Methode qui permet de se connecter à la base de donnée
     */
    public void connectServer() {
//        try {
//            //step1 load the driver class  
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            //step2 create  the connection object  
//            con = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@192.168.254.3:1521:PFPBS", "GROUPE_29", "GROUPE_29");
//            //step3 create the statement object  
//            Statement stmt = con.createStatement();
//            //step4 execute query  
//            ResultSet rs = stmt.executeQuery("select * from EXPERIENCE");
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//            }
//          
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }
    
    
}
