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
		System.out.println("date entr�e Visiteur :"+unVisiteur.getDateEntree());
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
		System.out.println("Code du M�decin :"+leMedecin.getCodeMed());
		System.out.println("Nom M�decin :"+leMedecin.getNom());
		System.out.println("Prenom M�decin :"+leMedecin.getPrenom());
		System.out.println("Adresse M�decin :"+leMedecin.getAdresse());
		afficherLocalite(leMedecin.getLaLocalite());
		System.out.println(" Num�ro de T�l�phone :"+leMedecin.getTelephone());
		System.out.println("confiance :"+leMedecin.getConfiance());
		System.out.println("Sp�cialit� :"+leMedecin.getSpecialite());
	}
}
