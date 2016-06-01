package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import metier.Adherent;
import metier.Employe;
import metier.Utilisateur;

public class UtilisateurDao {

	public Utilisateur findByKey(int id) throws SQLException, ClassNotFoundException, IOException{
		Utilisateur utilisateurDao = null;
		
		//Ouvrir la connection
		Connection cnx = ConnectionFactory.getConnectionSansAutoCommit();
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		
		PreparedStatement prst = cnx.prepareCall(
				"select * from Utilisateur where idUtilisateur = ?"  
				);
		
		prst.setInt(1, id);
		
		ResultSet rs = prst.executeQuery();
		
		if(rs.getString("categorieutilisateur").equals("EMPLOYE")){
			utilisateurDao = new Employe(rs.getString("nom"), rs.getString("prenom"),
														rs.getString("sexe"), rs.getDate("datenaissance"));
		
			utilisateurDao.setIdUtilisateur(rs.getInt("idUtilisateur"));
			utilisateurDao.setPseudonyme(rs.getString("pseudonyme"));
			utilisateurDao.setPwd(rs.getString("pwd"));
		}else if(rs.getString("categorieutilisateur").equals("ADHERENT")){
			
			utilisateurDao = new Adherent(rs.getString("nom"), rs.getString("prenom"),
					rs.getString("sexe"), rs.getDate("datenaissance"));
					
			utilisateurDao.setIdUtilisateur(rs.getInt("idUtilisateur"));
			utilisateurDao.setPseudonyme(rs.getString("pseudonyme"));
			utilisateurDao.setPwd(rs.getString("pwd"));
		}		
		
		return utilisateurDao;
	}
}
