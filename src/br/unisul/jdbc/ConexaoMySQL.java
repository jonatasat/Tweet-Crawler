package br.unisul.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConexaoMySQL {
 
	/**
	 * Realiza uma conexão com o banco de dados Mysql.
	 */
	public static Connection getConnection(){
		Connection conn = null;
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/tcc?user=root&password=;jatroot";
			conn = DriverManager.getConnection(url);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
}

