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

    @Value("${DATABASE_URL}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        try {
            // Si estamos en local y no hay DATABASE_URL, no hacemos nada (usará h2 o fallará)
            if (dbUrl == null || dbUrl.isEmpty()) {
                return null; 
            }

            // 1. Parsear la URL de Railway: postgres://user:pass@host:port/db
            // El prefijo 'postgres://' a veces da problemas con URI, lo cambiamos temporalmente a http
            URI uri = new URI(dbUrl.replace("postgres://", "http://"));
            
            String username = uri.getUserInfo().split(":")[0];
            String password = uri.getUserInfo().split(":")[1];

            // 2. Construir la URL JDBC correcta: jdbc:postgresql://host:port/db
            String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();

            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
            config.setDriverClassName("org.postgresql.Driver");
            
            System.out.println("✅ Conexión a BD configurada exitosamente: " + jdbcUrl);

        } catch (Exception e) {
            System.err.println("❌ Error configurando DataSource: " + e.getMessage());
            return null;
        }

        return new HikariDataSource(config);
    }
}
