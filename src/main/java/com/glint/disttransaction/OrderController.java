package com.glint.disttransaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String home(@RequestParam(required = false, defaultValue = "false") boolean fail) {
        orderService.placeOrder(fail);
        return "ok";
    }
}
