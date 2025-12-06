package com.tecsup.aurora_admin.service;

import com.tecsup.aurora_admin.model.Device;
import com.tecsup.aurora_admin.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> getLostDevices() {
        // Asegura que no sea null
        List<Device> list = deviceRepository.findByIsLostTrue();
        return list != null ? list : new ArrayList<>();
    }

    public Optional<Device> findByIdentifier(String identifier) {
        return deviceRepository.findByDeviceIdentifier(identifier);
    }

    public long countTotalDevices() {
        return deviceRepository.count();
    }
}