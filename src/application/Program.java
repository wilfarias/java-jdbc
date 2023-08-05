package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DataBase;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			
			connection = DataBase.getConnection();
			ps = connection.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ?)");
			
			ps.setDouble(1, 200.0);
			ps.setInt(2, 2);
			
			int rowsAffected = ps.executeUpdate();
			
			System.out.println("Rows affected: "+ rowsAffected);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
