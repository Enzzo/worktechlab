package ru.vasilev.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@Profile("dev")
public class HibernateDevConfig {
	@Bean
	 public DataSource dataSource() {
	  DriverManagerDataSource dataSource = new DriverManagerDataSource();
	  dataSource.setUrl("jdbc:h2:mem:libdb");
	  dataSource.setUsername("sa");
	  dataSource.setPassword("");
	  return dataSource;
	 }
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("ru.vasilev.model");
		
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernage.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.format_sql", true);
		
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
}