package br.unisul.classes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.unisul.javabean.*;
import br.unisul.DAO.*;
import br.unisul.jdbc.ConexaoMySQL;

public class RelacionaTermoFull {
	
	
	public static long listarTweets() {
		Connection conn = ConexaoMySQL.getConnection();
		long count = TweetDAO.qtdTweets();
		long contador = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int limit = 1000;
		int offset = 0;

		try {
			while(offset<count){

				if(conn.isClosed()==true){
					conn = ConexaoMySQL.getConnection();
				}
				
				ps = conn.prepareStatement("SELECT TWEETID, CONTEUDO FROM TWEET limit "+limit+" offset "+offset);
				rs = ps.executeQuery();

				while(rs.next()) {
					BigDecimal tweetid = rs.getBigDecimal(1);
					String tweet = rs.getString(2);
					processaTweet(tweetid, tweet);
					contador++;
				}
				offset = offset + limit;
				conn.close();
			}
		} catch(SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
			}catch(Exception e){}
		}
		return contador;
	}

	private static void processaTweet(BigDecimal tweetid, String tweet) {

		Connection conn = ConexaoMySQL.getConnection();

		if(tweet != null) {

			String[] palavras = tweet.split(" ");

			List<PalavraChave> listP = PalavraChaveDAO.listar();

			for (PalavraChave p: listP){
				for(int i = 0; i < palavras.length; i++) {
					if(palavras[i].equalsIgnoreCase(p.getPalavra())){

						String sql = "INSERT INTO EST_TERMOS(TWEETID, ID_TERMO) values (?,?)";

						try {

							PreparedStatement insert =  conn.prepareStatement(sql);
							insert.setBigDecimal(1,tweetid);
							insert.setFloat(2,p.getId());
							insert.executeUpdate();

						} catch (SQLException e) {
							e.printStackTrace();
						}finally{
							System.out.println("Cadastro realizado com sucesso");
						}
					}
				}
			}
		}
	}
}

