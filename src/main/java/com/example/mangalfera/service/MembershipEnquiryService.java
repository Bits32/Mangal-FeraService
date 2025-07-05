package com.example.mangalfera.service;

import com.example.mangalfera.model.MembershipEnquiry;

import java.util.List;

public interface MembershipEnquiryService {
    MembershipEnquiry saveEnquiry(MembershipEnquiry enquiry);
    List<MembershipEnquiry> getAllEnquiries();
}
