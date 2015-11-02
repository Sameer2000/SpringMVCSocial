package com.app.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
public class SocialConfig {

	@Value("${facebook.app.id}")
	private String appId; 
	
	@Value("${facebook.app.secret}")
	private String appSecret; 

	@Value("${application.url}")
	private String applicationUrl; 

	@Autowired
	DataSource dataSource;
	
	public UsersConnectionRepository usersConnectionRepository;
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public Facebook facebook(){
		Connection<Facebook> facebook = connectionRepository().findPrimaryConnection(Facebook.class);
		System.out.println("fb "+facebook);
		if(facebook != null){
			System.out.println(facebook.getApi().openGraphOperations().generalActions().like("tets"));
		}
		return facebook != null ? facebook.getApi() : new FacebookTemplate();
	}
	
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry connectionRegistry = new ConnectionFactoryRegistry();
		connectionRegistry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
		return connectionRegistry;
	}	

	@Bean
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		usersConnectionRepository = new JdbcUsersConnectionRepository(
				dataSource,
				connectionFactoryLocator,
				Encryptors.noOpText()
		);
		return usersConnectionRepository;
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository(){
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null){
			throw new IllegalStateException("Unalbe to get User Repository");
		}*/
		return usersConnectionRepository.createConnectionRepository("sameer");
	}
	
	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository){
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}
	
}
