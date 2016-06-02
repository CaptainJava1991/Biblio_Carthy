package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Adherent;
import metier.Employe;
import metier.EnumCategorieEmploye;
import metier.Utilisateur;

public class UtilisateurDao implements UtilisateurDAOInterface {
	private Connection cnx;

	public UtilisateurDao(Connection cnx) {
		this.cnx = cnx;
	}

	public Utilisateur findByKey(int id) throws SQLException,
			ClassNotFoundException, IOException {
		Utilisateur utilisateurDAO = null;

		Utilisateur utilisateur = null;
		PreparedStatement prst = cnx.prepareStatement("SELECT * "
				+ "FROM utilisateur u " + "LEFT JOIN adherent a "
				+ "ON a.idutilisateur=u.idutilisateur "
				+ "AND u.categorieutilisateur='ADHERENT' "
				+ "LEFT JOIN employe e "
				+ "ON e.idutilisateur=u.idutilisateur "
				+ "AND u.categorieutilisateur='EMPLOYE' "
				+ "WHERE u.idutilisateur=?");

		prst.setInt(1, id);

		ResultSet rs = prst.executeQuery();

		rs.next();

		if (rs.getString("CATEGORIEUTILISATEUR").equals("EMPLOYE")) {
			Employe employe = new Employe(rs.getString("nom"),
					rs.getString("prenom"), rs.getString("sexe"),
					rs.getDate("datenaissance"));
			employe.setIdUtilisateur(rs.getInt("idUtilisateur"));
			employe.setPseudonyme("pseudonyme");
			employe.setPwd("pwd");
			employe.setCodeMatricule(rs.getString("codeMatricule"));

			if (rs.getString("categorieEmploye").equals(
					EnumCategorieEmploye.BIBLIOTHECAIRE.toString())) {
				employe.setCategorieEmploye(EnumCategorieEmploye.BIBLIOTHECAIRE);
			} else if (rs.getString("categorieEmploye").equals(
					EnumCategorieEmploye.GESTIONNAIRE_DE_FONDS.toString())) {
				employe.setCategorieEmploye(EnumCategorieEmploye.GESTIONNAIRE_DE_FONDS);
			} else if (rs.getString("categorieEmploye").equals(
					EnumCategorieEmploye.RESPONSABLE.toString())) {
				employe.setCategorieEmploye(EnumCategorieEmploye.RESPONSABLE);
			}

			return employe;
		} else if (rs.getString("CATEGORIEUTILISATEUR").equals("ADHERENT")) {
			Adherent adherent = new Adherent(rs.getString("nom"),
					rs.getString("prenom"), rs.getString("sexe"),
					rs.getDate("datenaissance"), rs.getString("telephone"));
			adherent.setIdUtilisateur(rs.getInt("idUtilisateur"));
			adherent.setPseudonyme("pseudonyme");
			adherent.setPwd("pwd");

			return adherent;
		}

		prst.close();

		return null;
	}

	public Utilisateur[] findAll() throws SQLException, ClassNotFoundException,
			IOException {
		Utilisateur[] utilisateurDao = null;
		int i = 0;

		// A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();

		// Init le tableau
		ResultSet rs = stmt.executeQuery("select count(*) from Utilisateur");
		rs.next();
		utilisateurDao = new Utilisateur[rs.getInt(1)];

		// Requete AllUtilisateur
		PreparedStatement prst = cnx
				.prepareCall("SELECT * "
						+ "FROM utilisateur u " + "LEFT JOIN adherent a "
						+ "ON a.idutilisateur=u.idutilisateur "
						+ "AND u.categorieutilisateur='ADHERENT' "
						+ "LEFT JOIN employe e "
						+ "ON e.idutilisateur=u.idutilisateur "
						+ "AND u.categorieutilisateur='EMPLOYE'");

		rs = prst.executeQuery();

		while (rs.next()) {

			if (rs.getString("CATEGORIEUTILISATEUR").equals("EMPLOYE")) {
				Employe employe = new Employe(rs.getString("nom"),
						rs.getString("prenom"), rs.getString("sexe"),
						rs.getDate("datenaissance"));
				employe.setIdUtilisateur(rs.getInt("idUtilisateur"));
				employe.setPseudonyme("pseudonyme");
				employe.setPwd("pwd");
				employe.setCodeMatricule(rs.getString("codeMatricule"));

				if (rs.getString("categorieEmploye").equals(
						EnumCategorieEmploye.BIBLIOTHECAIRE.toString())) {
					employe.setCategorieEmploye(EnumCategorieEmploye.BIBLIOTHECAIRE);
				} else if (rs.getString("categorieEmploye").equals(
						EnumCategorieEmploye.GESTIONNAIRE_DE_FONDS.toString())) {
					employe.setCategorieEmploye(EnumCategorieEmploye.GESTIONNAIRE_DE_FONDS);
				} else if (rs.getString("categorieEmploye").equals(
						EnumCategorieEmploye.RESPONSABLE.toString())) {
					employe.setCategorieEmploye(EnumCategorieEmploye.RESPONSABLE);
				}

				utilisateurDao[i] = employe;
			} else if (rs.getString("CATEGORIEUTILISATEUR").equals("ADHERENT")) {
				Adherent adherent = new Adherent(rs.getString("nom"),
						rs.getString("prenom"), rs.getString("sexe"),
						rs.getDate("datenaissance"),
						rs.getString("telephone"));
				adherent.setIdUtilisateur(rs.getInt("idUtilisateur"));
				adherent.setPseudonyme("pseudonyme");
				adherent.setPwd("pwd");

				utilisateurDao[i] = adherent;
			}
			i++;

		}

		prst.close();
		stmt.close();

		return utilisateurDao;

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		Connection cnx = ConnectionFactory.getConnection();
		UtilisateurDao utilisateurDao = new UtilisateurDao(cnx);
		Utilisateur[] utilisateurTab = utilisateurDao.findAll();
		
		for(Utilisateur u : utilisateurTab){
			System.out.println(u);
		}
		
	}
}
