package com.pht.certify.main;

public class Main {

    private String siteName;
    private String siteDescription;
    private String developerName;
    private String purpose;

    public Main(String siteName, String siteDescription, String developerName, String purpose) {
        this.siteName = siteName;
        this.siteDescription = siteDescription;
        this.developerName = developerName;
        this.purpose = purpose;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteDescription() {
        return siteDescription;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public String getPurpose() {
        return purpose;
    }
}