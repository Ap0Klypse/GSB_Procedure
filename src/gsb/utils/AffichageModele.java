package gsb.utils;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;

public class AffichageModele {
	
	public static void afficherLocalite(Localite unLocalite) {
		System.out.println("CodePostal :"+unLocalite.getCodePostal());
		System.out.println("une ville :"+unLocalite.getVille());
	}
	
	public static void afficherVisiteur(Visiteur unVisiteur) {
		System.out.println("matricule Visiteur :"+unVisiteur.getMatricule());
		System.out.println("nom Visiteur :"+unVisiteur.getNom());
		System.out.println("prenom Visiteur :"+unVisiteur.getPrenom());
		System.out.println("adresse Visiteur :"+unVisiteur.getAdresse());
		afficherLocalite(unVisiteur.getCodePostal());
		System.out.println("date entrée Visiteur :"+unVisiteur.getDateEntree());
		System.out.println("codeUnit Visiteur :"+unVisiteur.getCodeUnit());
		System.out.println("nomUnit Visiteur :"+unVisiteur.getNomUnit());
	}
	
	public static void afficherVisite(Visite uneVisite) {
		System.out.println("Reference Visite :"+uneVisite.getReference());
		System.out.println("Date de Visite :"+uneVisite.getDateVisite());
		System.out.println("Commentaire Visite :"+uneVisite.getCommentaire());

		afficherMedecin(uneVisite.getLeMedecin());
		afficherVisiteur(uneVisite.getLeVisiteur());

		
	}
	
	public static void afficherMedecin(Medecin leMedecin) {
		System.out.println("Code du Médecin :"+leMedecin.getCodeMed());
		System.out.println("Nom Médecin :"+leMedecin.getNom());
		System.out.println("Prenom Médecin :"+leMedecin.getPrenom());
		System.out.println("Adresse Médecin :"+leMedecin.getAdresse());
		afficherLocalite(leMedecin.getLaLocalite());
		System.out.println(" Numéro de Téléphone :"+leMedecin.getTelephone());
		System.out.println("confiance :"+leMedecin.getConfiance());
		System.out.println("Spécialité :"+leMedecin.getSpecialite());
	}
}
