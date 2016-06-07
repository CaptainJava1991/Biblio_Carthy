package biblio.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import metier.BiblioException;
import metier.EmpruntEnCours;
import metier.Exemplaire;
import metier.Utilisateur;
import dao.ConnectionFactory;
import dao.EmpruntEnCoursDAO;
import dao.EmpruntEnCoursDB;
import dao.ExemplaireDAO;
import dao.UtilisateurDAO;

public class EmprunterCtl {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, BiblioException{
		String id = JOptionPane.showInputDialog("ID UTILISATEUR");
		Connection cnx = ConnectionFactory.getConnection("jdbc.properties");
		UtilisateurDAO utilisateurDao = new UtilisateurDAO(cnx);
		
		Utilisateur utilisateur = utilisateurDao.findByKey(Integer.parseInt(id));
		
		System.out.println(utilisateur.toString());
		
		EmpruntEnCoursDAO empruntDao = new EmpruntEnCoursDAO(cnx);
		EmpruntEnCoursDB[] empruntEnCoursDB = empruntDao.findByUtilisateur(utilisateur.getIdUtilisateur());
		
		System.out.println("Nombre d'emprunt en cours: " + empruntEnCoursDB.length);
	
		for(int i = 0; i < empruntEnCoursDB.length; i++){
			System.out.println(empruntEnCoursDB[i].toString());
		}
		
		PreparedStatement prst = cnx.prepareStatement("select count(*) from empruntencours where idUtilisateur = " + utilisateur.getIdUtilisateur());
		
		ResultSet rs = prst.executeQuery();
		
		rs.next();
		
		if(rs.getInt(1) < 3){
			id = JOptionPane.showInputDialog("ID EXEMPLAIRE");
			ExemplaireDAO exemplaireDao = new ExemplaireDAO(cnx);
			
			Exemplaire exemplaire = exemplaireDao.findByKey(Integer.parseInt(id));
			
			System.out.println(exemplaire.toString());
			
			if(exemplaire.getStatus().toString().equals("DISPONIBLE")){
				
				EmpruntEnCours newEmprunt = new EmpruntEnCours(new Date(), utilisateur, exemplaire);
				empruntDao.insertEmpruntEnCours(newEmprunt);
				System.out.println(exemplaire.toString());
			}else{
				System.err.println("Exemplaire n'est pas disponible");
			}
			
		}
		
		cnx.close();
	}
}
