package biblio.control;

import java.io.IOException;
import java.sql.SQLException;

import dao.EmpruntEnCoursDB;
import metier.BiblioException;
import metier.EmpruntEnCours;

public interface EmpruntEnCoursDAOInterface {

	public int insertEmpruntEnCours(EmpruntEnCours emprunt) throws ClassNotFoundException, SQLException, IOException;
	
	public EmpruntEnCoursDB findByKey(int id) throws ClassNotFoundException, SQLException, IOException, BiblioException;
	
	public EmpruntEnCoursDB[] findByUtilisateur(int id) throws ClassNotFoundException, SQLException, IOException, BiblioException;
}
