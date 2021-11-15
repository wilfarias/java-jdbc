package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection connection = null;

	/* Criar uma conecção no JDBC é instanciar um objeto do tipo connection */
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
	 * A classe Properties, nativa do java, carrega as configurações do arquivo
	 * db.properties e trata a exceção com a exceção personalizada criada
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

}
