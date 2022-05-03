package gsb.modele.dao;


/*
 * Créé le 23 sept. 2014
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.CallableStatement;

/**
 * @author Isabelle
 * 23 sept. 2014
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class ConnexionMySql { // DAO = Data Access Object
	
	static Connection cnx;
	
	public ConnexionMySql(){
		cnx = null;
		
	}
	
	/**
	 * methode qui permet la connexion à la base de données
	 * le fait que la méthode soit static permet d'éviter d'instancier dans une classe un objet ConnexioMySql
	 * pour utiliser cette méthode écrire : ConnexionMySql.connecterBd()
	 */
	public static void connecterBd(){
		//connexion à la base de donnée à partir de jdbc
		String url = "jdbc:mysql://192.177.1.13:3306/GSB"; // url : chaine de connexion
		// try permet d'essayer de lancer la connexion
		try {Class.forName("com.mysql.jdbc.Driver"); 
			cnx = DriverManager.getConnection(url,"usergsb","password"); 
		} 
		// si la connexion echoue un message d'erreur est affiché
        catch(Exception e) {  System.out.println("Echec lors de la connexion");  } 
		
		// url lycée: "jdbc:mysql://192.177.1.13:3306/GSB"
		//get connexion : url,"usergsb","password"
	}
	
	public static void connecterSqlServer() {
		//JDBC connexion SQL SERVER
		String url = "jdbc:sqlserver://172.20.10.10:1433; "
				+ "instanceName=SQLSERVER; "
				+ "databaseName=GSBJAVA;"
				+ "user=admindb;"
				+ "password=Chaussette1*; "
				+ "encrypt=true; "
				+ "trustServerCertificate=true";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cnx=DriverManager.getConnection(url);
			
		}catch(Exception e) {  System.out.println("Echec lors de la connexion "+e);  }
	}
	
	/**
	 * @param laRequete requête SQL de type SELECT
	 * @return un curseur qui contient les lignes obtenues lors de l'exécution de la requête, null sinon
	 * pour utiliser cette méthode écrire : ConnexionMySql.execReqSelection(uneRequete) où uneRequête est de type String
	 */
	public static ResultSet execReqSelection(String laRequete){ 
		connecterBd();
		ResultSet resultatReq = null;
		try {
				Statement requete = cnx.createStatement(); 
				resultatReq =requete.executeQuery(laRequete); 
		} 
		catch(Exception e) {  System.out.println("Erreur requete : "+laRequete);  }
		return resultatReq;	
	}
	
	public static ResultSet execReqSelectionSqlServer(String laRequete){ 
		connecterSqlServer();
		ResultSet resultatReq = null;
		try {
				Statement requete = cnx.createStatement(); 
				resultatReq =requete.executeQuery(laRequete); 
		} 
		catch(Exception e) {  System.out.println("Erreur requete : "+laRequete);  }
		return resultatReq;	
	}
	
	public static void execProcedure(String laProcedure) {
		connecterSqlServer();
		try {
			Statement requete = cnx.createStatement(); 
			requete.executeQuery(laProcedure); 
	} 
	catch(Exception e) {  }
	
	}
	
	public static int executeInitierConfiance()throws SQLException{
		connecterSqlServer();
		try(CallableStatement cstmt= cnx.prepareCall("{call dbo.initier_confiance(?)}");)
		{
			cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
			cstmt.execute();
			int message = cstmt.getInt(1);
			return message;
			}
		}
	
	public static void executeSetConfiance()throws SQLException{
		connecterSqlServer();
		try(CallableStatement cstmt= cnx.prepareCall("{call maj_confiance(?,?)}");)
		{
			cstmt.setInt("m002", 100);
			cstmt.execute();
			
			
			}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	/** Ici, on créer la requête avec la syntaxe pour procédure
	 * @param la procédure à appeler en BDD et les paramètres IN
	 * @return un resultset si la procédure fonctionne
	 * 		   rien si rien n'est trouvé selon les param
	 * 		   rien si erreur 
	public static ResultSet execProcedure(String laProcedure, String Params){
        connecterSqlServer();
        String query = "{ call "+ laProcedure + "(?) }" ;
        try {
            
            java.sql.CallableStatement temp = cnx.prepareCall(query);
            temp.setString(1, Params);
            ResultSet result = temp.executeQuery();
            
            return result;

        }catch (Exception er) {
            er.printStackTrace(); 
            System.out.println("echec requète : "+ er);
        }
        return null;      
    } */
	
	/**
	 * @param laRequete requête SQL de type INSERT, UPDATE ou DELETE
	 * @return 1 si la MAJ s'est bien déroulée, 0 sinon
	 * pour utiliser cette méthode écrire : ConnexionMySql.execReqMaj(uneRequete) où uneRequête est de type String
	 */
	public static int execReqMaj(String laRequete){
		connecterSqlServer();
		int nbMaj =0;
		try {
		Statement s = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        nbMaj = s.executeUpdate(laRequete);
        s.close();}
		catch (Exception er) {
			er.printStackTrace(); 
			System.out.println("echec requête : "+laRequete); }
		return nbMaj;       
	}
	
	/**
	 * attention : tant que la connexion n'est pas fermée, 
	 * les MAJ ne sont pas effectives, on reste en mode déconnecté
	 * pour utiliser cette méthode écrire : ConnexionMySql.fermerConnexionBd()
	 */
	public static void fermerConnexionBd(){
		try{cnx.close();}
		catch(Exception e) {  System.out.println("Erreur sur fermeture connexion");  } 
	}
	
	public static Connection getConnection() {
		connecterSqlServer();
		return cnx;
	}

}
