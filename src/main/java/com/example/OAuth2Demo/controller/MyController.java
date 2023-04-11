package com.example.OAuth2Demo.controller;

import com.example.OAuth2Demo.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MyController
{
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/dashboard")
    public String getLoginInfo(Model model, OAuth2AuthenticationToken authentication)
    {
        Map<String, String> userData = Helper.getUserData(authentication, authorizedClientService);

        System.out.println("User Details : " + userData.toString());

        model.addAttribute("name", userData.get("name"));

        return "loginSuccess";
    }
}
