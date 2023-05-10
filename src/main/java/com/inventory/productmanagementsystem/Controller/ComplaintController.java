package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.Complaint;
import com.inventory.productmanagementsystem.Service.ComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/complaint/")
@ResponseBody
public class ComplaintController {
    private final ComplaintService complaintService;
    private static final Logger logger = LoggerFactory.getLogger(ComplaintController.class);

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("getComplaints")
    public List<Complaint> getComplaints() {
        logger.info("Getting all the complaints.");
        return complaintService.getComplaints();
    }

//    @PostMapping("addComplaint")
//    public ResponseEntity<String> addComplaint (@RequestBody Complaint complaint) {
//        return complaintService.addComplaint(complaint);
//    }

    @PostMapping("addComplaint")
    public ResponseEntity<String> addComplaint(@RequestParam(required = true) String content) {
        logger.info("Customer is adding a complaint with content: " + content);
        return complaintService.addComplaint(content);
    }

    @DeleteMapping(path = "deleteComplaint/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable("id") Long complaintId) {
        logger.warn("Deleting a complaint with complaint id :" + complaintId);
        return complaintService.deleteComplaint(complaintId);
    }

    @PutMapping(path = "updateComplaint/{id}")
    public ResponseEntity<String> updateComplaint(@PathVariable("id") Long complaintId, @RequestParam(required = true) String content) {
        logger.info("Updating a complaint with id: " + complaintId + " . New content: " + content);
        return complaintService.updateComplaint(complaintId, content);
    }
}
