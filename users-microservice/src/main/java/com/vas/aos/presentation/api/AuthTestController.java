package com.vas.aos.presentation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users-management/test")
public class AuthTestController {

    @GetMapping
    public String test() {
        return "Hello";
    }

}