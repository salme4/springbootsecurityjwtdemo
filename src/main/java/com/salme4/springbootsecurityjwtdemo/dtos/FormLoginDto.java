package com.salme4.springbootsecurityjwtdemo.dtos;

public class FormLoginDto {
    public FormLoginDto() {
    }

    private String nickname;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
