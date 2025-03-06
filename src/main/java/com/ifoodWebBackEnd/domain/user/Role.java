package com.ifoodWebBackEnd.domain.user;

public enum Role {
    RESTAURANT("restaurant"),
    COSTUMER("costumer");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
