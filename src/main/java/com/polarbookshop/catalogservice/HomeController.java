package com.polarbookshop.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {
   // docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT
    //kubectl create deployment catalog-service --image=catalog-service:0.0.1-SNAPSHOT
    @GetMapping("/")
    public String getGreeting(){
        return "Welcome to the book catalog";
    }
}
