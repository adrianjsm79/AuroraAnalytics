package com.tecsup.aurora_admin.controller;

import com.tecsup.aurora_admin.service.DeviceService;
import com.tecsup.aurora_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/")
    public String index(Model model) {
        // Si alguna de estas funciones devuelve NULL o falla, la vista explota
        model.addAttribute("totalUsers", userService.countActiveUsers());
        model.addAttribute("totalDevices", deviceService.countTotalDevices());
        model.addAttribute("lostDevicesCount", deviceService.getLostDevices().size()); // <-- OJO AQUÍ
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/devices")
    public String devices(Model model) {
        model.addAttribute("devices", deviceService.getAllDevices());
        return "devices/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}