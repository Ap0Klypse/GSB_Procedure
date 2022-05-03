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

import javax.swing.Action;
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

public class JIFVisiteMaj extends JInternalFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JInternalFrame myJInternalFrame;
	
	protected JPanel p;
	protected JPanel p1;
	protected JPanel p2;
	protected JPanel p3;
	protected JPanel p4;
	protected JScrollPane pListe;
	
	
	private HashMap<String,Offrir> diccoMedicament;
	protected JTable table;
	
	protected JLabel JLref;
	protected JLabel JLdate;
	protected JLabel JLcomm;
	protected JLabel JLmatricule;
	protected JLabel JLcodeMed;

	
	protected JTextField JTref;
	protected JTextField JTdate;
	protected JTextArea JTcomm;
	protected JTextField JTmatricule;
	protected JTextField JTcodeMed;
	
	protected MenuPrincipal leMenu;
	protected Visite uneVisite;
	protected JButton Bref;
	protected JButton Bmodif;
	protected JButton Boffre;
	
	public JIFVisiteMaj(MenuPrincipal leMenu, Visite uneVisite) {
		setTitle("Mise à jour Visite");
		
		this.leMenu=leMenu;
		
		p=new JPanel();
		p1=new JPanel(new GridLayout(2,2));
		p2=new JPanel(new GridLayout(3,2));
		p3=new JPanel(new GridLayout(1,2));
		p4=new JPanel(new GridLayout(1,2));
		
		JLref= new JLabel("Référence");
        JLdate= new JLabel("Date visite");
        JLcomm= new JLabel("Commentaire");
        JLmatricule= new JLabel("Matricule visiteur");
        JLcodeMed= new JLabel("Code médecin");
        
        JTref = new JTextField(20);
        JTref.setMaximumSize(JTref.getPreferredSize());
        JTdate = new JTextField(20);
        JTdate.setMaximumSize(JTdate.getPreferredSize());
        JTcomm = new JTextArea(5,20);
        JTcomm.setMaximumSize(JTcomm.getPreferredSize());
        JTmatricule = new JTextField(20);
        JTmatricule.setMaximumSize(JTmatricule.getPreferredSize());
        JTcodeMed = new JTextField();
        
        Bref = new JButton("Rechercher visite");
        p1.add(JLref);
        p1.add(JTref);
        p1.add(Bref);
        Bref.addActionListener(this);
        
        p2.add(JLdate);
        p2.add(JTdate);
        p2.add(JLmatricule);
        p2.add(JTmatricule);
        p2.add(JLcodeMed);
        p2.add(JTcodeMed);
        
        p3.add(JLcomm);
        p3.add(JTcomm);
        
        p.add(p1);
        p.add(p2);
        p.add(p3);
        
        Container contentPane = getContentPane();
        contentPane.add(p);
        
        if(uneVisite!=null) {
        	JTref.setText(uneVisite.getReference());
            JTdate.setText(uneVisite.getDateVisite());
            JTcomm.setText(uneVisite.getCommentaire());
            JTmatricule.setText(uneVisite.getLeVisiteur().getMatricule());
            JTcodeMed.setText(uneVisite.getLeMedecin().getCodeMed());
            
            Bmodif= new JButton("Enregistrer commentaire");
            Boffre= new JButton("Modifier médicaments offerts");
            p4.add(Bmodif);
            p4.add(Boffre);
            p.add(p4);
            Bmodif.addActionListener(this);
            Boffre.addActionListener(this);
        }
       /* diccoMedicament = new HashMap<String,Offrir>();
        diccoMedicament = VisiteDao.rechercherMedicaments(uneVisite.getReference());
        int nbLignes = diccoMedicament.size();
        
        int i=0;
		String[][] data = new String[nbLignes][3] ;
		

		for(Map.Entry<String,Offrir> uneEntree : diccoMedicament.entrySet()) {
			data[i][0]="Medicament "+i;
			data[i][1]=uneEntree.getValue().getDepotLegal();
			data[i][2]=""+uneEntree.getValue().getQuantite()+"";
			
			i++;
		}
		
		String[] columnNames= {"","Dépot légal","Quantité offerte"};
        table = new JTable(data,columnNames);
        table.getSelectionModel().addListSelectionListener(table);
		pListe = new JScrollPane(table);
		pListe.setPreferredSize(new Dimension(400, 100));
		p.add(pListe); //ajout liste
        **/
        
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==Bref) {
			try {
				String ref=JTref.getText();
				uneVisite = VisiteDao.rechercher(ref);
				leMenu.ouvrirFenetre(new JIFVisiteMaj(leMenu, uneVisite));
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		else if (e.getSource()== Bmodif) {
			try {
				String comm=JTcomm.getText();
				String ref=JTref.getText();
				VisiteDao.modifCommentaire(ref ,comm );
				uneVisite = VisiteDao.rechercher(ref);
				leMenu.ouvrirFenetre(new JIFVisiteMaj(leMenu ,uneVisite));
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		else if(e.getSource()==Boffre) {
			String ref=JTref.getText();
			leMenu.ouvrirFenetre(new JIFVisiteOffre(leMenu,ref));
		}
		
	}
}
