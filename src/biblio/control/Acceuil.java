package biblio.control;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import java.awt.Panel;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;

import metier.BiblioException;
import metier.Employe;
import metier.EmpruntEnCours;
import metier.EnumCategorieEmploye;
import metier.EnumStatusExemplaire;
import metier.Exemplaire;
import metier.Utilisateur;
import dao.ConnectionFactory;
import dao.EmpruntEnCoursDAO;
import dao.EmpruntEnCoursDB;
import dao.ExemplaireDAO;
import dao.Trigger;
import dao.UtilisateurDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Acceuil {
	
	private TextArea textArea;
	private Connection cnx ;
	private JFrame frame;
	private JTextField txtId;
	private JPasswordField txtPassword;
	private JPanel panel;
	private JTextField txtRechercher;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil window = new Acceuil();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Acceuil() throws ClassNotFoundException, SQLException, IOException {
		initialize();
		cnx = ConnectionFactory.getConnection("jdbc.properties");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		initAcceuilPanel();
	}
	
	private void initAcceuilPanel(){
		removeAll();
		
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		
		txtId = new JTextField();
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnValide = new JButton("Valide");
		
		btnValide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				Utilisateur utilisateur = null;
			
				try {
					
					utilisateur = retreveUtulisateur(Integer.parseInt(txtId.getText()));
					
				} catch (NumberFormatException | ClassNotFoundException
						| SQLException | IOException e) {
					e.printStackTrace();
					txtId.setText("");
					txtPassword.setText("");
					JOptionPane.showMessageDialog(null, "Utilisateur introuvable");
				}
				
				Employe employe = null;
				
				if(!(utilisateur instanceof Employe)){
					txtId.setText("");
					txtPassword.setText("");
					JOptionPane.showMessageDialog(null, "Identifiez vous comme employé");
				}else{
					employe = (Employe) utilisateur;
					if(employe.getCategorieEmploye() == EnumCategorieEmploye.BIBLIOTHECAIRE){
						if(utilisateur.getPwd().equals(txtPassword.getText())){
							initEmpruntRendrePanel();
						}else{
							txtId.setText("");
							txtPassword.setText("");
							JOptionPane.showMessageDialog(null, "Mot de passe erroné");
						}
					}else if(employe.getCategorieEmploye() == EnumCategorieEmploye.RESPONSABLE){
						if(utilisateur.getPwd().equals(txtPassword.getText())
						&& (employe.getCategorieEmploye() 
								== EnumCategorieEmploye.RESPONSABLE)){
							responsable();
						}else{
							txtId.setText("");
							txtPassword.setText("");
							JOptionPane.showMessageDialog(null, "Mot de passe erroné");
						}
					}else if(employe.getCategorieEmploye() == EnumCategorieEmploye.GESTIONNAIRE_DE_FONDS){
						if(utilisateur.getPwd().equals(txtPassword.getText())
						&& (employe.getCategorieEmploye() 
								== EnumCategorieEmploye.GESTIONNAIRE_DE_FONDS)){
							gestionnaireDeFond();
						}else{
							txtId.setText("");
							txtPassword.setText("");
							JOptionPane.showMessageDialog(null, "Mot de passe erroné");
						}
					}
				}
				
				
			}
		});
		
		panel.add(btnValide);
		
		panel.updateUI();
	}
	
	private void initEmpruntRendrePanel(){
		removeAll();
		
		JButton btnEmprunter = new JButton("Emprunter");
		btnEmprunter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				emprunterLivre();
			}
		});
		panel.add(btnEmprunter);
		
		JButton btnRendre = new JButton("Rendre");
		btnRendre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				retourLivre();
			}
		});
		panel.add(btnRendre);
		
		JButton btnRetour = new JButton("Deconnection");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initAcceuilPanel();
			}
		});
		panel.add(btnRetour);
		
		panel.updateUI();
	}
	
	private void emprunterLivre(){
		removeAll();
		
		JLabel lblId = new JLabel("IDExemplaire");
		panel.add(lblId);
		
		txtRechercher = new JTextField();
		panel.add(txtRechercher);
		txtRechercher.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Exemplaire exemplaire = null;
				try {
					exemplaire = retreveExemplaire(Integer.parseInt(txtRechercher.getText()));
				} catch (NumberFormatException | SQLException e1) {
					txtRechercher.setText("");
					JOptionPane.showMessageDialog(null, "Exemplaire introuvable");
					e1.printStackTrace();
				}
				
				if(exemplaire != null && exemplaire.getStatus() == EnumStatusExemplaire.DISPONIBLE ){
					emprunterUtilisateur(exemplaire);
				}else{
					txtRechercher.setText("");
					JOptionPane.showMessageDialog(null, "Exemplaire non disponible");
				}
			}
		});
		panel.add(btnValider);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initEmpruntRendrePanel();
			}
		});
		panel.add(btnRetour);

		
		panel.updateUI();
	}
	
	private void emprunterUtilisateur(Exemplaire exemplaire){
		removeAll();
		
		final Exemplaire exemplaire2 = exemplaire;
		
		JLabel lblIdutilisateur = new JLabel("IDUtilisateur");
		panel.add(lblIdutilisateur);
		
		txtId = new JTextField();
		panel.add(txtId);
		txtId.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Utilisateur utilisateur = retreveUtulisateur(Integer.parseInt(txtId.getText()));
					if(isConditionsPretAcceptees(utilisateur)){
						
						EmpruntEnCours emprunt = new EmpruntEnCours(new Date(), utilisateur, exemplaire2);
						EmpruntEnCoursDAO empruntDAO = new EmpruntEnCoursDAO(cnx);
						empruntDAO.insertEmpruntEnCours(emprunt);
						initEmpruntRendrePanel();
					}else{
						txtId.setText("");
						JOptionPane.showMessageDialog(null, "L'utilisateur a plus de 3 exemplaire ou est en retard sur un exemplaire");
					}
				} catch (NumberFormatException | ClassNotFoundException
						| SQLException | IOException | BiblioException e1) {
					e1.printStackTrace();
					txtId.setText("");
					JOptionPane.showMessageDialog(null, "Utilisateur introuvable");
				}
			}
		});
		panel.add(btnValider);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				emprunterLivre();
			}
		});
		panel.add(btnRetour);
		
		textArea = new TextArea();
		panel.add(textArea, BorderLayout.CENTER);
		
		
		JButton btnInfo = new JButton("Pret");
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TextArea textArea2 = textArea;
				Utilisateur utilisateur = null;
				try {
					utilisateur = retreveUtulisateur(Integer.parseInt(txtId.getText()));
				} catch (NumberFormatException | ClassNotFoundException
						| SQLException | IOException e1) {
					e1.printStackTrace();
					txtId.setText("");
					JOptionPane.showMessageDialog(null, "Utilisateur introuvable");
				}
				try {
					EmpruntEnCoursDB[] tabEmpruntDB = info(utilisateur);
					textArea.setText(" ");
					
					for(int i = 0; i < tabEmpruntDB.length; i++){
						textArea.setText(textArea.getText() + "\n\n" + tabEmpruntDB[i].toString());
					}
					
				} catch (ClassNotFoundException | SQLException | IOException
						| BiblioException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(btnInfo);
		
		
		
		panel.updateUI();
	}

	private void retourLivre(){
		removeAll();
		
		JLabel lblIdLivre = new JLabel("IDLivre");
		panel.add(lblIdLivre);
		
		txtId = new JTextField();
		panel.add(txtId);
		txtId.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ExemplaireDAO exemplaireDAO = new ExemplaireDAO(cnx);
				Exemplaire exemplaire = null;
				
				try {
					exemplaire = exemplaireDAO.findByKey(Integer.parseInt(txtId.getText()));
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
					txtId.setText("");
					JOptionPane.showMessageDialog(null, "Exemplaire introuvable");
				}
				
				if(exemplaire.getStatus() == EnumStatusExemplaire.PRETE){
					Trigger trigger = new Trigger(cnx);
					
					try {
						trigger.delete(exemplaire);
						initEmpruntRendrePanel();
					} catch (ClassNotFoundException | SQLException
							| IOException e1) {
					
						e1.printStackTrace();
					}
				}else{
					txtId.setText("DEJA DISPONIBLE");
				}
			}
		});
		panel.add(btnValider);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initEmpruntRendrePanel();
			}
		});
		panel.add(btnRetour);
		
		panel.updateUI();
	}
	
	private void responsable(){
		removeAll();
		
		JButton btnEmpruntEnRetard = new JButton("Emprunt En Retard");
		btnEmpruntEnRetard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmpruntEnCoursDAO empruntDAO = new EmpruntEnCoursDAO(cnx);
				try {
					EmpruntEnCoursDB[] empruntRetard = empruntDAO.retreveRetardEmprunt();
					textArea.setText("");
					
					for(int i = 0; i < empruntRetard.length; i++){
						textArea.setText(textArea.getText() + "\n" + empruntRetard[i]);
					}
				} catch (SQLException | BiblioException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnEmpruntEnRetard);
		
		JButton btnEmprunEnCours = new JButton("Emprunt En Cours");
		btnEmprunEnCours.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EmpruntEnCoursDAO empruntDAO = new EmpruntEnCoursDAO(cnx);
				
				try {
					EmpruntEnCoursDB[] emprunt = empruntDAO.findAll();
					textArea.setText("");
					
					for(int i = 0; i < emprunt.length; i++){
						textArea.setText(textArea.getText() + "\n" + emprunt[i]);
					}
				} catch (BiblioException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		panel.add(btnEmprunEnCours);
		
		JButton btnDeconnection = new JButton("Deconnection");
		btnDeconnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initAcceuilPanel();
			}
		});
		panel.add(btnDeconnection);
		
		
		textArea = new TextArea();
		panel.add(textArea, BorderLayout.CENTER);
		
		panel.updateUI();
	}

	private void gestionnaireDeFond(){
		removeAll();
		
		JButton btnCreer = new JButton("Creer Un Livre");
		btnCreer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.add(btnCreer);
		
		JButton btnNew = new JButton("Nouvelle Exemplaire");
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		panel.add(btnNew);
		
		JButton btnDeconnection = new JButton("Deconnection");
		btnDeconnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initAcceuilPanel();
			}
		});
		panel.add(btnDeconnection);
		
		panel.updateUI();
	}
	
	private void removeAll(){
		panel.removeAll();
	}

	private Utilisateur retreveUtulisateur(int id) throws ClassNotFoundException, SQLException, IOException{
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO(cnx); 
		return utilisateurDAO.findByKey(id);
	}

	private Exemplaire retreveExemplaire(int idExemplaire) throws SQLException{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(cnx);
		return exemplaireDAO.findByKey(idExemplaire);
	}

	private boolean isConditionsPretAcceptees(Utilisateur utilisateur) throws ClassNotFoundException, SQLException, IOException, BiblioException{
		EmpruntEnCoursDAO empruntDAO = new EmpruntEnCoursDAO(cnx);
		EmpruntEnCoursDB[] tabEmprunt = empruntDAO.findByUtilisateur(utilisateur.getIdUtilisateur());
		for(int i = 0; i < tabEmprunt.length; i++){
			new EmpruntEnCours(tabEmprunt[i].getDateEmprunt(), utilisateur, tabEmprunt[i].getExmeplaire());
		}
		
		return utilisateur.isConditionsPretAcceptees();
	}

	
	private EmpruntEnCoursDB[] info(Utilisateur utilisateur) throws ClassNotFoundException, SQLException, IOException, BiblioException{
		EmpruntEnCoursDAO empruntDAO = new EmpruntEnCoursDAO(cnx);
		EmpruntEnCoursDB[] tabEmprunt = empruntDAO.findByUtilisateur(utilisateur.getIdUtilisateur());
		
		return tabEmprunt;
	}
}
