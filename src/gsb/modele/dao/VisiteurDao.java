package gsb.modele.dao;

import java.sql.ResultSet;

import gsb.modele.Localite;
import gsb.modele.Visiteur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class VisiteurDao {
	
	public static Visiteur rechercher(String matricule){
		Visiteur unVisiteur=null;
		Localite uneLocalite= null;
		ResultSet reqSelection = ConnexionMySql.execReqSelectionSqlServer("select * from VISITEUR where MATRICULE ='"+matricule+"'");
		try {
			if (reqSelection.next()) {
				uneLocalite = LocaliteDao.rechercher(reqSelection.getString(7));
				unVisiteur = new Visiteur(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4),
						reqSelection.getString(5), reqSelection.getString(6),uneLocalite, reqSelection.getString(8), reqSelection.getString(9), reqSelection.getString(10));
			};
		}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from VISITEUR where MATRICULE ='"+matricule+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return unVisiteur;
	}
	
	public static int creer(Visiteur unVisiteur) {
		String requeteInsertion;
		String matricule = unVisiteur.getMatricule();
		String nom = unVisiteur.getNom();
		String prenom = unVisiteur.getPrenom();
		String login = unVisiteur.getLogin();
		String mdp = unVisiteur.getMdp();
		String adresse = unVisiteur.getAdresse();
		Localite codePostal = unVisiteur.getCodePostal();
		String dateEntree = unVisiteur.getDateEntree();
		String codeUnit = unVisiteur.getCodeUnit();
		String nomUnit = unVisiteur.getNomUnit();
		requeteInsertion ="insert into Visiteur values("+matricule+",'"+nom+"','"+prenom+"','"+login+"','"
				+mdp+"','"+adresse+"','"+codePostal+"','"+dateEntree+"','"+codeUnit+"',"+nomUnit+")";
		int result = ConnexionMySql.execReqMaj(requeteInsertion);
		return result;
	}
}
			
