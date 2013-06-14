package edu.devry.cis470.tps.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * An application context Java configuration class. The usage of Java
 * configuration requires Spring Framework 3.0 or higher with following
 * exceptions:
 * <ul>
 * <li>@EnableWebMvc annotation requires Spring Framework 3.1</li>
 * </ul>
 * 
 */
@Configuration
@ComponentScan(basePackages = { "edu.devry.cis470.tps" })
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("edu.devry.cis470.tps.repository")
@PropertySource("classpath:application.properties")
public class ApplicationContext {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String PROPERTY_NAME_HIBERNATE_GENERATE_DDL = "hibernate.hbm2ddl.auto";

	// private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME =
	// "message.source.basename";
	// private static final String
	// PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE =
	// "message.source.use.code.as.default.message";

	@Resource
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		final BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
			throws ClassNotFoundException {
		final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory
				.setPackagesToScan(environment
						.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		entityManagerFactory
				.setPersistenceProviderClass(HibernatePersistence.class);

		final Properties jpaProterties = new Properties();
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_GENERATE_DDL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_GENERATE_DDL));

		entityManagerFactory.setJpaProperties(jpaProterties);

		return entityManagerFactory;
	}

	// @Bean
	// public MessageSource messageSource() {
	// final ResourceBundleMessageSource messageSource = new
	// ResourceBundleMessageSource();
	//
	// messageSource.setBasename(environment
	// .getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
	// messageSource
	// .setUseCodeAsDefaultMessage(Boolean.parseBoolean(environment
	// .getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));
	//
	// return messageSource;
	// }

	@Bean
	public JpaTransactionManager transactionManager()
			throws ClassNotFoundException {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());

		return transactionManager;
	}
}