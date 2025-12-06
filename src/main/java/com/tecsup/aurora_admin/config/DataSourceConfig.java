package com.tecsup.aurora_admin.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DataSourceConfig {

    // Railway inyecta automáticamente esta variable
    @Value("${DATABASE_URL}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        try {
            // 1. Parseamos la URL de Railway (formato: postgres://user:pass@host:port/db)
            URI uri = new URI(dbUrl);
            
            // 2. Extraemos credenciales
            String username = uri.getUserInfo().split(":")[0];
            String password = uri.getUserInfo().split(":")[1];

            // 3. Construimos la URL JDBC correcta (formato: jdbc:postgresql://host:port/db)
            String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();

            // 4. Configuramos el Pool
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
            config.setDriverClassName("org.postgresql.Driver");
            
        } catch (Exception e) {
            System.err.println("Error parseando DATABASE_URL: " + dbUrl);
            e.printStackTrace();
            return null;
        }

        return new HikariDataSource(config);
    }
}