package com.example.helloWorld.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class HelloController {

    @GetMapping("/greet")
    public ResponseEntity<String> getGreeting(){
        LocalTime localTime = LocalTime.now();
        LocalTime morning = LocalTime.NOON;
        LocalTime evening = LocalTime.parse("18:00:00");
        String greeting = "";
        if(localTime.isBefore(morning)){
            greeting="Good Morning";
        }
        if (localTime.isAfter(morning)){
            greeting="Good Afternoon";
        }
        if (localTime.isAfter(evening)){
            greeting="Good Evening";
        }
        return ResponseEntity.ok(greeting+" The current time is "+localTime);
    }

}
