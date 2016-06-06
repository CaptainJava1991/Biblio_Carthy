package biblio.control;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import java.awt.Panel;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;

import metier.Utilisateur;
import dao.ConnectionFactory;
import dao.UtilisateurDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Acceuil {
	
	Connection cnx ;
	private JFrame frame;
	private JTextField txtId;
	private JTextField txtPassword;

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
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		initAcceuilPanel(panel);

	}
	
	public void initAcceuilPanel(JPanel panel){
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		
		txtId = new JTextField();
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);
		
		txtPassword = new JTextField();
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
				
				if(utilisateur.getPwd().equals(txtPassword.getText())){
					Menu.main(null);
				}else{
					txtPassword.setText("ERREUR");
				}
			}
		});
		
		panel.add(btnValide);
	}
	
	public void uninitAcceuilPanel(JPanel){
		panel.add(btnValide);
	}

	
	public Utilisateur retreveUtulisateur(int id) throws ClassNotFoundException, SQLException, IOException{
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO(cnx); 
		return utilisateurDAO.findByKey(id);
	}

}
