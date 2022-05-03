package gsb.vue;

import gsb.modele.Medecin;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.MedecinDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JIFMedecinArchive extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ArrayList<Medecin> lesRetraites;
	
	protected JPanel p;
	protected JPanel pInfo;
	protected JScrollPane scrollPane;
	protected MenuPrincipal fenetreContainer;
	protected JButton Barchiver;
	protected JLabel JLinfo;
	
	public JIFMedecinArchive(MenuPrincipal fenetreContainer) {
		
		this.fenetreContainer = fenetreContainer;
		//recupération des medecins retraités non archivés
		lesRetraites = MedecinDao.retournerRetraites();
		int nbLignes=lesRetraites.size();
		
		JTable table;
		p = new JPanel(new GridLayout(2,1));
		
		int i=0;
		String[][] data = new String[nbLignes][4] ;
		for(Medecin unMedecin : lesRetraites){
			data[i][0] = unMedecin.getCodeMed();
			data[i][1] = unMedecin.getNom();
			data[i][2] = unMedecin.getPrenom();
			data[i][3] = unMedecin.getLaLocalite().getVille() ;
			i++;
			}
		String[] columnNames = {"Code", "Nom","Prenom","Ville"};
		
		table = new JTable(data, columnNames);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		p.add(scrollPane);
		
		
		pInfo = new JPanel();
		if( nbLignes ==0) {
			JLinfo = new JLabel("Il n'y a plus de retraités non archivés");
			pInfo.add(JLinfo);
		}
		else {
			
			Barchiver = new JButton("Archiver les médecins retraités");
			Barchiver.addActionListener(this);
			pInfo.add(Barchiver);
			
		}
		
		
		
		p.add(pInfo);
		
		
		// mise en forme de la fenêtre
				Container contentPane = getContentPane();
				contentPane.add(p);
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()== Barchiver) {
			try {
				MedecinDao.archiverMedecins();
				fenetreContainer.ouvrirFenetre(new JIFMedecinArchive(fenetreContainer));
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		
		}
	}
}
