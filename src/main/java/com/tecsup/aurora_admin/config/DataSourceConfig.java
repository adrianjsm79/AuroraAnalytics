package com.tecsup.aurora_admin.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DataSourceConfig {

    @Value("${DATABASE_URL}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        if (dbUrl == null || dbUrl.isEmpty()) {
            System.err.println("ERROR CRÍTICO: La variable DATABASE_URL está vacía o no existe.");
            return null;
        }

        HikariConfig config = new HikariConfig();

        try {
            // 1. Parseamos la URL de Railway (postgres://...)
            // Reemplazamos temporalmente para que URI lo entienda
            URI uri = new URI(dbUrl.replace("postgres://", "http://")); 
            
            String username = uri.getUserInfo().split(":")[0];
            String password = uri.getUserInfo().split(":")[1];
            
            // 2. Construimos la URL JDBC
            // Añadimos '?sslmode=require' porque Railway/Nube lo suelen exigir
            String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath() + "?sslmode=require";

            // LOG DE DEPURACIÓN (Ocultando contraseña)
            System.out.println("Configurando conexión a: " + jdbcUrl);
            System.out.println("Usuario detectado: " + username);

            // 3. Configuración del Pool (HikariCP)
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
            config.setDriverClassName("org.postgresql.Driver");
            
            // 4. Ajustes de Resiliencia para la Nube (Importante para evitar Timeout)
            config.setConnectionTimeout(30000); // 30 segundos para conectar
            config.setIdleTimeout(600000);      // 10 minutos de inactividad
            config.setMaxLifetime(1800000);     // 30 minutos de vida máxima
            config.setMaximumPoolSize(5);       // Pocas conexiones para no saturar el plan gratuito

        } catch (Exception e) {
            System.err.println("Error grave configurando DataSource: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return new HikariDataSource(config);
    }
}
