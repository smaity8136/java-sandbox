package com.seedollar.java.sandbox.resilience4j.service.impl;

import com.seedollar.java.sandbox.resilience4j.model.Branch;
import com.seedollar.java.sandbox.resilience4j.service.BranchService;
import io.github.resilience4j.cache.Cache;
import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BranchServiceImpl implements BranchService {

    private static final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

    private Cache<String, Branch> branchCacheContext;

    public BranchServiceImpl(Cache<String, Branch> branchCacheContext) {
        this.branchCacheContext = branchCacheContext;
    }

    @Override
    public void addBranch(Branch branch) {
    }

    @Override
    public Branch getBranch(String branchName) {
        // Decorate the buildNewBranch function with the cacheContext
        CheckedFunction1<String, Branch> getOrBuildBranchFunction = Cache.decorateCheckedSupplier(branchCacheContext, () -> buildNewBranch(branchName));
        return Try.of(() -> getOrBuildBranchFunction.apply(branchName)).get();
    }

    @Override
    public List<Branch> getAllBranches() {
        return null;
    }

    private Branch buildNewBranch(String branchName) {
        logger.info("Building new branch with name: {}", branchName);
        Branch newBranch = new Branch();
        newBranch.setBranchId(UUID.randomUUID().toString());
        newBranch.setName(branchName);
        newBranch.setOpen(ThreadLocalRandom.current().nextBoolean());
        return newBranch;
    }
}
