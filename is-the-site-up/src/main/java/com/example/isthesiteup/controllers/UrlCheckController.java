package com.example.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/")
    public String helloMessage() {
        return "Welcome to the site checker app!";
    }

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        System.out.println(url);

        try {
            URL urlObj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCodeCategory = conn.getResponseCode() / 100;
                System.out.println(conn.getResponseCode());
                if (responseCodeCategory == 2 || responseCodeCategory == 3) {
                    returnMessage = SITE_IS_UP;
                    System.out.println("if statement hit");
                } else {
                    returnMessage = SITE_IS_DOWN;
                    System.out.println("else statement hit");
                }
            } catch (MalformedInputException e) {
                returnMessage = INCORRECT_URL;
            } catch (IOException e) {
               returnMessage = SITE_IS_DOWN;
        } 
        return returnMessage;
    }
}