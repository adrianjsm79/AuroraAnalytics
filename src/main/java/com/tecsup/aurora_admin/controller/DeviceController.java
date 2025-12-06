package com.tecsup.aurora_admin.controller;

import com.tecsup.aurora_admin.model.Device;
import com.tecsup.aurora_admin.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/devices")
@CrossOrigin("*")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    // Endpoint crítico: Ver dispositivos perdidos
    @GetMapping("/lost")
    public List<Device> getLostDevices() {
        return deviceService.getLostDevices();
    }

    @GetMapping("/search")
    public ResponseEntity<Device> getDeviceByIdentifier(@RequestParam String identifier) {
        return deviceService.findByIdentifier(identifier)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/count")
    public long countDevices() {
        return deviceService.countTotalDevices();
    }
}