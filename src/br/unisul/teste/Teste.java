package br.unisul.teste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.unisul.DAO.TweetDAO;
import br.unisul.jdbc.ConexaoMySQL;

public class Teste {
	public static void main(String [] args){

		Connection conn = ConexaoMySQL.getConnection();
		long count = TweetDAO.qtdTweets();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int limit =2;
		int offset = 0;

		try {
			while(offset<count){
				ps = conn.prepareStatement("SELECT TWEETID, CONTEUDO FROM TWEET limit "+limit+" offset "+offset);
				rs = ps.executeQuery();

				while(rs.next()) {
					BigDecimal tweetid = rs.getBigDecimal(1);
					String tweet = rs.getString(2);
					System.out.println("Tweetid: "+tweetid+"\n"+
					"Tweet: "+tweet+"\n"+"Offset: "+offset);
				}
				
				offset = offset + limit;
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
				conn.close();
			}catch(Exception e){}
		}
	}
}
