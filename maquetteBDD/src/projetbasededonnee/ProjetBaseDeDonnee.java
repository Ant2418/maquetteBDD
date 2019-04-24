package projetbasededonnee;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale du projet BDD IHM, gestion de plaque de laboratoire. </br>
 * Un chercheur ou un laborantin peut se connecter.
 * @verison 26/04/2019
 * @author Antoine et Ludivine
 */
public class ProjetBaseDeDonnee extends Application {

    private Personne pers;
    private Connexion maCo; 
    
    /**
     * Methode qui permet de lancer la page de connexion (connexion.fxml) </br>
     * Creation de la personne et de sa connexion
     * @param primaryStage fenetre principale de taille minimale 1020x610
     * @throws IOException 
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
        Parent root = (Parent) loader.load();
        // Permet d'associer ton controleur à page à charger pour faire le lien
        ConnexionController coCo = loader.getController();

        // permet de faire la connection et de la stocker dans l'appli
        maCo = new Connexion();
      
        Scene scene = new Scene(root);
        
        coCo.setConnexion(maCo); 
        
        //CREER UN OBJECT PERSONNE 
        pers= new Personne("0", "0", "0", "0");
        coCo.setPersonne(pers); 
        
        primaryStage.setTitle("Gestions de plaque de laboratoire");
        primaryStage.setMinWidth(1020);
        primaryStage.setMinHeight(610);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Permet de créer le fichier jar
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Getter de la personne qui est connecte
     * @return 
     */
    public Personne getPersonne(){
        return pers; 
    }
    
    /**
     * Getter de la connexion
     * @return 
     */
    public Connexion getConnect(){
        return maCo; 
    }
}
