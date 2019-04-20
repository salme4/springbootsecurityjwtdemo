package com.salme4.springbootsecurityjwtdemo.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

    public boolean isCorrectName(String name){
        return name.equalsIgnoreCase(this.roleName);
    }

    public static UserRole getRoleByName(String roleName){
        return Arrays.stream(UserRole.values()).filter(role -> role.isCorrectName(roleName)).findFirst().orElseThrow(()-> new NoSuchElementException("검색된 권한이 없습니다."));
    }
}
