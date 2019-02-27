package com.seedollar.java.sandbox.resilience4j.service;

import com.seedollar.java.sandbox.resilience4j.model.Branch;

import java.util.List;

public interface BranchService {

    void addBranch(Branch branch);

    Branch getBranch(String branchName);

    List<Branch> getAllBranches();


}
