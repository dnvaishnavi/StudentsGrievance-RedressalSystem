package com.example.grievences.service;

import com.example.grievences.model.Grievence;
import com.example.grievences.repository.GrievenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GrievenceService {

    @Autowired
    private GrievenceRepository grievenceRepository;

    public Grievence addGrievence(Grievence grievence) {
        return grievenceRepository.save(grievence);
    }

    public List<Grievence> getAllGrievences() {
        return grievenceRepository.findAll();
    }

    public List<Grievence> getGrievencesByUser(Long userId) {
        return grievenceRepository.findByUserUserId(userId);
    }

    public Grievence updateStatus(Long id, String status) {
        Grievence g = grievenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grievance not found"));
        g.setStatus(status);
        return grievenceRepository.save(g);
    }

    public void deleteGrievence(Long id) {
        grievenceRepository.deleteById(id);
    }

    public List<Grievence> findByUserUserId(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUserUserId'");
    }
}
