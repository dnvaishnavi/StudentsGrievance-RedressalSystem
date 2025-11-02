package com.example.grievences.controller;

import com.example.grievences.model.Grievence;
import com.example.grievences.service.GrievenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/grievances")
@CrossOrigin(origins = "*")
public class GrievenceController {

    @Autowired
    private GrievenceService grievenceService;

    @PostMapping
    public Grievence addGrievence(@RequestBody Grievence grievence) {
        return grievenceService.addGrievence(grievence);
    }

    @GetMapping
    public List<Grievence> getAll() {
        return grievenceService.getAllGrievences();
    }

    @GetMapping("/user/{userId}")
    public List<Grievence> getByUser(@PathVariable Long userId) {
        return grievenceService.getGrievencesByUser(userId);
    }

    @PutMapping("/{id}/status")
    public Grievence updateStatus(@PathVariable Long id, @RequestParam String status) {
        return grievenceService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        grievenceService.deleteGrievence(id);
    }
}
