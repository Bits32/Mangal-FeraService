package com.example.mangalfera.controller;

import com.example.mangalfera.model.MembershipEnquiry;
import com.example.mangalfera.service.MembershipEnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership-leads")
@CrossOrigin
public class MembershipEnquiryController {

    @Autowired
    private MembershipEnquiryService service;

    @PostMapping("/add")
    public MembershipEnquiry createEnquiry(@RequestBody MembershipEnquiry enquiry) {
        return service.saveEnquiry(enquiry);
    }

    @GetMapping("/all")
    public List<MembershipEnquiry> getAll() {
        return service.getAllEnquiries();
    }

}
