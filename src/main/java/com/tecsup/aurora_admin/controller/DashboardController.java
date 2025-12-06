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
        model.addAttribute("totalUsers", userService.countActiveUsers());
        model.addAttribute("totalDevices", deviceService.countTotalDevices());
        model.addAttribute("lostDevicesCount", deviceService.getLostDevices().size());
        
        // --- SOLUCIÓN: Le decimos a la vista quién es ---
        model.addAttribute("activePage", "home");
        
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        
        // --- SOLUCIÓN ---
        model.addAttribute("activePage", "users");
        
        return "users/list";
    }

    @GetMapping("/devices")
    public String devices(Model model) {
        model.addAttribute("devices", deviceService.getAllDevices());
        
        // --- SOLUCIÓN ---
        model.addAttribute("activePage", "devices");
        
        return "devices/list";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
