package com.example.grievences.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Grievence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String category;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Grievence() {
        this.status = "Pending";
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { 
        return id; }

    public String getStudentName() {
         return studentName; }
    public void setStudentName(String studentName) { 
        this.studentName = studentName; }

    public String getCategory() { 
        return category; }
    public void setCategory(String category) {
         this.category = category; }

    public String getDescription() { 
        return description; }
    public void setDescription(String description) { 
        this.description = description; }

    public String getStatus() { 
        return status; }
    public void setStatus(String status) {
         this.status = status; }
      
    public void setCreatedAt(LocalDateTime createdAt) {
         this.createdAt = createdAt; }     
    public LocalDateTime getCreatedAt() { 
        return createdAt; }
    public LocalDateTime getUpdatedAt() { 
        return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) {
         this.updatedAt = updatedAt; }
}
