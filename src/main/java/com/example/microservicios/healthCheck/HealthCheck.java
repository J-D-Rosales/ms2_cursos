package com.example.microservicios.healthCheck;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RequestMapping("/")
@AllArgsConstructor
@RestController
public class HealthCheck{
    @GetMapping
    public String healthCheck(){
        return "OK";
    }
}
