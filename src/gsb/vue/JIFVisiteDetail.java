package gsb.vue;

import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;
import gsb.modele.Medecin;
import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.modele.Visiteur;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class JIFVisiteDetail extends JInternalFrame implements ActionListener {

	
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pComm;
	protected JScrollPane pListe;
	
	private HashMap<String,Offrir> diccoMedicament;
	protected JTable table;
	
	protected JLabel JLref;
	protected JLabel JLdate;
	protected JLabel JLcomm;
	protected JLabel JLmatricule;
	protected JLabel JLcodeMed;
	protected JLabel JLconfiance;
	
	protected JTextField JTref;
	protected JTextField JTdate;
	protected JTextArea JTcomm;
	protected JTextField JTmatricule;
	protected JTextField JTcodeMed;
	protected JTextField JTconfiance;
		
	
	
	
	
	public JIFVisiteDetail(Visite uneVisite) {
		
		setTitle("Visite détaillée");
		p = new JPanel();
        pTexte = new JPanel(new GridLayout(5,2));
        pComm= new JPanel(new GridLayout(1,2));
        
        JLref= new JLabel("Référence");
        JLdate= new JLabel("Date visite");
        JLmatricule= new JLabel("Matricule visiteur");
        JLcodeMed= new JLabel("Code médecin");
        JLconfiance= new JLabel("indice de confiance");
     
        JLcomm= new JLabel("Commentaire");
		
        JTref = new JTextField(20);
        JTref.setMaximumSize(JTref.getPreferredSize());
        JTdate = new JTextField();
        JTmatricule = new JTextField();
        JTcodeMed = new JTextField();
        JTconfiance = new JTextField();
        
        JTcomm= new JTextArea(5,20);
        JTcomm.setMaximumSize(JTcomm.getPreferredSize());
        
        pTexte.add(JLref);
        pTexte.add(JTref);
        pTexte.add(JLdate);
        pTexte.add(JTdate);

        pTexte.add(JLmatricule);
        pTexte.add(JTmatricule);
        pTexte.add(JLcodeMed);
        pTexte.add(JTcodeMed);
        
        pTexte.add(JLconfiance);
        pTexte.add(JTconfiance);
        
        p.add(pTexte);
        
        pComm.add(JLcomm);
        pComm.add(JTcomm);
        p.add(pComm);
        
        diccoMedicament = new HashMap<String,Offrir>();
        diccoMedicament = VisiteDao.rechercherMedicaments(uneVisite.getReference());
        int nbLignes = diccoMedicament.size();
        
        int i=0;
        int j=1;
		String[][] data = new String[nbLignes][3] ;
		

		for(Map.Entry<String,Offrir> uneEntree : diccoMedicament.entrySet()) {
			data[i][0]="Medicament "+j;
			data[i][1]=uneEntree.getValue().getDepotLegal();
			data[i][2]=""+uneEntree.getValue().getQuantite()+"";
			j++;
			i++;
		}
		
		String[] columnNames= {"","Dépot légal","Quantité offerte"};
        table = new JTable(data,columnNames);
        table.getSelectionModel().addListSelectionListener(table);
		pListe = new JScrollPane(table);
		pListe.setPreferredSize(new Dimension(400, 60));
		p.add(pListe); //ajout liste
        
        Container contentPane = getContentPane();
        contentPane.add(p);
        
        JTref.setText(uneVisite.getReference());
        JTdate.setText(uneVisite.getDateVisite());
        JTcomm.setText(uneVisite.getCommentaire());
        JTmatricule.setText(uneVisite.getLeVisiteur().getMatricule());
        JTcodeMed.setText(uneVisite.getLeMedecin().getCodeMed());
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



	
	

}
