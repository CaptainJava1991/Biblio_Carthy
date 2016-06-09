package biblio.control;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import metier.Utilisateur;
import dao.ConnectionFactory;
import dao.UtilisateurDAO;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.TextArea;

public class Control {
	
	private Connection cnx ;
	private JFrame frame;
	private JPanel panel;
	private JTextField txtRechercher;	
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Control window = new Control();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Control() throws ClassNotFoundException, SQLException, IOException {
		initialize();
		cnx = ConnectionFactory.getConnection("jdbc.properties");
		initAcceuilPanel();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JLabel lblIsbn_1 = new JLabel("ISBN");
		panel_1.add(lblIsbn_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre");
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblIdediteur = new JLabel("idEditeur");
		panel_1.add(lblIdediteur);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCodetheme = new JLabel("CodeTheme");
		panel_1.add(lblCodetheme);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAnneeDeParution = new JLabel("Annee De Parution");
		panel_1.add(lblAnneeDeParution);
		
		textField_4 = new JTextField();
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNombreDePage = new JLabel("Nombre De Page");
		panel_1.add(lblNombreDePage);
		
		textField_5 = new JTextField();
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Valider");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_1.add(btnNewButton_2);
		panel_1.add(btnNewButton_1);
		

		

	}
	
	private void initAcceuilPanel(){
		removeAll();
		
	}

	private void initEmpruntRendrePanel(){
		removeAll();
		
		JButton btnEmprunter = new JButton("Emprunter");
		btnEmprunter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Emprunter();
			}
		});

		frame.getContentPane().add(btnEmprunter);
		
		JButton btnRendre = new JButton("Rendre");
		btnRendre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	
		frame.getContentPane().add(btnRendre);
		
	}
	
	private void Emprunter(){
		removeAll();
		
		JLabel lblIsbn = new JLabel("ID");
		frame.getContentPane().add(lblIsbn);
		
		txtRechercher = new JTextField();
		frame.getContentPane().add(txtRechercher);
		txtRechercher.setColumns(10);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Recherche");
		frame.getContentPane().add(lblNewLabel);
	
		panel.updateUI();
	}
	
	private void removeAll(){
		if(frame != null){
			frame.removeAll();
		}
	}

	public Utilisateur retreveUtulisateur(int id) throws ClassNotFoundException, SQLException, IOException{
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO(cnx); 
		return utilisateurDAO.findByKey(id);
	}

}
