package com.veterinaria.veterinaria.src.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "hora")
public class health {
    @GetMapping(value = "/hour")
    public String getFecha(){
        return LocalDateTime.now().toString();
    }
}
