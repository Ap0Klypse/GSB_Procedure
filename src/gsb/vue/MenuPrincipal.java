package gsb.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**	
 * @author Isabelle 22 f�vr. 2015 TODO Pour changer le mod�le de ce commentaire
 *         de type g�n�r�, allez � : Fen�tre - Pr�f�rences - Java - Style de
 *         code - Mod�les de code
 */
public class MenuPrincipal extends JFrame implements ActionListener {

	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2591453837113855452L;

	protected JInternalFrame myJInternalFrame;
	protected JDesktopPane desktopPane;
	protected JMenuBar mbar;
	protected JMenu mMedecins;
	protected JMenu mMedicaments;
	protected JMenu mVisiteur;

	protected JMenu mVisites;

	/**
	 * 
	 */
	public MenuPrincipal() {

		myJInternalFrame = new JInternalFrame(); // pour affichage d'une seule
													// JInternalFrame � la fois
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.gray);
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);

		setTitle("GSB");
		setSize(700, 500);

		// Ajout d'une barre de menus � la fen�tre
		mbar = new JMenuBar();
		mMedecins = new JMenu("Medecins");
		JMenuItem mC1 = new JMenuItem("Consultation Medecin");
		mC1.addActionListener(this); // installation d'un �couteur d'action
		mMedecins.add(mC1);
		JMenuItem mC2 = new JMenuItem("Liste Medecins");
		mC2.addActionListener(this);
		mMedecins.add(mC2);
		JMenuItem mC3 = new JMenuItem("Archiver Retrait�s");
		mC3.addActionListener(this);
		mMedecins.add(mC3);

		mMedicaments = new JMenu("Medicaments");
		JMenuItem mE1 = new JMenuItem("Consultation Medicament");
		mE1.addActionListener(this); // installation d'un �couteur d'action
		mMedicaments.add(mE1);
		JMenuItem mE3 = new JMenuItem("Medicament par Famille");
		mE3.addActionListener(this); // installation d'un �couteur d'action
		mMedicaments.add(mE3);
		JMenuItem mE2 = new JMenuItem("Ajout Medicaments");
		mE2.addActionListener(this);
		mMedicaments.add(mE2);

		mVisites = new JMenu("Visites");
		JMenuItem mA1 = new JMenuItem("Liste Visite");
		mA1.addActionListener(this); // installation d'un �couteur d'action
		mVisites.add(mA1);
		JMenuItem mA2 = new JMenuItem("Ajout Visite");
		mA2.addActionListener(this);
		mVisites.add(mA2);
		JMenuItem mA3 = new JMenuItem("Maj Visite");
		mA3.addActionListener(this);
		mVisites.add(mA3);
		
		mVisiteur = new JMenu("Visiteur");
		JMenuItem mB1 = new JMenuItem("Consultation Visiteur");
		mB1.addActionListener(this); // installation d'un �couteur d'action
		mVisiteur.add(mB1);
		JMenuItem mB2 =new JMenuItem("Ajout Visiteur");
		mB2.addActionListener(this);
		mVisiteur.add(mB2);

		mbar.add(mMedecins);
		mbar.add(mMedicaments);
		mbar.add(mVisites);
		mbar.add(mVisiteur);
		setJMenuBar(mbar);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent evt) {
		// TODO Raccord de m�thode auto-g�n�r�
		if (evt.getSource() instanceof JMenuItem) {
			String ChoixOption = evt.getActionCommand();

			if (ChoixOption.equals("Consultation Medecin")) {
				// Creation d'une sous-fen�tre
				ouvrirFenetre(new JIFMedecinCons());

			} else if (ChoixOption.equals("Liste Medecins")) {
				ouvrirFenetre(new JIFMedecinListeDic(this));
			} else if (ChoixOption.equals("Ajout Visite")) {
				ouvrirFenetre(new JIFVisiteAjout());
			} else if(ChoixOption.equals("Archiver Retrait�s")) {
				ouvrirFenetre(new JIFMedecinArchive(this));
			}else if (ChoixOption.equals("Liste Visite")) {
				ouvrirFenetre(new JIFVisiteListe(this, null,null));
			} else if (ChoixOption.equals("Maj Visite")) {
				ouvrirFenetre(new JIFVisiteMaj(this, null));
			} else if (ChoixOption.equals("Ajout Visiteur")) {
				ouvrirFenetre(new JIFVisiteurAjout());
			} else if(ChoixOption.equals("Ajout Medicaments")) {
				ouvrirFenetre(new JIFAjoutMedicament());
			} else if(ChoixOption.equals("Consultation Medicament")) {
				ouvrirFenetre(new JIFMedicamentListe(this));
			}else if(ChoixOption.equals("Medicament par Famille")) {
				ouvrirFenetre(new JIFMedicamentFamille(this, null));
			}
		}

	}

	public void ouvrirFenetre(JInternalFrame uneFenetre) {
		myJInternalFrame.dispose(); // si une fen�tre �tait dej� affich�e, elle
									// est lib�r�e
		myJInternalFrame = uneFenetre;
		myJInternalFrame.setVisible(true);
		myJInternalFrame.setResizable(true);
		myJInternalFrame.setMaximizable(true);
		myJInternalFrame.setClosable(true);
		myJInternalFrame.setSize(480, 300);
		desktopPane.add(myJInternalFrame);
	}

}
