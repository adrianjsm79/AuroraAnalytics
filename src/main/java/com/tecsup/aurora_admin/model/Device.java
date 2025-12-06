package com.tecsup.aurora_admin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "devices_device") // Nombre de la tabla en Django
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación Muchos-a-Uno con Usuario
    // @JoinColumn name es el nombre de la columna FK en la tabla (user_id)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "device_identifier", unique = true, length = 255)
    private String deviceIdentifier;

    @Column(name = "is_lost")
    private Boolean isLost;

    private Double latitude;
    private Double longitude;
    private Double accuracy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    // Relación con el historial de ubicaciones
    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Location> locationHistory;
}