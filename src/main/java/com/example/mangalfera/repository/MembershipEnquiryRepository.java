package com.example.mangalfera.repository;

import com.example.mangalfera.model.MembershipEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipEnquiryRepository extends JpaRepository<MembershipEnquiry, Long> {
}