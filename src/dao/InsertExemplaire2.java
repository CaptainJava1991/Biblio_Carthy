package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Exemplaire;
import ping.PingJdbc;

public class InsertExemplaire2 {
	
	public int insert(Exemplaire exemplaire) throws ClassNotFoundException, SQLException, IOException{
		//Ouvrir la connection
		Connection cnx = PingJdbc.getIntance();
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		
		CallableStatement prst = cnx.prepareCall(
				"{Call p_new_exemplaire(?,?)}"  
				);
		
		java.sql.Date sqlDate = new java.sql.Date(exemplaire.getDateAchat().getTime());
		
		prst.setString(1, exemplaire.getISBN());
		prst.setDate(2, sqlDate);
		
		cnx.commit();
		
		prst.close();
		stmt.close();
		cnx.close();
		
		return 1;
	}
}
