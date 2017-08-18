package servlet;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.protocol.BasicHttpContext;

/**
 * Utilitário para pesquisa do CNPJ direto no site da receita federal
 * @author Gabriel Vieira - gabrielvra@outlook.com
 *
 */
public final class ConsultaCNPJURL {

	private HttpClient client;
	private BasicHttpContext context;
	
	private static ConsultaCNPJURL INSTANTE;
	
	public static final ConsultaCNPJURL get() {
		if (INSTANTE == null) {
			INSTANTE = new ConsultaCNPJURL();
		}
		return INSTANTE;
	}
	
	/**
	 * Inicializa os atributos com dados de uma nova requisição
	 */
	public void init() {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(40 * 10000).build();
		HttpClientBuilder clientBuilder = HttpClientBuilder.create(); 
		clientBuilder.setDefaultRequestConfig(config);
		clientBuilder.setRedirectStrategy(new LaxRedirectStrategy());
		clientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
		BasicCookieStore cookie = new BasicCookieStore();
		context = new BasicHttpContext();  
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookie);
		client = clientBuilder.build();
	}

	public HttpClient getClient() {
		return client;
	}
	
	public BasicHttpContext getContext() {
		return context;
	}
}