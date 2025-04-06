package ru.vasilev.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@Profile("dev")
public class HibernateProdConfig {
	private DataSource dataSource;

	@Autowired
	public HibernateProdConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("ru.vasilev.model");
		
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernage.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		hibernateProperties.put("hibernate.show_sql", false);
		hibernateProperties.put("hibernate.format_sql", false);
		
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
}