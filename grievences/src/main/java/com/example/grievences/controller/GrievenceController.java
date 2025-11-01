package com.example.grievences.controller;

import com.example.grievences.model.Grievence;
import com.example.grievences.service.GrievenceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/grievances")
@CrossOrigin(origins = "*")
public class GrievenceController {
    private final GrievenceService grievanceService;

    public GrievenceController(GrievenceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @PostMapping
    public Grievence createGrievance(@RequestBody Grievence grievance) {
        return grievanceService.submitGrievance(grievance);
    }

    @GetMapping
    public List<Grievence> getAllGrievances() {
        return grievanceService.getAllGrievances();
    }

    @GetMapping("/{id}")
    public Grievence getGrievanceById(@PathVariable Long id) {
        return grievanceService.getGrievanceById(id);
    }

    @PutMapping("/{id}")
    public Grievence updateStatus(@PathVariable Long id, @RequestBody Grievence updated) {
        return grievanceService.updateStatus(id, updated.getStatus());
    }
}
