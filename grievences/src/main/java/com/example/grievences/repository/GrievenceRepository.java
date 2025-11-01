package com.example.grievences.repository;

import com.example.grievences.model.Grievence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrievenceRepository extends JpaRepository<Grievence, Long> {
}
