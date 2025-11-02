package com.example.grievences.repository;

import com.example.grievences.model.Grievence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GrievenceRepository extends JpaRepository<Grievence, Long> {
    List<Grievence> findByUserUserId(Long userId); }
