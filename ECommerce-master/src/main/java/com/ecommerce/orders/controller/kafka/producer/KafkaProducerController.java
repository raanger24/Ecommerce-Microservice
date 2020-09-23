package com.ecommerce.orders.controller.kafka.producer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.orders.domain.OrderStatus;
import com.ecommerce.orders.domain.Orders;
import com.ecommerce.orders.domain.UpdateStatusByID;
import com.ecommerce.orders.service.OrdersService;
import com.ecommerce.orders.service.kafka.producer.KafkaSender;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kafka-producer")
@Api(value = "Kafka Producer", description = "Verify Kafka Producer-Consumer Communication")
public class KafkaProducerController {

    private final OrdersService ordersService;
    private final KafkaSender kafkaSender;

    @Autowired
    public KafkaProducerController(OrdersService ordersService, KafkaSender kafkaSender) {
        this.ordersService = ordersService;
        this.kafkaSender = kafkaSender;
    }

    @ApiOperation(value = "Data Creation Verification", response = List.class)
    @GetMapping(path = "/verifyCreation/{orderID}", produces = "application/json")
    public List<Orders> verifyCreation(@PathVariable String orderID) {
        List<Orders> listOrders = new ArrayList<>();
        listOrders.add(ordersService.getOrder(orderID));
        //Sending Orders data by ID to Kafka
        kafkaSender.sendDataForOrdersCreation(listOrders);
        return listOrders;
    }

    @ApiOperation(value = "Status Update to Cancelled", response = UpdateStatusByID.class)
    @GetMapping(path = "/verifyUpdation/{orderID}", produces = "application/json")
    public UpdateStatusByID verifyUpdation(@PathVariable String orderID) {
        List<String> listOrderIDs = new ArrayList<>();
        listOrderIDs.add("ordersService.getOrder(orderID).getId().toString()");
        UpdateStatusByID updateStatusByID = new UpdateStatusByID();
        updateStatusByID.setOrderIDs(listOrderIDs);
        updateStatusByID.setStatus(OrderStatus.CANCELLED);
        //Sending Orders data by ID to Kafka
        kafkaSender.sendDataForOrdersUpdate(updateStatusByID);
        return updateStatusByID;
    }
}
