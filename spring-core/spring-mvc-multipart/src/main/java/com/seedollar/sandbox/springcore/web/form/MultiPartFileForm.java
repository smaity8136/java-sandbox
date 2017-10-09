package com.seedollar.sandbox.springcore.web.form;

import org.springframework.web.multipart.MultipartFile;

public class MultiPartFileForm {

    private MultipartFile evidenceDocument;

    public MultipartFile getEvidenceDocument() {
        return evidenceDocument;
    }

    public void setEvidenceDocument(MultipartFile evidenceDocument) {
        this.evidenceDocument = evidenceDocument;
    }
}
