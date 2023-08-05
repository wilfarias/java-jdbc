package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DataBase;
import database.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement ps = null;
		
		try {
			
			connection = DataBase.getConnection();
			
			/* Transação só será totalmente finalizada após a confirmação do commit manual*/
			connection.setAutoCommit(false);
			
			ps = connection.createStatement();
			
			int rows1 = ps.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			/*int x = 1;
			if (x < 2) {
				throw new SQLException("Fake error");
			}*/
			
			int rows2 = ps.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			/* Confirmação manual da transação */
			connection.commit();
			
			System.out.println("rows1 = "+ rows1);
			System.out.println("rows2 = "+ rows2);
			
		} catch (SQLException e) {
			try {
				connection.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
				
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
			
		} finally {
			DataBase.closeStatement(ps);
			DataBase.closeConnection();
		}
	}
}
