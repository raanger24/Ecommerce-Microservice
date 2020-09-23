package com.ecommerce.orders.controller;

import com.ecommerce.orders.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.orders.service.kafka.producer.KafkaSender;
import com.ecommerce.orders.service.OrdersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/orders")
@Api(value = "Orders", description = "Order Processing in the ECommerce Store")
public class OrdersController {

    private final OrdersService ordersService;
    private final KafkaSender kafkaSender;

    @Autowired
    public OrdersController(OrdersService ordersService, KafkaSender kafkaSender) {
        this.ordersService = ordersService;
        this.kafkaSender = kafkaSender;
    }

    @ApiOperation(value = "Get Order by ID", response = Object.class)
    @GetMapping(path = "/get/{orderID}", produces = "application/json")
    public Object getOrder(@PathVariable String orderID) {
        return ordersService.getOrder(orderID);
    }

    @PostMapping("/create")
    public Orders createOrder(@RequestBody Orders order){
        return ordersService.createOrder(order);
    }

    @GetMapping("/cancel/{orderID}")
    public Orders cancelOrder(@PathVariable String orderID){
        return ordersService.cancelOrder(orderID);
    }
}
