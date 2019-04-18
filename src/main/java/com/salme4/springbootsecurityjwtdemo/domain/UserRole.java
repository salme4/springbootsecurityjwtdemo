package com.salme4.springbootsecurityjwtdemo.domain;

public enum UserRole {
    //ElementCollection??

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String roleName;

    UserRole(String roleName){
        this.roleName=roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
