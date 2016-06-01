package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.EmpruntEnCours;
import metier.EmpruntEnCoursDB;
import metier.Utilisateur;

public class EmpruntEnCoursDao {

	public int insertEmpruntEnCours(EmpruntEnCours emprunt) throws ClassNotFoundException, SQLException, IOException{
		//Ouvrir la connection
		Connection cnx = ConnectionFactory.getConnectionSansAutoCommit();
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		PreparedStatement prst = cnx.prepareCall(
				"insert into EmpruntEncours (idExemplaire,idUtilisateur,dateEmprunt) values (?,?,?)"  
				);
		
		prst.setInt(1, emprunt.getExmeplaire().getIdExemplaire());
		prst.setInt(2, emprunt.getUtlisateur().getIdUtilisateur());
		
		java.sql.Date sqlDate = new java.sql.Date(emprunt.getDateEmprunt().getTime());
		
		prst.setDate(3, sqlDate);
		
		return prst.executeUpdate();
	}
	
	public EmpruntEnCoursDB findByKey(int id) throws ClassNotFoundException, SQLException, IOException{
		EmpruntEnCoursDB emprunt = null;
		
		//Ouvrir la connection
		Connection cnx = ConnectionFactory.getConnectionSansAutoCommit();
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		PreparedStatement prst = cnx.prepareCall(
				"select * from EmpruntEncours where idExemplaire = ?"  
				);
		
		prst.setInt(1, id);
		
		ResultSet rs = prst.executeQuery();
		
		while(rs.next()){
			emprunt = new EmpruntEnCoursDB(rs.getDate("dateEmprunt"), rs.getInt("idUtilisateur"), rs.getInt("idExemplaire"));
		}
		
		return emprunt;
	}
	
	public EmpruntEnCoursDB[] findByUtilisateur(int id) throws ClassNotFoundException, SQLException, IOException{
		EmpruntEnCoursDB[] emprunt = null;
		int i = 0;
		
		//Ouvrir la connection
		Connection cnx = ConnectionFactory.getConnectionSansAutoCommit();
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		//Init le tableau
		ResultSet rs = stmt.executeQuery("select count(*) from EmpruntEnCours where idUtilisateur = " + id );
		rs.next();		
		emprunt = new EmpruntEnCoursDB[rs.getInt(1)];
		
		
		//Tout les Emprunt de l'utilisateur
		PreparedStatement prst = cnx.prepareCall(
				"select * from EmpruntEncours where idUtilisateur = ?"  
				);
		
		prst.setInt(1, id);
		
		rs = prst.executeQuery();
		
		while(rs.next()){
			emprunt[i] = new EmpruntEnCoursDB(rs.getDate("dateEmprunt"), rs.getInt("idUtilisateur"), rs.getInt("idExemplaire"));
			i++;
		}
		
		return emprunt;
	}
}