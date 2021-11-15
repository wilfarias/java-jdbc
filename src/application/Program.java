package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DB.getConnection();
			
			statement = connection.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS); // ? -> place holder
			
			statement.setString(1, "Carl Purple");
			statement.setString(2, "carl@gmail.com");
			statement.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			statement.setDouble(4, 3000);
			statement.setInt(5, 4);
			
			int rowsAffected = statement.executeUpdate(); //retorna o número de linhas alteradas no banco.
			
			if(rowsAffected > 0){
				ResultSet resultSet = statement.getGeneratedKeys(); // resultSet retorna uma tabela
				while(resultSet.next()) {
					int id = resultSet.getInt(1); //apenas a coluna contendo os Ids
					System.out.println("Done! Id = "+ id);
				}
			}else {
				System.out.println("No rows affected");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}

	}

}
