package com.manohar.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyFirstController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello from my first controller";
    }

    @GetMapping("/hello2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello2() {
        return "hello-2 from my first controller";
    }

    @PostMapping("/post")
    public String saveHello(@RequestBody String message) {
        return "Request accepted and the message is : " + message;
    }

    @PostMapping("/post-order")
    public String saveOrder(@RequestBody Order order) {
        return "Request accepted and the message is : " + order.toString();
    }

    @PostMapping("/post-order-record")
    public String saveOrderRecord(@RequestBody OrderRecord order) {
        return "Request accepted and the message is : " + order.toString();
    }

    @GetMapping("/hello/{user-name}")
    public String sayHelloPathVariable(@PathVariable("user-name") String userName) {
        return "hello from my first controller with path variable: " + userName;
    }
}
