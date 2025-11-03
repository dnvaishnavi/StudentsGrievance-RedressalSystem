package com.example.grievences.controller;

import com.example.grievences.model.Grievence;
import com.example.grievences.service.GrievenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/grievances")
@CrossOrigin(origins = "*") // allows frontend requests (React, HTML, etc.)
public class GrievenceController {

    @Autowired
    private GrievenceService grievenceService;

    // Add a new grievance
    @PostMapping("/add")
    public Grievence addGrievence(@RequestBody Grievence grievence) {
        return grievenceService.addGrievence(grievence);
    }
//
    //  Get all grievances (for admin)
    @GetMapping("/all")
    public List<Grievence> getAllGrievances() {
        return grievenceService.getAllGrievences();
    }

     //Get grievances by user ID
    @GetMapping("/user/{userId}")
    public List<Grievence> getByUser(@PathVariable Long userId) {
        return grievenceService.getGrievencesByUser(userId);
    }

    //  Update grievance status (admin use)
    @PutMapping("/{id}/status")
    public Grievence updateStatus(@PathVariable Long id, @RequestParam String status) {
        return grievenceService.updateStatus(id, status);
    }

    //  Update full grievance details (status + action taken)
    @PutMapping("/{id}/update")
    public Grievence updateGrievance(@PathVariable Long id, @RequestBody Grievence updated) {
        return grievenceService.updateGrievance(id, updated);
    }

    //  Delete grievance
    @DeleteMapping("/{id}")
    public void deleteGrievance(@PathVariable Long id) {
        grievenceService.deleteGrievence(id);
    }

    //  Search grievances (by keyword in subject/description)
    @GetMapping("/search")
    public List<Grievence> searchGrievances(@RequestParam String keyword) {
        return grievenceService.searchGrievances(keyword);
    }

    //  Get 5 most recent grievances
    @GetMapping("/recent")
    public List<Grievence> getRecentGrievances() {
        return grievenceService.getRecentGrievances();
    }

    //  Get statistics (resolved & pending count)
    @GetMapping("/stats/{userId}")
    public Map<String, Long> getStats(@PathVariable Long userId) {
        return grievenceService.getStats(userId);
    }
}
