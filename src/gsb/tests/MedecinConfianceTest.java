package gsb.tests;

import gsb.modele.dao.ConnexionMySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.CallableStatement;

public class MedecinConfianceTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		 ConnexionMySql.execProcedure("EXEC maj_confiance 'm002', 80");
	}
}
