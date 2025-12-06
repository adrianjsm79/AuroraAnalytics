package com.tecsup.aurora_admin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users_user") // Nombre exacto de la tabla en PostgreSQL
@Data // Genera Getters, Setters, toString, etc. (Lombok)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // Hash de Django (PBKDF2...)

    @Column(length = 150)
    private String nombre;

    @Column(length = 17, unique = true)
    private String numero;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_staff")
    private Boolean isStaff; // Importante para saber si es admin

    @Column(name = "is_superuser")
    private Boolean isSuperuser;

    @Column(name = "date_joined")
    private LocalDateTime dateJoined;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    // Campos de ubicación del navegador (Agregados recientemente)
    @Column(name = "browser_latitude")
    private Double browserLatitude;

    @Column(name = "browser_longitude")
    private Double browserLongitude;

    @Column(name = "browser_last_seen")
    private LocalDateTime browserLastSeen;

    @Column(name = "image")
    private String imagePath; // Ruta relativa de la imagen (ej: profile_pics/foto.jpg)

    // Relación: Un usuario tiene muchos dispositivos
    // mappedBy debe coincidir con el nombre del campo 'user' en la clase Device
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Device> devices;
    
    // Método helper para construir la URL completa de la imagen si es necesario
    public String getFullImageUrl(String baseUrl) {
        if (imagePath != null && !imagePath.isEmpty()) {
            return baseUrl + "/media/" + imagePath;
        }
        return null;
    }
}