package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Adherent;
import metier.Employe;
import metier.Utilisateur;

public class UtilisateurDao implements UtilisateurDAOInterface{
	private Connection cnx;
	
	public UtilisateurDao(Connection cnx){
		this.cnx = cnx;
	}
	
	public Utilisateur findByKey(int id) throws SQLException, ClassNotFoundException, IOException{
		Utilisateur utilisateurDao = null;
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		
		PreparedStatement prst = cnx.prepareCall(
				"select * from Utilisateur where idUtilisateur = ?"  
				);
		
		prst.setInt(1, id);
		
		ResultSet rs = prst.executeQuery();
		rs.next();
		
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
		
		prst.close();
		stmt.close();
		
		return utilisateurDao;
	}
	
	public Utilisateur[] findAll() throws SQLException, ClassNotFoundException, IOException{
		Utilisateur[] utilisateurDao = null;
		int i = 0;		
				
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		//Init le tableau
		ResultSet rs = stmt.executeQuery("select count(*) from Utilisateur");
		rs.next();		
		utilisateurDao = new Utilisateur[rs.getInt(1)];
		
		//Requete AllUtilisateur		
		PreparedStatement prst = cnx.prepareCall(
				"select * from Utilisateur where idUtilisateur"  
				);
		
		rs = prst.executeQuery();
		
		
		while(rs.next()){
		
			if(rs.getString("categorieutilisateur").equals("EMPLOYE")){
				utilisateurDao[i] = new Employe(rs.getString("nom"), rs.getString("prenom"),
															rs.getString("sexe"), rs.getDate("datenaissance"));
			
				utilisateurDao[i].setIdUtilisateur(rs.getInt("idUtilisateur"));
				utilisateurDao[i].setPseudonyme(rs.getString("pseudonyme"));
				utilisateurDao[i].setPwd(rs.getString("pwd"));
			}else if(rs.getString("categorieutilisateur").equals("ADHERENT")){
				
				utilisateurDao[i] = new Adherent(rs.getString("nom"), rs.getString("prenom"),
						rs.getString("sexe"), rs.getDate("datenaissance"));
						
				utilisateurDao[i].setIdUtilisateur(rs.getInt("idUtilisateur"));
				utilisateurDao[i].setPseudonyme(rs.getString("pseudonyme"));
				utilisateurDao[i].setPwd(rs.getString("pwd"));
			}		
			i++;
		
		}
		
		prst.close();
		stmt.close();
		
		return utilisateurDao;	
		
	}
}
