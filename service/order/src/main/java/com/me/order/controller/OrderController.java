package com.me.order.controller;

import com.me.base_domains.Order;
import com.me.base_domains.OrderEventDTO;
import com.me.order.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEventDTO orderEventDTO = new OrderEventDTO("PENDING","order status in pending status",order);

        orderProducer.sendMessage(orderEventDTO);

        return "Order placed successfully";
    }
}
