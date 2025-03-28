package ru.vasilev.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateConfig {
	private DataSource dataSource;

	@Autowired
	public HibernateConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("ru.vasilev.model");
		
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernage.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.format_sql", true);
		
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
}