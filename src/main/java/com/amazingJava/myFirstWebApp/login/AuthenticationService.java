package com.amazingJava.myFirstWebApp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public  boolean authenticate(String name,String password){
        return  name.equals("jewel") && password.equals("3456");
    }
}
