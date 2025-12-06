package com.tecsup.aurora_admin.service;

import com.tecsup.aurora_admin.model.Location;
import com.tecsup.aurora_admin.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final LocationRepository locationRepository;

    public AnalyticsService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    /**
     * Calcula la distancia total recorrida por un dispositivo en Kilómetros.
     * Utiliza la fórmula de Haversine para precisión geoespacial.
     */
    public Double calculateTotalDistanceKm(Long deviceId) {
        // Obtenemos el historial ordenado cronológicamente (del más viejo al más nuevo)
        List<Location> history = locationRepository.findByDeviceIdOrderByTimestampAsc(deviceId);
        
        if (history.size() < 2) return 0.0;

        double totalDistance = 0.0;

        for (int i = 0; i < history.size() - 1; i++) {
            Location point1 = history.get(i);
            Location point2 = history.get(i+1);
            
            totalDistance += haversine(
                point1.getLatitude(), point1.getLongitude(),
                point2.getLatitude(), point2.getLongitude()
            );
        }

        // Redondear a 2 decimales
        return Math.round(totalDistance * 100.0) / 100.0;
    }

    /**
     * Fórmula matemática para calcular la distancia entre dos coordenadas (Lat/Lon).
     * Esto es computacionalmente intensivo si hay miles de puntos, 
     * ideal para Java.
     */
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la tierra en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
                   
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return R * c;
    }
    
    // Aquí podrías agregar métodos futuros como:
    // - calculateAverageSpeed()
    // - detectSuspiciousMovement()
}