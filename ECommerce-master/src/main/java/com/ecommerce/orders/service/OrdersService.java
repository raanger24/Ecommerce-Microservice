package com.ecommerce.orders.service;

import com.ecommerce.orders.dao.OrderRepository;
import com.ecommerce.orders.domain.Customer;
import com.ecommerce.orders.domain.OrderStatus;
import com.ecommerce.orders.domain.Orders;

import com.ecommerce.orders.exception.IllegalOrderException;
import com.ecommerce.orders.exception.OrderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class OrdersService {
    private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    public Orders getOrder(String orderID) {
        try {
            UUID uuid = UUID.fromString(orderID);
            Optional<Orders> ordersOptional = orderRepository.findById(uuid);
            if(ordersOptional.isPresent()) {
                return ordersOptional.get();
            }
        } catch(IllegalArgumentException e) {
            throw new IllegalOrderException();
        }
        throw new OrderNotFoundException();
    }

    public Orders createOrder(Orders order) {
        Customer customer = customerService.getOrCreateCustomer(order.getCustomer());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PLACED);
        return orderRepository.save(order);
    }

    public Orders cancelOrder(String orderID) {
        Orders order = getOrder(orderID);
        order.setStatus(OrderStatus.CANCELLED);
        return order;
    }

    public Orders updateOrderByStatus(String orderID, OrderStatus status){
        Orders order = getOrder(orderID);
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
