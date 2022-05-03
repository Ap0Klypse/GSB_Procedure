package gsb.modele.dao; 
import gsb.modele.Medicament; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.HashMap; 
 
public class MedicamentDao { 
	 
	/* 
	 * rechercherMedicament(String idMedicament) 
	 * Rechercher un médicament via le code d'un m�dicament 
	 */ 
	public static Medicament rechercherMedicament(String idMedicament) { 
		Medicament unMedicament = null; 
		ResultSet query = ConnexionMySql.execReqSelectionSqlServer("select * from MEDICAMENT where MED_DEPOTLEGAL = '" + idMedicament + "'"); 
		 
		try { 
			if (query.next()) { 
				unMedicament = new Medicament(query.getString(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getFloat(6), query.getString(7), query.getString(8));	 
			}; 
			} 
		catch(Exception e) { 
			System.out.println("select * from MEDICAMENT where MED_DEPOTLEGAL = '" + idMedicament + "'"); 
			e.printStackTrace(); 
			} 
		ConnexionMySql.fermerConnexionBd(); 
		return unMedicament; 
	} 
	/* 
	 * ListMedicamentDetail 
	 * Renvoie la liste de tous les m�dicaments 
	 */ 
	public static ArrayList<Medicament> ListMedicamentDetail(){ 
		 
		ArrayList<Medicament> ListeDesMedicament = new ArrayList<Medicament>(); 
		Medicament unMedicament = null; 
		ResultSet query = ConnexionMySql.execReqSelectionSqlServer("select * from MEDICAMENT"); 
		try{ 
		while (query.next()) { 
			unMedicament = null; 
			unMedicament = new Medicament(query.getString(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getFloat(6), query.getString(7), query.getString(8)); 
			ListeDesMedicament.add(unMedicament); 
			} 
		} 
		catch (SQLException e) { 
			e.printStackTrace(); 
			System.out.println("Erreur ListMedicament" + e.getMessage()); 
		} 
		return ListeDesMedicament; 
	} 
	 
	 
	/* 
	 * ListMedicamentFam(String famille) 
	 * Renvoie la liste des medicament de la famille @param 
	 */ 
	public static ArrayList<Medicament> ListMedicamentFam(String famille){ 
		ArrayList<Medicament> ListeDesMedicamentFamille = new ArrayList<Medicament>(); 
		Medicament unMedicament = null; 
		ResultSet query = ConnexionMySql.execReqSelectionSqlServer("select * from MEDICAMENT where FAM_LIBELLE = '" + famille + "'"); 
		try{ 
		while (query.next()) { 
			unMedicament = null; 
			unMedicament = new Medicament(query.getString(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getFloat(6), query.getString(7), query.getString(8)); 
			ListeDesMedicamentFamille.add(unMedicament); 
			} 
		} 
		catch (SQLException e) { 
			e.printStackTrace(); 
			System.out.println("Erreur ListMedicamentFamille" + e.getMessage()); 
		} 
		return ListeDesMedicamentFamille; 
	}
	
	
	public static Medicament ListMedicamentCode(String Code){ 
		Medicament unMedicament = null; 
		
		try{
			ResultSet query = ConnexionMySql.execReqSelectionSqlServer("SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL = '" + Code + "'"); 
			while (query.next()) { 
			unMedicament = new Medicament(query.getString(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getFloat(6), query.getString(7), query.getString(8));
			}
		} 
		catch (SQLException e) { 
			e.printStackTrace(); 
			System.out.println("Erreur ListMedicamenCode: \n" + e.getMessage()); 
		} 
		return unMedicament; 
	} 
	 
	/* 
	 * insertNewMedicament() 
	 * Insert un nouveau m�dicament dans la table m�dicament 
	 */ 
	public static void insertNewMedicament(String depotLegal, String nom, String composition, String effet, String contreIndic, String famille, String libelleFam) { 
		 
		try{ 
			ConnexionMySql.execReqMaj("INSERT INTO `MEDICAMENT` (`MED_DEPOTLEGAL`, `MED_NOMCOMMERCIAL`, `MED_COMPOSITION`, `MED_EFFETS`, `MED_CONTREINDIC`, `FAM_CODE`, `FAM_LIBELLE`)\r\n" 
					+ "VALUES ('"+ depotLegal + "', '" + nom + "', '" + composition + "', '" + effet + "', '" + contreIndic + "', '" + famille + "', '" + libelleFam + "');"); 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
			System.out.println("Erreur lors de l'insertion du Médicament" + e.getMessage()); 
		} 
	}
	
	public static HashMap<String,Medicament> dicoFamille(String Famille){
		HashMap<String, Medicament> dicoFamille = new HashMap<String, Medicament>();
		String matRq;
		if(Famille==null) {
			matRq = "%";
		}else {
			matRq = "%" + Famille + "%";
		}
		ResultSet query = ConnexionMySql.execReqSelectionSqlServer("select * from MEDICAMENT where FAM_CODE like '"+matRq+"'");
		try {
			while(query.next()){
				String reference = query.getString(1);
				dicoFamille.put(reference, MedicamentDao.rechercherMedicament(reference));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("erreur DicoFamille");
		}
		
		return dicoFamille;
		
	}
	 
	 
} 
