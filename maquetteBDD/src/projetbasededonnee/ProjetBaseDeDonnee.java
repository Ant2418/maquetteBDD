/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbasededonnee;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Antoine
 */
public class ProjetBaseDeDonnee extends Application {
    
//    private String nom; 
//    private String prenom;
//    private String email; 
//    private String fonction; 
//    private Connection con; 
//    private Personne pers; 
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("connexion.fxml"));
        Scene scene = new Scene(root);
        
        // permet de faire la connection et de la stocker dans l'appli
//        Connexion maCo = new Connexion();
//        con = maCo.getConnection();
        
        // Permet d'associer ton controleur à page à charger pour faire le lien
        ConnexionController coCo = loader.getController();
//        coCo.setMain(this);
        
        
        //CREER UN OBJECT PERSONNE 
//        pers= new Personne(null, null, null, null);
        
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
//    public Personne getPersonne(){
//        return pers; 
//    }

}
