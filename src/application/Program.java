package application;

import java.sql.Connection;

import database.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = DB.getConnection();
		DB.closeConnection();

	}

}
