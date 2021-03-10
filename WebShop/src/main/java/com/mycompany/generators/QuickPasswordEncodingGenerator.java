package com.mycompany.generators;

/*
*Class to calculate the encrypted password for the initial user
*/

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class QuickPasswordEncodingGenerator {
    
    public static void main(String[] args) {
        
        String password = "";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("PASSWORD IS:  " + passwordEncoder.encode(password));
    }
    
}
