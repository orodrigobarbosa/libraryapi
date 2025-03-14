package io.github.orodrigobarbosa.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        //pool de conexões
        config.setMaximumPoolSize(10); //máx de conexoes liberadas
        config.setMinimumIdle(1);// tamanho inicial do pool
        config.setPoolName("libraryapi-db-pool");
        config.setMaxLifetime(600000); //tempo máximo de conexão 10min
        config.setConnectionTimeout(100000);// tempo para conseguir uma conexao.. se nao conseguir, dará erro
        config.setConnectionTestQuery("SELECT 1"); //query de teste com o banco

        return new  HikariDataSource(config);
    }
}
