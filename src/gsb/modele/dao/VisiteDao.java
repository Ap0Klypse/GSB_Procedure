package gsb.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.dao.MedicamentDao;


public class VisiteDao {
	/** ConnexionMySql.execReqSelection("select * from VISITE where REFERENCE='"+reference+"'"); */
	public static Visite rechercher(String reference) {
		Visite uneVisite =null;
		
		ResultSet reqSelection = ConnexionMySql.execReqSelectionSqlServer("select * from VISITE where REFERENCE='"+reference+"'");
		try {
			if (reqSelection.next()) {
				String ref = reqSelection.getString(1);
				String date = reqSelection.getString(2);
				String comm = reqSelection.getString(3);
				String visiteur = reqSelection.getString(4);
				String medecin = reqSelection.getString(5);
				Visiteur leVisiteur = VisiteurDao.rechercher(visiteur);
				Medecin leMedecin = MedecinDao.rechercher(medecin);
				uneVisite = new Visite(ref, date, comm, leVisiteur, leMedecin);
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from VISITE where REFERENCE='"+reference+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return uneVisite;
	}
	
	public static int creer(String ref, String date, String comm, String matVisiteur, String codeMed ) {
	/*	String ref = uneVisite.getReference();
		String date = uneVisite.getDateVisite();
		String comm = uneVisite.getCommentaire();
		Visiteur leVisiteur = uneVisite.getLeVisiteur();
		String matVisiteur = leVisiteur.getMatricule();
		Medecin leMedecin = uneVisite.getLeMedecin();
		String codeMed = leMedecin.getCodeMed();
	**/	
		int result = 0;
		if( !ref.equals(rechercher(ref).getReference())) {
			
			String reqInsertion = "insert into VISITE values('"+ref+"','"+date+"','"+comm+"','"+matVisiteur+"','"+codeMed+"')";
			result = ConnexionMySql.execReqMaj(reqInsertion);
			ConnexionMySql.fermerConnexionBd();
		}
		
		
		return result;
		}
	
	public static HashMap<String,Visite> retournerDictionnaireDesVisitesRecherchees(String matricule, String date){
		HashMap<String,Visite> diccoDesVisites = new HashMap<String,Visite>();
		String matRq;
		String dateRq;
		if(matricule==null) {
			 matRq="%";
		}
		else {
			 matRq="%"+matricule+"%";
			
		}
		
		if(date==null) {
			 dateRq="%";
		}
		else {
			 dateRq="%"+date+"%";
		}
		
		ResultSet reqSelection = ConnexionMySql.execReqSelectionSqlServer(
				"select REFERENCE from VISITE where MATRICULE like'"+matRq+"' and DATEVISITE like'"+dateRq+"'");
		try {
			while(reqSelection.next()) {
				String reference=reqSelection.getString(1);
				diccoDesVisites.put(reference, VisiteDao.rechercher(reference));
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerDiccoDesVisitesRecherchees");
		}
		return diccoDesVisites;
		
		
	}
	
	public static int modifCommentaire(String reference, String commentaire) {
		String reqModif ="update VISITE SET COMMENTAIRE='"+commentaire+"' WHERE REFERENCE='"+reference+"'";
		int result=ConnexionMySql.execReqMaj(reqModif);
		ConnexionMySql.fermerConnexionBd();
		return result;
	}
	
	public static HashMap<String,Offrir> rechercherMedicaments(String reference) {
		HashMap<String,Offrir> lesMedicaments = new HashMap<String,Offrir>();
		ResultSet reqVisite = ConnexionMySql.execReqSelectionSqlServer("select * from OFFRIR where REFERENCE='"+reference+"'");
		int i = 0;
		try {
			while(reqVisite.next()) {
				Offrir leMedicament=null;
				int quantite=reqVisite.getInt(1);
				String ref=reqVisite.getString(2);
				String depotlegal=reqVisite.getString(3);
				leMedicament = new Offrir(quantite, ref, depotlegal);
				lesMedicaments.put("'"+i+"'",leMedicament);
				i++;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerMedicaments");
		}
		
		
		return lesMedicaments;
	}
	
	public static int updateMedicaments(String ref, String depotIni, String depotNew, String quantite) {
		int resultat=0;
		if(depotIni==null) {
			String reqInsert="insert into OFFRIR values("+quantite+",'"+ref+"','"+depotNew+"')";
			resultat= ConnexionMySql.execReqMaj(reqInsert);
			ConnexionMySql.fermerConnexionBd();
			
		}
		else {
			String reqUpdate="update OFFRIR set QUANTITE="+quantite+", MED_DEPOTLEGAL='"+depotNew+"' where MED_DEPOTLEGAL='"+depotIni+"'";
			resultat=ConnexionMySql.execReqMaj(reqUpdate);
			ConnexionMySql.fermerConnexionBd();
		}
		return resultat;
	}
	
	
	/**public static HashMap<String,Visite> retournerDictionnaireDesVisites(){
		HashMap<String,Visite> diccoDesVisites = new HashMap<String,Visite>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select REFERENCE from VISITE ");
		try {
			while(reqSelection.next()) {
				String reference=reqSelection.getString(1);
				diccoDesVisites.put(reference, VisiteDao.rechercher(reference));
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerDiccoDesVisites");
		}
		return diccoDesVisites;
		
		
	} */
}
