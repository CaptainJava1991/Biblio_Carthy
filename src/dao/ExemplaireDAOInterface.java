package dao;

import java.sql.SQLException;

import metier.Exemplaire;

public interface ExemplaireDAOInterface {

	
	public Exemplaire findByKey(int idExemplaire) throws SQLException;
}
