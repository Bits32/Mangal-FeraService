package com.example.mangalfera.service;

import com.example.mangalfera.model.MembershipPlan;
import com.example.mangalfera.repository.MembershipPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipPlanService {

    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    public List<MembershipPlan> getAllPlans() {
        return membershipPlanRepository.findAll();
    }

    public MembershipPlan createPlan(MembershipPlan plan) {
        return membershipPlanRepository.save(plan);
    }

    public MembershipPlan getPlanById(Long id) {
        return membershipPlanRepository.findById(id).orElse(null);
    }

    public void deletePlan(Long id) {
        membershipPlanRepository.deleteById(id);
    }
}
