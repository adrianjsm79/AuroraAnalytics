package com.tecsup.aurora_admin.controller;

import com.tecsup.aurora_admin.service.LegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/legal")
@CrossOrigin("*")
public class LegalController {

    @Autowired
    private LegalService legalService;

    @GetMapping("/terms")
    public Map<String, String> getTerms() {
        return Map.of("content", legalService.getTermsAndConditions());
    }

    @PostMapping("/terms")
    public void updateTerms(@RequestBody Map<String, String> body) {
        String content = body.get("content");
        if (content != null) {
            legalService.updateTerms(content);
        }
    }
}