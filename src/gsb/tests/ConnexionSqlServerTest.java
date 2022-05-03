package gsb.tests;

import java.sql.ResultSet;

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteurDao;

public class ConnexionSqlServerTest {

	public static void main(String[] args) {
		// TODO Raccord de méthode auto-généré
		ResultSet resultat = ConnexionMySql.execReqSelectionSqlServer("SELECT * FROM MEDECIN WHERE RETRAITE=0");
		
	try {
		while (resultat.next()) {
			System.out.println(resultat.getString(1) + "  "
					+ resultat.getString(2));
		}
		;
		System.out.println("fin");
	} catch (Exception e) {
		System.out.println("Exception "+e);
		e.printStackTrace();
	}
}
	
}
