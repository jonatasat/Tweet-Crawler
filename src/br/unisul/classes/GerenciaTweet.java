package br.unisul.classes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import br.unisul.javabean.*;
import br.unisul.DAO.*;
import br.unisul.jdbc.ConexaoMySQL;

public class GerenciaTweet {


	public static void listarTweets(long tid) {

		Connection conn = ConexaoMySQL.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT TWEETID, CONTEUDO FROM TWEET WHERE TWEETID = "+ tid);
			rs = ps.executeQuery();

			while(rs.next()) {
				BigDecimal tweetid = rs.getBigDecimal(1);
				String tweet = rs.getString(2);
				processaTweet(conn, tweetid, tweet);
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
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e){}
		}
	}

	private static void processaTweet(Connection conn, BigDecimal tweetid, String tweet) {

		if(conn != null && tweet != null) {

			if(tweet != null) {

				String[] palavras = tweet.split(" ");

				List<PalavraChave> listP = PalavraChaveDAO.listar();

				for (PalavraChave p: listP){
					for(int i = 0; i < palavras.length; i++) {
						if(palavras[i].equalsIgnoreCase(p.getPalavra())){
							PreparedStatement insert = null;
							String sql = "INSERT INTO EST_TERMOS(TWEETID, ID_TERMO) values (?,?)";

							try {

								insert =  conn.prepareStatement(sql);
								insert.setBigDecimal(1,tweetid);
								insert.setFloat(2,p.getId());
								insert.executeUpdate();

							} catch (SQLException e) {
								e.printStackTrace();
							}finally{
								if(insert != null) {
									try {
										insert.close();
									}catch(Exception ex){}
								}

								System.out.println("Relacionamento termo realizado com sucesso - Tweetid: "+tweetid);
							}
						}
					}
				}
			}
		}
	}
}