package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Exemplaire;

public class InsertExemplaire1 {
	private Connection cnx;
	
	public InsertExemplaire1(Connection cnx){
		this.cnx = cnx; 
	}

	public int insert(Exemplaire exemplaire) throws ClassNotFoundException, SQLException, IOException{
		//A la cnx, on demande un statement
		PreparedStatement prst = cnx.prepareStatement(
				"insert into EXEMPLAIRE ( DATEACHAT, STATUS, ISBN) values (?,?,?)"  
				);
		
		java.sql.Date sqlDate = new java.sql.Date(exemplaire.getDateAchat().getTime());
		
		prst.setDate(1, sqlDate);
		prst.setString(2, exemplaire.getStatus().toString());
		prst.setString(3, exemplaire.getISBN());
		
		int nb = prst.executeUpdate();
	
		prst.close();
		
		return nb;
	}
}
