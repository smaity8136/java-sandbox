package com.seedollar.java.sandbox.resilience4j.controller;

import com.seedollar.java.sandbox.resilience4j.service.MainframeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainframe")
public class MainframeController {

    private static final Logger logger = LoggerFactory.getLogger(MainframeController.class);
    private MainframeService mainframeService;

    public MainframeController(MainframeService mainframeService) {

        this.mainframeService = mainframeService;
    }

    @PostMapping("/sync/{accountId}")
    public ResponseEntity<String> syncAccount(@PathVariable("accountId") String accountId) {
        String body = mainframeService.syncAccount(accountId);
        logger.info("Sync Account Result: {}", body);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/batch")
    public ResponseEntity<String> batchJobs() {
        String body = mainframeService.batchJobs();
        logger.info("Batch Jobs Result: {}", body);
        return ResponseEntity.ok(body);
    }



}
