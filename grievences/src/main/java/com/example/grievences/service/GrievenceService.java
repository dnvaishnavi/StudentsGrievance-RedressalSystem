package com.example.grievences.service;

import com.example.grievences.model.Grievence;
import com.example.grievences.repository.GrievenceRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GrievenceService {
    private final GrievenceRepository grievenceRepository;

    public GrievenceService(GrievenceRepository grievenceRepository) {
        this.grievenceRepository = grievenceRepository;
    }

    public Grievence submitGrievance(Grievence grievance) {
        grievance.setStatus("Pending");
        grievance.setCreatedAt(LocalDateTime.now());
        return grievenceRepository.save(grievance);
    }

    public List<Grievence> getAllGrievances() {
        return grievenceRepository.findAll();
    }

    public Grievence getGrievanceById(Long id) {
        return grievenceRepository.findById(id).orElse(null);
    }

    public Grievence updateStatus(Long id, String status) {
        Grievence grievance = grievenceRepository.findById(id).orElseThrow();
        grievance.setStatus(status);
        grievance.setUpdatedAt(LocalDateTime.now());
        return grievenceRepository.save(grievance);
    }
}
