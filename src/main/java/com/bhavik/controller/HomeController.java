package com.bhavik.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeControllerHandler() {
        return "this is home controller 1";
    }

    @GetMapping("/home")
    public String homeControllerHandler2() {
        return "this is home controller 2";
    }

    @GetMapping("/codewithzosh")
    public String homeControllerHandler3() {
        return "hello codewithzosh";
    }

//@PutMapping
//@PostMapping
//	@DeleteMapping

}
