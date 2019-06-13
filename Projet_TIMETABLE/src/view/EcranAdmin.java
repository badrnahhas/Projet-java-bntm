package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import timeTableController.TimeTableController;
import userController.UserController;

public class EcranAdmin extends JFrame{
  JLabel lblTitreAdminGroupe = new JLabel("Gérer les groupes :");
  JLabel lblTitreAdminSalle = new JLabel("Gérer les salles :");
  JLabel lblTitreAdminUtil = new JLabel("Gérer les utilisateurs :");
  private JPanel pan4 = new JPanel();
  private JButton boutonVoirGroupe = new JButton("Voir les groupes");
  private JButton boutonAjouterGroupe = new JButton("Ajouter un groupe");
  private JButton boutonSupprGroupe = new JButton("Supprimer un groupe");
  private JButton boutonAssocierEtu = new JButton("Associer un étudiant à un groupe");
  private JButton boutonVoirSalle = new JButton("Voir les salles");
  private JButton boutonAjouterSalle = new JButton("Ajouter une salle");
  private JButton boutonSupprSalle = new JButton("Supprimer une salle");
  private JButton boutonVoirUtil = new JButton("Voir les utilisateurs");
  private JButton boutonAjouterEtu = new JButton("Ajouter un étudiant");
  private JButton boutonAjouterProf = new JButton("Ajouter un professeur");
  private JButton boutonAjouterAdmin = new JButton("Ajouter un Administrateur");
  private JButton boutonSupprUtil = new JButton("Supprimer un utilisateur");
  private JButton boutonLogout = new JButton("Déconnexion");
  
  public EcranAdmin(String stringLoginArg, String stringPasswordArg, UserController userController, TimeTableController tTController){
	JLabel lblTitreAdmin = new JLabel("ADMIN :"+stringLoginArg);
	pan4.setLayout(null);
    this.setTitle("EcranAdmin");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    lblTitreAdmin.setBounds(450, 0, 100, 10);
    lblTitreAdminGroupe.setBounds(75, 25, 75, 10);
    lblTitreAdminSalle.setBounds(375, 25, 75, 10);
    lblTitreAdminUtil.setBounds(675, 25, 75, 10);
    boutonVoirGroupe.setBounds(75, 50, 125, 25);
    boutonAjouterGroupe.setBounds(75, 100, 125, 25);
    boutonSupprGroupe.setBounds(75, 150, 125, 25);
    boutonAssocierEtu.setBounds(75, 200, 125, 25);
    boutonVoirSalle.setBounds(375, 50, 125, 25);
    boutonAjouterSalle.setBounds(375, 100, 125, 25);
    boutonSupprSalle.setBounds(375, 150, 125, 25);
    boutonVoirUtil.setBounds(675, 50, 125, 25);
    boutonAjouterEtu.setBounds(675, 100, 125, 25);
    boutonAjouterProf.setBounds(675, 150, 125, 25);
    boutonAjouterAdmin.setBounds(675, 200, 125, 25);
    boutonSupprUtil.setBounds(675, 250, 125, 25);
    boutonLogout.setBounds(675, 400, 125, 25);
    pan4.add(lblTitreAdmin);
    pan4.add(lblTitreAdminGroupe);
    pan4.add(lblTitreAdminSalle);
    pan4.add(lblTitreAdminUtil);
    pan4.add(boutonVoirGroupe);
    pan4.add(boutonAjouterGroupe);
    pan4.add(boutonSupprGroupe);
    pan4.add(boutonAssocierEtu);
    pan4.add(boutonVoirSalle);
    pan4.add(boutonSupprSalle);
    pan4.add(boutonAjouterSalle);
    pan4.add(boutonVoirUtil);
    pan4.add(boutonAjouterEtu);
    pan4.add(boutonAjouterProf);
    pan4.add(boutonAjouterAdmin);
    pan4.add(boutonSupprUtil);
    pan4.add(boutonLogout);
    
    this.setContentPane(pan4);
    this.setVisible(false);
    
    boutonVoirGroupe.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranVoirGroupe ecranVoirGroupe = new EcranVoirGroupe(userController);
        	ecranVoirGroupe.setVisible(true);
        	}
    	});
    
    boutonAjouterGroupe.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranAjouterGroupe ecranAjouterGroupe = new EcranAjouterGroupe(stringLoginArg, userController, tTController);
        	ecranAjouterGroupe.setVisible(true);
        	}
    	});
    
    boutonSupprGroupe.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranSupprGroupe ecranSupprGroupe = new EcranSupprGroupe(stringLoginArg, userController);
        	ecranSupprGroupe.setVisible(true);
        	}
    	});
    
    boutonAssocierEtu.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranAssocierEtu ecranAssocierEtu = new EcranAssocierEtu(stringLoginArg, userController);
        	ecranAssocierEtu.setVisible(true);
        	}
    	});
    
    boutonVoirSalle.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranVoirSalle ecranVoirSalle = new EcranVoirSalle(tTController);
        	ecranVoirSalle.setVisible(true);
        	}
    	});
    
    boutonSupprSalle.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranSupprSalle ecranSupprSalle = new EcranSupprSalle(tTController);
        	ecranSupprSalle.setVisible(true);
        	}
    	});
    
    boutonAjouterSalle.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranAjouterSalle ecranAjouterSalle = new EcranAjouterSalle(stringLoginArg, tTController);
        	ecranAjouterSalle.setVisible(true);
        	}
    	});
    
    boutonVoirUtil.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranVoirUtil ecranVoirUtil = new EcranVoirUtil(userController);
        	ecranVoirUtil.setVisible(true);
        	}
    	});
    
    boutonAjouterEtu.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranAjouterEtu ecranAjouterEtu = new EcranAjouterEtu(stringLoginArg, userController);
        	ecranAjouterEtu.setVisible(true);
        	}
    	});
    
    boutonAjouterProf.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranAjouterProf ecranAjouterProf = new EcranAjouterProf(stringLoginArg, userController);
        	ecranAjouterProf.setVisible(true);
        	}
    	});
    
    boutonAjouterAdmin.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranAjouterAdmin ecranAjouterAdmin = new EcranAjouterAdmin(stringLoginArg, userController);
        	ecranAjouterAdmin.setVisible(true);
        	}
    	});
    
    boutonSupprUtil.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	EcranSupprUtil ecranSupprUtil = new EcranSupprUtil(stringLoginArg, userController);
        	ecranSupprUtil.setVisible(true);
        	}
    	});
    
    boutonLogout.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	MainFrame mainframe = new MainFrame(userController, tTController);
        	dispose();
        	mainframe.setVisible(true);
        	}
	});
  }       
}