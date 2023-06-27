package com.tqs.pickuppointbackend.constants;

public enum UserType {
    ADMIN("ADMIN"),
    ACP("ACP"),
    CLIENT("CLIENT"),
    TEST("TEST");

    private String userType;

    UserType(String userType) {
        this.userType = userType;
    }
}
