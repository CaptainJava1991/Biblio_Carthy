package biblio.control;

import java.io.IOException;
import java.sql.SQLException;

import metier.Utilisateur;

public interface UtilisateurDAOInterface {

	public Utilisateur findByKey(int id) throws SQLException, ClassNotFoundException, IOException;
	
	public Utilisateur[] findAll() throws SQLException, ClassNotFoundException, IOException;
}
