/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author julia
 */
@Entity
public class Account {
    
    @Id
    @GeneratedValue
    private int id;
    
    @NotNull
    @Size(min = 6, max = 15, message = "size should be 6 -15")
    private String password;
    
    @NotNull
    @Size(min = 6, max = 15)
    private String username;
    
    public Account(){
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}