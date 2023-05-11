package com.inventory.productmanagementsystem;

import com.inventory.productmanagementsystem.Model.Complaint;
import com.inventory.productmanagementsystem.Repository.ComplaintRepository;
import com.inventory.productmanagementsystem.Service.ComplaintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ComplaintTests {
    @InjectMocks
    private ComplaintService complaintService;

    @Mock
    private ComplaintRepository complaintRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(ComplaintTests.this);
        complaintService = new ComplaintService(complaintRepository);
    }

//    @Test
//    public void testAddComplaint() {
//        String content = "This is a test complaint.";
//        ResponseEntity<String> response = complaintService.addComplaint(content);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Complaint Registered.", response.getBody());
//    }

//    @Test
//    public void testGetComplaints() {
//        // create some test complaints
//        Complaint complaint1 = new Complaint(LocalDateTime.now(), "Test complaint 1");
//        Complaint complaint2 = new Complaint(LocalDateTime.now(), "Test complaint 2");
//        complaintRepository.save(complaint1);
//        complaintRepository.save(complaint2);
//        // call the method to get all complaints
//        List<Complaint> complaints = complaintService.getComplaints();
//        // check if the returned list contains the test complaints
//        assertTrue(complaints.contains(complaint1));
//        assertTrue(complaints.contains(complaint2));
//        // check if the user and order lists of each complaint are cleared
//        for (Complaint complaint : complaints) {
//            assertNull(complaint.getUser().getComplaintList());
//            assertNull(complaint.getUser().getOrderList());
//        }
//    }

//    @Test
//    public void testDeleteComplaint() {
//        // Create a new complaint
//        Complaint complaint = new Complaint(LocalDateTime.now(), "Test complaint content");
//        complaintRepository.save(complaint);
//        Long complaintId = complaint.getId();
//        // Delete the complaint using the method
//        ResponseEntity<String> response = complaintService.deleteComplaint(complaintId);
//        // Check if the complaint was deleted successfully
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Complaint deleted successfully.", response.getBody());
//        assertFalse(complaintRepository.existsById(complaintId));
//    }

//    @Test
//    void testUpdateComplaint() {
//        // create a new complaint
//        Complaint complaint = new Complaint(LocalDateTime.now(), "Test complaint content");
//        complaint = complaintRepository.save(complaint);
//        // update the complaint content
//        String newContent = "New test complaint content";
//        ResponseEntity<String> response = complaintService.updateComplaint(complaint.getId(), newContent);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        // retrieve the updated complaint from the database and check its content
//        Optional<Complaint> updatedComplaint = complaintRepository.findById(complaint.getId());
//        assertTrue(updatedComplaint.isPresent());
//        assertEquals(newContent, updatedComplaint.get().getContent());
//        // delete the complaint to clean up the database
//        complaintRepository.deleteById(complaint.getId());
//    }
}