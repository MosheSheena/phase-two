package com.example.phasetwo.logic;

import com.example.phasetwo.common.UserType;

public class Producer extends User {

    private String businessName;
    private String businessDescription;

    public Producer(String name, String email, String password, UserType userType,
                    String businessName, String businessDescription) {
        super(name, email, password, userType);
        this.businessName = businessName;
        this.businessDescription = businessDescription;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }
}
