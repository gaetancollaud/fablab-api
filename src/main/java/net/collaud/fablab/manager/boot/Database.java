package net.collaud.fablab.manager.boot;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * @author Gaetan Collaud
 */
@Configuration
public class Database {

	private static DataSource datasource;

	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource() {
		if (datasource == null) {
			datasource = new DriverManagerDataSource();
		}
		return datasource;
	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
		HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
		factory.setEntityManagerFactory(emf);
		return factory;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
		return converter;
	}

//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan(UserEO.class.getPackage().getName());
//		final Properties hibernateProperties = sessionFactory.getHibernateProperties();
//		hibernateProperties.setProperty("hibernate.format_sql", "true");
//		hibernateProperties.setProperty("format_sql", "true");
//		hibernateProperties.setProperty("hibernate.show_sql", "true");
//		return sessionFactory;
//	}

}


/**
 * <bean id="fablabDataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
 * <property name="driverClassName" value="com.mysql.jdbc.Driver" />
 * <property name="url" value="jdbc:mysql://localhost:3306/fablab" />
 * <property name="username" value="${jdbc.username}" />
 * <property name="password" value="${jdbc.password}" />
 * </bean>
 * <p>
 * <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
 * <property name="dataSource" ref="fablabDataSource" />
 * <property name="packagesToScan" value="net.collaud.fablab.manager.data" />
 * <property name="hibernateProperties">
 * <props>
 * <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
 * <prop key="hibernate.show_sql">true</prop>
 * <prop key="format_sql">true</prop>
 * </props>
 * </property>
 * </bean>
 * <p>
 * <tx:annotation-driven transaction-manager="txManager"/>
 * <bean id="txManager"
 * class="org.springframework.orm.jpa.JpaTransactionManager"
 * p:entityManagerFactory-ref="entityManagerFactory"/>
 * <p>
 * <data:repositories base-package="net.collaud.fablab.manager.dao"
 * entity-manager-factory-ref="entityManagerFactory"
 * transaction-manager-ref="txManager"/>
 * <p>
 * <bean id="entityManagerFactory"
 * class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
 * <property name="packagesToScan" value="net.collaud.fablab.manager.data" />
 * <property name="dataSource" ref="fablabDataSource" />
 * <p>
 * <property name="jpaProperties">
 * <props>
 * <prop key="hibernate.show_sql">true</prop>
 * <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
 * </props>
 * </property>
 * <p>
 * <property name="persistenceProvider">
 * <bean class="org.hibernate.jpa.HibernatePersistenceProvider"></bean>
 * </property>
 * </bean>
 */
