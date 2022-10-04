package ru.job4j.cars.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class HibernateConfig {

    @DependsOn("liquibase")
    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory(BasicDataSource basicDataSource) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.DATASOURCE, basicDataSource)
                .configure()
                .build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Bean
    public BasicDataSource dataSource(@Value("${spring.datasource.driver-class-name}") String className,
                                 @Value("${spring.datasource.url}") String url,
                                 @Value("${spring.datasource.username}") String login,
                                 @Value("${spring.datasource.password}") String password)  {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUrl(url);
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        return dataSource;
    }
}
