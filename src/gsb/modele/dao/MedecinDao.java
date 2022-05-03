/*
ù * Créé le 22 févr. 2015
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medecin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.microsoft.sqlserver.jdbc.SQLServerException;



/**
 * @author Isabelle
 * 22 févr. 2015
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class MedecinDao {
	
	public static Medecin rechercher(String codeMedecin){
		Medecin unMedecin=null;
		Localite uneLocalite= null;
		ResultSet reqSelection = ConnexionMySql.execReqSelectionSqlServer("select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
		try {
			if (reqSelection.next()) {
				uneLocalite = LocaliteDao.rechercher(reqSelection.getString(5));
				unMedecin = new Medecin(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), uneLocalite, reqSelection.getString(6),  reqSelection.getInt(11), reqSelection.getString(8) );	
			};
			//confiance = int 11e position
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return unMedecin;
	}
	
	public static ArrayList<Medecin> retournerCollectionDesMedecins(){
		ArrayList<Medecin> collectionDesMedecins = new ArrayList<Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelectionSqlServer("select CODEMED from MEDECIN");
		try{
		while (reqSelection.next()) {
			String codeMedecin = reqSelection.getString(1);
		    collectionDesMedecins.add(MedecinDao.rechercher(codeMedecin));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedecins()");
		}
		return collectionDesMedecins;
	}
	
	public static ArrayList<Medecin> retournerRetraites(){
		ArrayList<Medecin> collectionRetraites = new ArrayList<Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelectionSqlServer("EXEC medecins_retraite");
		try {
			while(reqSelection.next()) {
				String codeMedecin = reqSelection.getString(1);
				collectionRetraites.add(MedecinDao.rechercher(codeMedecin));
				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerRetraites()"+e);
	}
		return collectionRetraites;
	}
	
	public static void  archiverMedecins() {
		try {
			ConnexionMySql.execReqSelectionSqlServer("EXEC supprime_medecins_retraite");
			
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("erreur archivage() "+e);
			
		}
		
	}
	
	/*public static void majConfiance(String medecin, int confiance){
		ConnexionMySql.execReqSelectionSqlServer("EXEC maj_confiance "+medecin+", "+confiance);
		
	} **/
	public static void majConfiance(String medecin, int confiance) {
		ConnexionMySql.execProcedure("EXEC maj_confiance "+medecin+", "+confiance);
			
			
			
	}
	
	
	public static HashMap<String,Medecin> retournerDictionnaireDesMedecins(){
		HashMap<String, Medecin> diccoDesMedecins = new HashMap<String, Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelectionSqlServer("select CODEMED from MEDECIN");
		try{
		while (reqSelection.next()) {
			String codeMedecin = reqSelection.getString(1);
		    diccoDesMedecins.put(codeMedecin, MedecinDao.rechercher(codeMedecin));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerDiccoDesMedecins()");
		}
		return diccoDesMedecins;
	}

}
