package com.amazingJava.myFirstWebApp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class loginController {


//    @RequestMapping("login" )
//    public String gotoLoginPage(@RequestParam String name, ModelMap model){
//        model.put("name",name);
//        System.out.println("request parameter is : "+name);
//        return "login";
//    }

    @Autowired
    private  AuthenticationService authenticationService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String gotoLoginPage(){
        return "login";
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String gotoWelcomePage(@RequestParam String name,@RequestParam String password,ModelMap model) {
        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            model.put("password", password);
            //System.out.println(name);
            return "welcome";
        }
        model.put("errorMessage","invalid user and password");
        return "login";
    }

}
