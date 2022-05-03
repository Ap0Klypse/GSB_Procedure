package gsb.tests;

import gsb.modele.Medecin;
import gsb.modele.Localite;
import gsb.utils.AffichageModele;

public class MedecinTest {
	public static void main(String[] args) {
		Localite unLocalite = new Localite("56100", "Lorient");
		Medecin leMedecin = new Medecin("m001", "Pagbot", "Matheo","Rue Mars", unLocalite, "0608268440",0,"Neurologue");
		
		AffichageModele.afficherMedecin(leMedecin);
	}

}
