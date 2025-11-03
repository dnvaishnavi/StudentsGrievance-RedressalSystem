package com.example.grievences.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grievences")
public class Grievence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String description;
    private String suggestions;
    private String status = "Pending";  // Default status
    private String action_Taken;         // ✅ Added this properly

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Link to user

    // ---------------- Constructors ----------------
    public Grievence() {
    }

    public Grievence(String subject, String description, String suggestions, String status, String actionTaken, User user) {
        this.subject = subject;
        this.description = description;
        this.suggestions = suggestions;
        this.status = status;
        this.action_Taken = actionTaken;
        this.user = user;
    }

    // ---------------- Getters & Setters ----------------
    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionTaken() {
        return action_Taken;
    }

    public void setActionTaken(String actionTaken) {
        this.action_Taken = actionTaken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
