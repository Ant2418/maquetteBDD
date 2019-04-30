package projetbasededonnee;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe qui permet de générer la connexion
 * @version 28/03/2019
 * @author Ludivine et Antoine
 */
public class Connexion {
     
    private Connection con; 
    
    /**
     * Constructeur de la classe Connexion
     */
    public Connexion(){
    try {
            //step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //step2 create  the connection object  
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.254.3:1521:PFPBS", "GROUPE_29", "GROUPE_29");
            //step3 create the statement object  
            Statement stmt = con.createStatement();
            
            projetbasededonnee.Data.CurrentDate data = new projetbasededonnee.Data.CurrentDate();
          
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    /**
     * Getter de la connection
     * @return con
     */
    public Connection getCon(){
        return con;  
    }
}
