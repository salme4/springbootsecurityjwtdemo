package com.salme4.springbootsecurityjwtdemo.domain;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    public Account() {
    }

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String nickname;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Long socialId;
    private String profileHref;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getSocialId() {
        return socialId;
    }

    public void setSocialId(Long socialId) {
        this.socialId = socialId;
    }

    public String getProfileHref() {
        return profileHref;
    }

    public void setProfileHref(String profileHref) {
        this.profileHref = profileHref;
    }
}
