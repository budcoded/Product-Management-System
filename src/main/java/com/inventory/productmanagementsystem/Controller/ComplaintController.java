package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.Complaint;
import com.inventory.productmanagementsystem.Service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/complaint/")
@ResponseBody
public class ComplaintController {
    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("getComplaints")
    public List<Complaint> getComplaints () {
        return complaintService.getComplaints();
    }

//    @PostMapping("addComplaint")
//    public ResponseEntity<String> addComplaint (@RequestBody Complaint complaint) {
//        return complaintService.addComplaint(complaint);
//    }

    @PostMapping("addComplaint")
    public ResponseEntity<String> addComplaint (@RequestParam(required = true) String content) {
        return complaintService.addComplaint(content);
    }

    @DeleteMapping(path = "deleteComplaint/{id}")
    public ResponseEntity<String> deleteComplaint (@PathVariable("id") Long complaintId) {
        return complaintService.deleteComplaint(complaintId);
    }

    @PutMapping(path = "updateComplaint/{id}")
    public ResponseEntity<String> updateComplaint (@PathVariable("id") Long complaintId, @RequestParam(required = true) String content) {
        return complaintService.updateComplaint(complaintId, content);
    }
}
