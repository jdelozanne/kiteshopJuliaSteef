/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.model.service;

import com.steefjulia.kiteshop.model.Account;
import com.steefjulia.kiteshop.model.data.AccountDao;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author julia
 */
@Service
public class LoginService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public boolean accountExists(String gebruikersnaam) {
        boolean exists = true;

        try {
            accountDao.findByUsername(gebruikersnaam).getUsername();
        } catch (NullPointerException e) {
            exists = false;
        }
        return exists;
    }

    public void createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));  
        accountDao.save(account);
    }
}
