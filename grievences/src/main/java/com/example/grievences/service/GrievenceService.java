package com.example.grievences.service;

import com.example.grievences.model.Grievence;
import com.example.grievences.repository.GrievenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrievenceService {

    @Autowired
    private GrievenceRepository grievenceRepository;

    // Add new grievance
    public Grievence addGrievence(Grievence grievence) {
        return grievenceRepository.save(grievence);
    }

    // Get all grievances
    public List<Grievence> getAllGrievences() {
        return grievenceRepository.findAll();
    }

    // Get grievances by user
    public List<Grievence> getGrievencesByUser(Long userId) {
        return grievenceRepository.findByUserUserId(userId);
    }

    // Update grievance status
    public Grievence updateStatus(Long id, String status) {
        Grievence g = grievenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grievance not found with ID: " + id));
        g.setStatus(status);
        return grievenceRepository.save(g);
    }

    // Delete grievance
    public void deleteGrievence(Long id) {
        grievenceRepository.deleteById(id);
    }

    // Search grievances by keyword in subject or description
    public List<Grievence> searchGrievances(String keyword) {
        return grievenceRepository.findBySubjectContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    // Get top 5 recent grievances
    public List<Grievence> getRecentGrievances() {
        return grievenceRepository.findTop5ByOrderByIdDesc();
    }

    // Update grievance details (status and action taken)
    public Grievence updateGrievance(Long id, Grievence updated) {
        Grievence grievance = grievenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grievance not found with ID: " + id));
        grievance.setStatus(updated.getStatus());
        grievance.setActionTaken(updated.getActionTaken());
        return grievenceRepository.save(grievance);
    }

    // Get statistics of pending and resolved grievances per user
    public Map<String, Long> getStats(Long userId) {
        List<Grievence> grievances = grievenceRepository.findByUserUserId(userId);
        long pending = grievances.stream().filter(g -> "Pending".equalsIgnoreCase(g.getStatus())).count();
        long resolved = grievances.stream().filter(g -> "Resolved".equalsIgnoreCase(g.getStatus())).count();

        Map<String, Long> stats = new HashMap<>();
        stats.put("pending", pending);
        stats.put("resolved", resolved);
        return stats;
    }
}
