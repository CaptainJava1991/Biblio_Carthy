package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import biblio.control.EmpruntEnCoursDAOInterface;
import metier.Adherent;
import metier.BiblioException;
import metier.EmpruntEnCours;

public class EmpruntEnCoursDAO implements EmpruntEnCoursDAOInterface {
	private Connection cnx;
	
	public EmpruntEnCoursDAO(Connection cnx){
		this.cnx = cnx;
	}

	public int insertEmpruntEnCours(EmpruntEnCours emprunt) throws ClassNotFoundException, SQLException, IOException{		
		//A la cnx, on demande un statement
		
		PreparedStatement prst = cnx.prepareStatement("Update EXEMPLAIRE set status = 'PRETE' where idExemplaire = " 
														+ emprunt.getExmeplaire().getIdExemplaire());
		
		prst.execute();
		
		prst = cnx.prepareCall(
				"insert into EmpruntEncours (idExemplaire,idUtilisateur,dateEmprunt) values (?,?,?)"  
				);
		
		prst.setInt(1, emprunt.getExmeplaire().getIdExemplaire());
		prst.setInt(2, emprunt.getUtlisateur().getIdUtilisateur());
		
		java.sql.Date sqlDate = new java.sql.Date(emprunt.getDateEmprunt().getTime());
		
		prst.setDate(3, sqlDate);
		
		int rs = prst.executeUpdate();
		
		prst.close();
		
		return rs;
	}
	
	public EmpruntEnCoursDB findByKey(int idExemplaire) throws ClassNotFoundException, SQLException, IOException, BiblioException{
		EmpruntEnCoursDB emprunt = null;
		
		//A la cnx, on demande un statement
		PreparedStatement prst = cnx.prepareCall(
				"select * from EmpruntEncours where idExemplaire = ?"  
				);
		
		prst.setInt(1, idExemplaire);
		
		ResultSet rs = prst.executeQuery();
		
		while(rs.next()){
			emprunt = new EmpruntEnCoursDB(rs.getDate("dateEmprunt"), rs.getInt("idUtilisateur"), rs.getInt("idExemplaire"));
		}
		
		prst.close();
		
		return emprunt;
	}
	
	public EmpruntEnCoursDB[] findByUtilisateur(int id) throws ClassNotFoundException, SQLException, IOException, BiblioException{
		EmpruntEnCoursDB[] emprunt = null;
		int i = 0;
		
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
		
		prst.close();
		stmt.close();
		
		return emprunt;
	}
	
	public EmpruntEnCoursDB[] retreveRetardEmprunt() throws SQLException, BiblioException{
		EmpruntEnCoursDB[] emprunt = null;
		EmpruntEnCoursDB[] empruntRetard = null;
		int i = 0;
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		//Init le tableau
		ResultSet rs = stmt.executeQuery("select count(*) from EmpruntEnCours ");
		rs.next();		
		emprunt = new EmpruntEnCoursDB[rs.getInt(1)];
		empruntRetard = new EmpruntEnCoursDB[rs.getInt(1)];
		
		//Tout les Emprunt de l'utilisateur
		PreparedStatement prst = cnx.prepareCall(
				"select * from EmpruntEncours"  
				);
		
		rs = prst.executeQuery();
		
		while(rs.next()){
			emprunt[i] = new EmpruntEnCoursDB(rs.getDate("dateEmprunt"), rs.getInt("idUtilisateur"), rs.getInt("idExemplaire"));
			if(!Adherent.isPretEnRetard(emprunt[i])){
				empruntRetard[i] = emprunt[i];
			}
			i++;
		}
		
		prst.close();
		stmt.close();
		
		return empruntRetard;
	}
	
	public EmpruntEnCoursDB[] findAll() throws BiblioException, SQLException{
		EmpruntEnCoursDB[] emprunt = null;
		int i = 0;
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		//Init le tableau
		ResultSet rs = stmt.executeQuery("select count(*) from EmpruntEnCours");
		rs.next();		
		emprunt = new EmpruntEnCoursDB[rs.getInt(1)];
		
		
		//Tout les Emprunt de l'utilisateur
		PreparedStatement prst = cnx.prepareCall(
				"select * from EmpruntEncours"  
				);
		

		
		rs = prst.executeQuery();
		
		while(rs.next()){
			emprunt[i] = new EmpruntEnCoursDB(rs.getDate("dateEmprunt"), rs.getInt("idUtilisateur"), rs.getInt("idExemplaire"));
			i++;
		}
		
		prst.close();
		stmt.close();
		
		return emprunt;
	}
}
