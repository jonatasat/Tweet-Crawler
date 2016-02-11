package br.unisul.classes;

import java.util.Date;
import java.util.List;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import br.unisul.DAO.PalavraChaveDAO;
import br.unisul.DAO.TweetDAO;
import br.unisul.javabean.PalavraChave;
import br.unisul.javabean.Tweet;

public class TweetStream {

	static private TwitterStream twitterStream;
	static private StatusListener listener;

	public static void  stopStream(){
		twitterStream.shutdown();
	}

	public static void startStream(){
		String consumerKey = "Lqu10HD5NL1AxdbCXVohzHD86";
		String consumerSecret = "UqaTvKuUV2s8zC6RLUF457i9cFY65Rdoqpxe5Fn8pJ9cKVBICD";
		String twitterToken = "1522548828-iClO69dfYWD0FsW75CDyauSUhtJG9x2Y9J8DXoY";
		String twitterTokenSecret = "k9kmo7UxSCTdE8ELbFOkEDIPSFqEh0bGvfjqYdNoxVdP8";


		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		cb.setOAuthAccessToken(twitterToken);
		cb.setOAuthAccessTokenSecret(twitterTokenSecret);

		twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

		listener = new StatusListener(){

			@Override
			public void onStatus(Status status) {
				User user = status.getUser();

				Tweet t = new Tweet();


				Date data = status.getCreatedAt();
				t.setDataPublicacao(data);
				System.out.println(data);


				String username = status.getUser().getScreenName();
				t.setUsuario(username);
				System.out.println(username);

				String profileLocation = user.getLocation();
				t.setLocalizacao(profileLocation);
				System.out.println(profileLocation);

				long tweetId = status.getId();
				t.setTweetId(tweetId);
				System.out.println(tweetId);


				String content = status.getText();
				t.setConteudo(content);
				System.out.println(content +"\n");
				
				

				try{
					TweetDAO.salvar(t);
					GerenciaTweet.listarTweets(tweetId);
					GerenciaEntidade.listarTweets(tweetId);
				}catch (Exception e){
					e.printStackTrace();
				}
			}

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub

			}
		};


		try{

			FilterQuery fq = new FilterQuery();
			
			List<PalavraChave> list = PalavraChaveDAO.listar();
			String[] lista = new String[list.size()];
			int i=0;
					
			for(PalavraChave l: list){
				lista[i] = l.getPalavra();
				i++;
			}
			
			fq.track(lista);
			
			twitterStream.addListener(listener);
			twitterStream.filter(fq);


		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
