package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Exemplaire;

public class InsertExemplaire2 {
	private Connection cnx;
	
	public InsertExemplaire2(Connection cnx){
		this.cnx = cnx;
	}
	
	
	public int insert(Exemplaire exemplaire) throws ClassNotFoundException, SQLException, IOException{		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		
		CallableStatement clbs = cnx.prepareCall(
				"{Call p_new_exemplaire(?,?)}"  
				);
		
		java.sql.Date sqlDate = new java.sql.Date(exemplaire.getDateAchat().getTime());
		
		clbs.setString(1, exemplaire.getISBN());
		clbs.setDate(2, sqlDate);
		
		int nb = clbs.executeUpdate();
		
		clbs.close();
		stmt.close();
		
		return nb;
	}
}
