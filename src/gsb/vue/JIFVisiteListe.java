package gsb.vue;

import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;
import gsb.modele.Medecin;
import gsb.modele.Visite;

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
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class JIFVisiteListe extends JInternalFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JInternalFrame myJInternalFrame;
	private HashMap<String,Visite> diccoVisite;
	
	protected JPanel p;  
	protected JPanel pTexte1;
	protected JPanel pSaisie;
	
	

	
	protected JLabel JLmatricule;
	protected JLabel JLdate;
	protected JLabel JLreference;
	protected JLabel JLmessage;
	
	protected JTextField JTmatricule;
	protected JTextField JTdate;
	protected JTextField JTreference;
	
	
	private JButton Brecherche;
	private JButton Baffiche;
	protected JTable table;
	protected JScrollPane scrollPane;
	protected MenuPrincipal leMenu;
	
	
	public JIFVisiteListe(MenuPrincipal leMenu ,String leMatricule, String laDate) {
		
		
		this.leMenu = leMenu;
		setTitle("Liste des visites");
		
		
    	p = new JPanel();  // panneau principal de la fenêtre
    	
        pTexte1 = new JPanel(new GridLayout(2,3)); //premier panneau (form)
        
        
        JLmatricule= new JLabel("Code Visiteur");
        JLdate= new JLabel("Date");
        JLmessage=new JLabel("");
        
        JTmatricule = new JTextField();
        JTdate = new JTextField();
        
        Brecherche = new JButton("Rechercher Visites");
        
        pTexte1.add(JLmatricule);
        pTexte1.add(JTmatricule);
        pTexte1.add(JLmessage);
        
        pTexte1.add(JLdate);
        
        pTexte1.add(JTdate);
        pTexte1.add(Brecherche);
        Brecherche.addActionListener(this);
        
        p.add(pTexte1); //ajoue form
        
        
        	diccoVisite = new HashMap<String,Visite>();
            diccoVisite = VisiteDao.retournerDictionnaireDesVisitesRecherchees(leMatricule, laDate);
    		int nbLignes= diccoVisite.size();
    		
    		int i=0;
    		String[][] data = new String[nbLignes][3] ;
    		
    		for (Map.Entry<String,Visite> uneEntree : diccoVisite.entrySet()){
    			data[i][0] = uneEntree.getValue().getReference();
    			Medecin leMedecin = uneEntree.getValue().getLeMedecin();
    			data[i][1] = leMedecin.getCodeMed();
    			data[i][2] = leMedecin.getAdresse();
    			i++;
    			}
    		String[] columnNames = {"Référence", "CodeMed", "Lieu"};
    		table = new JTable(data, columnNames);
    		
    		table.getSelectionModel().addListSelectionListener(table);
    		scrollPane = new JScrollPane(table);
    		scrollPane.setPreferredSize(new Dimension(400, 150));
    		p.add(scrollPane); //ajout liste
        
      
		
		pSaisie= new JPanel(); //deuxième panneau
		JLreference = new JLabel("Référence");
		JTreference = new JTextField(20);
		JTreference.setMaximumSize(JTreference.getPreferredSize());
		
		Baffiche = new JButton("Visite Détaillée");
		Baffiche.addActionListener(this);
		pSaisie.add(JLreference);
		pSaisie.add(JTreference);
		pSaisie.add(Baffiche);
		
		p.add(pSaisie); //ajout form affichage
		
		// mise en forme de la fenêtre
			Container contentPane = getContentPane();
			contentPane.add(p);
		
        
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()== Brecherche) {
			try {
				
				leMenu.ouvrirFenetre(new JIFVisiteListe(leMenu, JTmatricule.getText(),JTdate.getText()));
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		else if(e.getSource()==Baffiche) {
			
				if (diccoVisite.containsKey(JTreference.getText())){
					Visite laVisite=VisiteDao.rechercher(JTreference.getText());
					leMenu.ouvrirFenetre(new JIFVisiteDetail(laVisite));
				}
			
		}
	}
	
	
	
}
