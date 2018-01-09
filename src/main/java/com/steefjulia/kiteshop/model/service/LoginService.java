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
        /* 
		 * Het ingegeven nog niet gehashte password wordt in de hasher gestopt en daarna wordt het pasword overschreven 
		 * door het gehashte, en het orgineel bestaat dan dus niet meer
         */

//        String salthex = createSaltHex();
//        String hashedwachtwoord = createHashedPassword(salthex, account.getPassword());
//        account.setSalt(salthex);
//        account.setPassword(hashedwachtwoord);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDao.save(account);
    }
    

    public boolean checkLogin(String gebruikersnaam, String gegevenWachtwoord) {
        Account currentAccount = accountDao.findByUsername(gebruikersnaam);
        String saltCurrentAccount = currentAccount.getSalt();
        String gegevenWachtwoordGehashd = createHashedPassword(saltCurrentAccount, gegevenWachtwoord);
        return gegevenWachtwoordGehashd.equals(currentAccount.getPassword());
    }

    public static String createHashedPassword(String saltHex, String password) {
        String paswordHex = toHexadecimal(password);
        String combinedHex = saltHex + paswordHex;

        byte[] combinedArray = hexStringtoByteArray(combinedHex);

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("md5"); // je kan hier md5 of SHA zetten, blijft werken
        } catch (Exception e) {
            e.printStackTrace();
        }

        md.update(combinedArray);
        byte[] hashedPasswordInBytes = md.digest();
        String hashedPasswordHex = biteArrayToHexString(hashedPasswordInBytes);

        return hashedPasswordHex;
    }

    public static String createSaltHex() {
        byte[] saltBytes = createSaltBytes();
        String saltHexString = biteArrayToHexString(saltBytes);
        return saltHexString;
    }

    private static byte[] createSaltBytes() {
        SecureRandom random = new SecureRandom();
        byte[] values = new byte[32];

        random.nextBytes(values);

        return values;
    }

    public static String createHashedToken(String token) {
        String tokenHex = toHexadecimal(token + "d|t1s33nt0k3n");
        byte[] combinedArray = hexStringtoByteArray(tokenHex);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA"); // je kan hier md5 of SHA zetten, blijft werken
        } catch (Exception e) {
            e.printStackTrace();
        }
        md.update(combinedArray);
        byte[] hashedPasswordInBytes = md.digest();
        String hashedTokenHex = biteArrayToHexString(hashedPasswordInBytes);

        return hashedTokenHex;
    }

    //Helper 'omreken' functies
    private static String biteArrayToHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }

    private static byte[] hexStringtoByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

    private static String toHex(byte[] buf) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    private static String toHexadecimal(String text) {
        byte[] myBytes = null;
        try {
            myBytes = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return DatatypeConverter.printHexBinary(myBytes);
    }

}
