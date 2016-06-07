package biblio.control;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import java.awt.Panel;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Acceuil {
	
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
		Container panell = frame.getContentPane();
		panel = new JPanel();
		frame.getContentPane().add(panel);
		
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
					txtId.setText("ERREUR");
				}
				
				Employe employe = null;
				
				if(!(utilisateur instanceof Employe)){
					txtId.setText("ERRONEE");
				}else{
					employe = (Employe) utilisateur;
					if(!(employe.getCategorieEmploye() == EnumCategorieEmploye.BIBLIOTHECAIRE)){
						txtId.setText("ERRONE");
					}else{
						if(utilisateur.getPwd().equals(txtPassword.getText())
						&& (employe.getCategorieEmploye() 
								== EnumCategorieEmploye.BIBLIOTHECAIRE)){
							initEmpruntRendrePanel();;
						}else{
							txtPassword.setText("ERREUR");
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
				EmprunterLivre();
			}
		});
		panel.add(btnEmprunter);
		
		JButton btnRendre = new JButton("Rendre");
		btnRendre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.add(btnRendre);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initAcceuilPanel();
			}
		});
		panel.add(btnRetour);
		
		panel.updateUI();
	}
	
	private void EmprunterLivre(){
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
					txtRechercher.setText("ERREUR");
					e1.printStackTrace();
				}
				
				if(exemplaire != null && exemplaire.getStatus() == EnumStatusExemplaire.DISPONIBLE ){
					EmprunterUtilisateur(exemplaire);
				}else{
					txtRechercher.setText("NON DISPONIBLE");
				}
			}
		});
		panel.add(btnValider);
		

		
		panel.updateUI();
	}
	
	private void EmprunterUtilisateur(Exemplaire exemplaire){
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
						initAcceuilPanel();
					}
				} catch (NumberFormatException | ClassNotFoundException
						| SQLException | IOException | BiblioException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnValider);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmprunterLivre();
			}
		});
		panel.add(btnRetour);
		
		panel.updateUI();
	}

	
	public void removeAll(){
		panel.removeAll();
	}

	
	
	public Utilisateur retreveUtulisateur(int id) throws ClassNotFoundException, SQLException, IOException{
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO(cnx); 
		return utilisateurDAO.findByKey(id);
	}

	public Exemplaire retreveExemplaire(int idExemplaire) throws SQLException{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(cnx);
		return exemplaireDAO.findByKey(idExemplaire);
	}

	public boolean isConditionsPretAcceptees(Utilisateur utilisateur) throws ClassNotFoundException, SQLException, IOException, BiblioException{
		EmpruntEnCoursDAO empruntDAO = new EmpruntEnCoursDAO(cnx);
		EmpruntEnCoursDB[] tabEmprunt = empruntDAO.findByUtilisateur(utilisateur.getIdUtilisateur());
		for(int i = 0; i < tabEmprunt.length; i++){
			new EmpruntEnCours(tabEmprunt[i].getDateEmprunt(), utilisateur, tabEmprunt[i].getExmeplaire());
		}
		
		return utilisateur.isConditionsPretAcceptees();
	}
	
}
