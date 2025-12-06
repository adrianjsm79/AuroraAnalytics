package com.tecsup.aurora_admin.repository;

import com.tecsup.aurora_admin.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    // Obtener historial de un dispositivo ordenado por fecha (Más reciente primero)
    List<Location> findByDeviceIdOrderByTimestampDesc(Long deviceId);
    
    // Obtener historial (Más antiguo primero -> Para trazar rutas coherentes)
    List<Location> findByDeviceIdOrderByTimestampAsc(Long deviceId);

    // Obtener solo las últimas N ubicaciones (Paginación para no saturar el mapa)
    Page<Location> findByDeviceId(Long deviceId, Pageable pageable);
}