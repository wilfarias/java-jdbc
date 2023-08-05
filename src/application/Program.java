package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DataBase;
import database.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			
			connection = DataBase.getConnection();
			ps = connection.prepareStatement(
					"DELETE FROM "
					+"department "
					+ "WHERE "
					+ "id = ?");
			
			/* Ao tentar apagar um department utilizado como FK em outra tabela
			 * será disparada uma exceção de integridade referencial */			
			ps.setDouble(1, 2);			
			
			int rowsAffected = ps.executeUpdate();
			
			System.out.println("Rows affected: "+ rowsAffected);
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

}
