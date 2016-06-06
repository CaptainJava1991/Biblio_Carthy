package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import biblio.control.ExemplaireDAOInterface;
import metier.EnumStatusExemplaire;
import metier.Exemplaire;



public class ExemplaireDAO implements ExemplaireDAOInterface {

	Connection cnx1 = null;
		
	public ExemplaireDAO(Connection cnx1) {
			this.cnx1=cnx1;
		}

	public Exemplaire findByKey(int idExemplaire) throws SQLException{
		Exemplaire ex = null;
		
		Statement stmt = cnx1.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select idexemplaire,status, dateachat, ISBN "+
				" FROM exemplaire where idexemplaire = " + idExemplaire);			
		
		
		if( rs.next()) {
			int idexemplaire = rs.getInt("idexemplaire");
			String status = rs.getString("status");
			Date dateachat=rs.getDate("dateachat");
			String isbn = rs.getString("ISBN");
			EnumStatusExemplaire enumStatus = EnumStatusExemplaire.valueOf(status);
				
			ex = new Exemplaire( dateachat, isbn);//ici, mapping Objet Relationel
			
			ex.setIdExemplaire(idexemplaire);
			if(status.equals(EnumStatusExemplaire.DISPONIBLE.toString())){
				ex.setStatus(EnumStatusExemplaire.DISPONIBLE);
			}else if(status.equals(EnumStatusExemplaire.PRETE.toString())){
				ex.setStatus(EnumStatusExemplaire.PRETE);
			}else if(status.equals(EnumStatusExemplaire.SUPPRIME.toString())){
				ex.setStatus(EnumStatusExemplaire.SUPPRIME);
			}
			
			
			
		}
		
		stmt.close();
		
		return ex;
			
	}
	
//	public ArrayList<Exemplaire> findall() throws SQLException{
//		Statement stmt1 = cnx1.createStatement();
//		ArrayList <Exemplaire> listeExemplaire= new ArrayList<Exemplaire>();
//		ResultSet rs3 = stmt1.executeQuery("select * FROM exemplaire");			
//		while( rs3.next()){
//			
//			String idexemplaire = rs3.getString(1);
//			Date dateachat=rs3.getDate(2);
//			
//			String status = rs3.getString(3);
//			EnumStatusExemplaire enstex = EnumStatusExemplaire.valueOf(status);
//			
//			//System.out.println("ID Exemplaire : " + idexemplaire +" Status : "+ status );
//			//Livre livre=new Livre();
//			Exemplaire ex = new Exemplaire(idexemplaire,dateachat,enstex,null);//mapping Objet Relationel
//			listeExemplaire.add(ex);
//			
//		}
//		
//		return listeExemplaire;
//	}
	
}
