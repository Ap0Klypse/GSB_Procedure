package gsb.vue;

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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteDao;


public class JIFVisiteAjout extends JInternalFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBouton1;
	protected JPanel pBouton2;
	protected JPanel pConfiance;
	protected JPanel pMessage;
	

	protected JLabel JLref;
	protected JLabel JLdate;
	protected JLabel JLcomm;
	protected JLabel JLmatricule;
	protected JLabel JLcodeMed;
	protected JLabel JLconfiance;
	protected JLabel JLmessage;
	
	
	protected JTextField JTref;
	protected JTextField JTdate;
	protected JTextField JTcomm;
	protected JTextField JTmatricule;
	protected JTextField JTcodeMed;
	protected JTextField JTconfiance;

	private JButton ajouter;
	private JButton confiance;
	
	
	 public JIFVisiteAjout() {
		 	setTitle("Ajout Visite");
	    	p = new JPanel();  // panneau principal de la fenï¿½tre
	        pBouton1 = new JPanel(); 
	        // panneau supportant le bouton
	        pBouton2 = new JPanel();
	        pTexte = new JPanel(new GridLayout(5,2));
	        pConfiance = new JPanel(new GridLayout(1,2));
	        pMessage = new JPanel();
	        
	        JLref= new JLabel("Référence");
	        JLdate= new JLabel("Date visite");
	        JLcomm= new JLabel("Commentaire");
	        JLmatricule= new JLabel("Matricule visiteur");
	        JLcodeMed= new JLabel("Code médecin");
	        JLconfiance = new JLabel("Nouvelle confiance");
	        JLmessage= new JLabel("");
	        
	        JTref = new JTextField(20);
	        JTref.setMaximumSize(JTref.getPreferredSize());
	        JTdate = new JTextField();
	        JTcomm = new JTextField();
	        JTmatricule = new JTextField();
	        JTcodeMed = new JTextField();
	        
	        JTconfiance = new JTextField(20);
	        
	        ajouter = new JButton("Ajouter Visite");
	        confiance = new JButton("Mettre à jour confiance");
	        
	        
	        pTexte.add(JLref);
	        pTexte.add(JTref);
	        pTexte.add(JLdate);
	        pTexte.add(JTdate);
	        pTexte.add(JLcomm);
	        pTexte.add(JTcomm);
	        pTexte.add(JLmatricule);
	        pTexte.add(JTmatricule);
	        pTexte.add(JLcodeMed);
	        pTexte.add(JTcodeMed);
	        
	        pBouton1.add(ajouter);
	        ajouter.addActionListener(this);
	        // mise en forme de la fenï¿½tre
	        pConfiance.add(JLconfiance);
	        pConfiance.add(JTconfiance);
	        
	        pBouton2.add(confiance);
	        confiance.addActionListener(this);
	        
	        pMessage.add(JLmessage);
	        
	        p.add(pTexte);
	        p.add(pBouton1);
	        p.add(pConfiance);
	        p.add(pBouton2);
	        p.add(pMessage);
	        Container contentPane = getContentPane();
	        contentPane.add(p);
	 }
	 
	 public void actionPerformed(ActionEvent evt) {
		 if (evt.getSource() ==ajouter) {
			 try {
				 VisiteDao.creer(JTref.getText(), JTdate.getText(), JTcomm.getText(), JTmatricule.getText(), JTcodeMed.getText());
				 JTref.setText("");
				 JTdate.setText("");
				 JTcomm.setText("");
				 JTmatricule.setText("");
				 JTcodeMed.setText("");
				 JLmessage.setText("Visite ajoutée !");
				 
			 } catch (Exception e1) {
				 e1.printStackTrace();
			 }
		 }
		 else if(evt.getSource() ==confiance ) {
			 
			 try {
				 MedecinDao.majConfiance(JTcodeMed.getText(), Integer.valueOf(JTconfiance.getText()));
				 JLmessage.setText("Confiance mise à jour");
				 JTconfiance.setText("");
			 }
			 catch(Exception e){
				 
			 }
		 }
	 }
}
