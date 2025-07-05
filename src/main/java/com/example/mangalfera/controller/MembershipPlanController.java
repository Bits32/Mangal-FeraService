package com.example.mangalfera.controller;

import com.example.mangalfera.model.MembershipPlan;
import com.example.mangalfera.service.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership-plans")
public class MembershipPlanController {

    @Autowired
    private MembershipPlanService membershipPlanService;


    @GetMapping("/all")
    public List<MembershipPlan> getAllPlans() {
        return membershipPlanService.getAllPlans();
    }

    @PostMapping
    public MembershipPlan createPlan(@RequestBody MembershipPlan plan) {
        return membershipPlanService.createPlan(plan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipPlan> getPlan(@PathVariable Long id) {
        MembershipPlan plan = membershipPlanService.getPlanById(id);
        return plan != null ? ResponseEntity.ok(plan) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        membershipPlanService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}

