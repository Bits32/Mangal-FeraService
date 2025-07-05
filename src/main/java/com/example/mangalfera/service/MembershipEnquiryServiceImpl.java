package com.example.mangalfera.service;

import com.example.mangalfera.model.MembershipEnquiry;
import com.example.mangalfera.repository.MembershipEnquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipEnquiryServiceImpl implements MembershipEnquiryService {

    private final MembershipEnquiryRepository repository;

    public MembershipEnquiryServiceImpl(MembershipEnquiryRepository repository) {
        this.repository = repository;
    }

    @Override
    public MembershipEnquiry saveEnquiry(MembershipEnquiry enquiry) {
        return repository.save(enquiry);
    }

    @Override
    public List<MembershipEnquiry> getAllEnquiries() {
        return repository.findAll();
    }
}
