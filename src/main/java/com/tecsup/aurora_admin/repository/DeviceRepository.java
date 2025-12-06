package com.tecsup.aurora_admin.repository;
    
import com.tecsup.aurora_admin.model.Device;
import com.tecsup.aurora_admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    // Buscar por el ID único de hardware
    Optional<Device> findByDeviceIdentifier(String deviceIdentifier);

    // Listar todos los dispositivos de un usuario
    List<Device> findByUser(User user);

    // Listar todos los dispositivos marcados como PERDIDOS (Crítico para soporte)
    List<Device> findByIsLostTrue();

    // Query personalizada: Contar dispositivos por usuario
    @Query("SELECT d.user.email, COUNT(d) FROM Device d GROUP BY d.user.email")
    List<Object[]> countDevicesByUser();
}