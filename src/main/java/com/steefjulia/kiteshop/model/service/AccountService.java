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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author julia
 */
@Service
public class AccountService {

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
    //hier wordt gebruik gemaakt van de instantie die alle gegeven van de gebruiker bewaard.
        //sessionAttribute wordt dus niet meer gebruikt
        //als de gebruiker nog niet is ingelogd krijgt ie dummy als username
    public Account checkUsername(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = new Account();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            account.setUsername(currentUserName);
        } else {
            account.setUsername("dummy");
        }
        return account;
    }
}
