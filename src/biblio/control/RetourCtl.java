package biblio.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import metier.BiblioException;
import metier.EmpruntEnCours;
import metier.EnumStatusExemplaire;
import metier.Exemplaire;
import metier.Utilisateur;
import dao.ConnectionFactory;
import dao.EmpruntEnCoursDAO;
import dao.EmpruntEnCoursDB;
import dao.ExemplaireDAO;
import dao.Trigger;
import dao.UtilisateurDAO;

public class RetourCtl {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, BiblioException {
		String id = JOptionPane.showInputDialog("ID EXEMPLAIRE");
		Connection cnx = ConnectionFactory.getConnection("jdbc.properties");
		
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(cnx);
		
		Exemplaire exemplaire = exemplaireDAO.findByKey(Integer.parseInt(id));
		
		if(exemplaire.getStatus() == EnumStatusExemplaire.PRETE){
			Trigger trigger = new Trigger(cnx);
			
			trigger.delete(exemplaire);
			System.err.println("DELETE");
		}
		
		cnx.close();
	}

}
