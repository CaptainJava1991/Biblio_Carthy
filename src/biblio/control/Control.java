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
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblIdutilisateur = new JLabel("Responsable");
		panel_1.add(lblIdutilisateur);
		
		JButton btnValider = new JButton("Emprunt En Retard");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_1.add(btnValider);
		
		JButton btnRetour = new JButton("Emprunt En Cours");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		panel_1.add(btnRetour);
		
		JButton btnNewButton_1 = new JButton("Deconnection");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_1.add(btnNewButton_1);
		
		
		TextArea textArea = new TextArea();
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		

		

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
