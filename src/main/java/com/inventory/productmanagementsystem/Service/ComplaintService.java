package com.inventory.productmanagementsystem.Service;

import com.inventory.productmanagementsystem.Model.Complaint;
import com.inventory.productmanagementsystem.Repository.ComplaintRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintService {
    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> getComplaints() {
        return complaintRepository.findAll();
    }

    public ResponseEntity<String> addComplaint(String content) {
        Complaint complaint = new Complaint(LocalDateTime.now(), content);
        complaint.setCreatedAt(LocalDateTime.now());
        Complaint savedComplaint = complaintRepository.save(complaint);
        if (savedComplaint.getContent().equals(complaint.getContent())) {
            return ResponseEntity.ok("Complaint Registered.");
        } else {
            return ResponseEntity.status(400).body("Error in registering complaint.");
        }
    }

    public ResponseEntity<String> deleteComplaint(Long id) {
        if (!complaintRepository.existsById(id)) {
            return ResponseEntity.status(400).body("Complaint with id: " + id + " doesn't exist.");
        } else {
            complaintRepository.deleteById(id);
            return ResponseEntity.status(200).body("Complaint deleted successfully.");
        }
    }

    @Transactional
    public ResponseEntity<String> updateComplaint(Long complaintId, String content) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new IllegalStateException("Complaint with id: " + complaintId + " doesn't exist."));
        complaint.setContent(content);
        complaint.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.status(200).body("Complaint updated successfully.");
    }
}
