package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection connection = null;

	/* Criar uma conec��o no JDBC � instanciar um objeto do tipo connection */
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				connection = DriverManager.getConnection(url, properties);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return connection;
	}
	
	public static void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	/*
	 * A classe Properties, nativa do java, carrega as configura��es do arquivo
	 * db.properties e trata a exce��o com a exce��o personalizada criada
	 * DbException
	 */
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	/*Classes criadas para tratar a exce��o atrav�s da Classe de exce��o personalizada
	 * tratando como uma RuntimeException, dessa forma, evitando que seja necess�rio
	 * circundar todo fechando de conex�o no programa principal com um try catch*/
	public static void closeStatement(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
