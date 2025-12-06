package com.tecsup.aurora_admin.controller;

import com.tecsup.aurora_admin.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/analytics")
@CrossOrigin("*")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/distance/{deviceId}")
    public Map<String, Object> getTotalDistance(@PathVariable Long deviceId) {
        Double distance = analyticsService.calculateTotalDistanceKm(deviceId);
        return Map.of(
            "deviceId", deviceId,
            "totalDistanceKm", distance,
            "unit", "km"
        );
    }
}