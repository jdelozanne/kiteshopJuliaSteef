/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.model.service;

import com.steefjulia.kiteshop.model.Account;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author julia
 */
public class loginService {
    
    public boolean accountExists(String gebruikersnaam) {
        boolean exists = false;
        if (accountDao.findByUsername(gebruikersnaam).getUsername() != null) {
            exists = true;
        }
        return exists;
    }

    public void createAccount(Account account) {

        String salthex = createSaltHex();
        String hashedwachtwoord = createHashedPassword(salthex, account.getPassword());
        account.setSalt(salthex);
        account.setPassword(hashedwachtwoord);

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
