package ru.job4j.cars;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.model.User;
import ru.job4j.cars.util.SessionUtil;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@Controller
@PropertySource("datasource.properties")
public class CarsStarter {

    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory(BasicDataSource basicDataSource) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.DATASOURCE, basicDataSource)
                .configure()
                .build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Bean(destroyMethod = "close")
    public BasicDataSource basicDataSource(@Value("${jdbc.driver}") String className,
                                           @Value("${jdbc.url}") String url,
                                           @Value("${jdbc.username}") String login,
                                           @Value("${jdbc.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUrl(url);
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        return dataSource;
    }

    @GetMapping("/")
    String home(Model model, HttpSession session) {
        return "redirect:/loginPage";
    }

    public static void main(String[] args) {
        SpringApplication.run(CarsStarter.class, args);
    }
}
