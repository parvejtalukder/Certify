package com.pht.certify.exception;

public class CertificateNotFoundException extends RuntimeException {

    public CertificateNotFoundException(String id) {
        super("Certificate not found with ID: " + id);
    }
}