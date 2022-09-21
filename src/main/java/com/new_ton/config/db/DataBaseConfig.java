package com.new_ton.config.db;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Slf4j
@Configuration
@EnableJpaRepositories(basePackages = "com.new_ton.repository", entityManagerFactoryRef = "lider_entity_manager",
        transactionManagerRef = "liderTransactionManager")
public class DataBaseConfig {

    @Value("${lider.db.jdbc-url}")
    private String dbURL;

    @Value("${lider.db.driver}")
    private String driver;

    @Value("${lider.db.username}")
    private String username;

    @Value("${lider.db.password}")
    private String password;

    @Value("${hikari.connection.pool}")
    private int connectionPool;


    @Autowired
    private Environment env;


    @Primary
    @Bean()
    public DataSource getDriverManagerDatasource() {
        log.info("-------- Lider Hikari started connection pool : " + connectionPool);
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setMaximumPoolSize(connectionPool);
        hikariDataSource.setJdbcUrl(dbURL);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setDriverClassName(driver);
        return hikariDataSource;
    }

    @Primary
    @Bean( name = "lider_entity_manager")
    public LocalContainerEntityManagerFactoryBean productEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDriverManagerDatasource());
        em.setPackagesToScan(
                new String[]{"com.new_ton.domain.entities"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect",
                env.getProperty("hibernatedialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager liderTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                productEntityManager().getObject());
        return transactionManager;
    }
}
