package dao;

import java.io.IOException;
import java.sql.SQLException;

import metier.BiblioException;
import metier.EmpruntEnCours;
import metier.EmpruntEnCoursDB;

public interface EmpruntEnCoursDAOInterface {

	public int insertEmpruntEnCours(EmpruntEnCours emprunt) throws ClassNotFoundException, SQLException, IOException;
	
	public EmpruntEnCoursDB findByKey(int id) throws ClassNotFoundException, SQLException, IOException, BiblioException;
	
	public EmpruntEnCoursDB[] findByUtilisateur(int id) throws ClassNotFoundException, SQLException, IOException, BiblioException;
}
