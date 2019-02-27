package com.seedollar.java.sandbox.resilience4j.controller;

import com.seedollar.java.sandbox.resilience4j.model.Branch;
import com.seedollar.java.sandbox.resilience4j.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{branchName}")
    public ResponseEntity<Branch> getBranchForName(@PathVariable("branchName") String branchName) {
        return ResponseEntity.ok(branchService.getBranch(branchName));
    }
}
