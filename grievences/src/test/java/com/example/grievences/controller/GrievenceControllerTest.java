package com.example.grievences.controller;

import com.example.grievences.model.User;
import com.example.grievences.repository.GrievenceRepository;
import com.example.grievences.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GrievenceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GrievenceRepository grievanceRepository;
    private User savedUser;
    @BeforeEach
    void setup() {
        grievanceRepository.deleteAll();
        userRepository.deleteAll();

        // Create and save a user since Grievence.user cannot be null
        User user = new User();
        user.setUsername("matt");
        user.setPassword("password123");
        savedUser = userRepository.save(user);
    }
    @Test
    void testAddGrievance() throws Exception {
        // Create valid grievance JSON that includes user reference
        String grievanceJson = String.format("""
            {
                "subject": "Portal Issue",
                "description": "Unable to submit complaint form",
                "status": "Pending",
                "user": { "userId": %d }
            }
        """, savedUser.getUserId());

        // Perform POST request and expect HTTP 200 OK
        mockMvc.perform(post("/api/grievances/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(grievanceJson))
                .andExpect(status().isOk());
    }
}
