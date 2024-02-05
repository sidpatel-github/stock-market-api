package com.example.stockpaymentservice.config;

import com.example.stockpaymentservice.event.impl.OrderEvent;
import com.example.stockpaymentservice.event.impl.PaymentEvent;
import com.example.stockpaymentservice.service.PaymentService;
import com.example.stockpaymentservice.status.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        // get the user id
        // check the balance availability
        // if balance sufficient -> Payment completed and deduct amount from DB
        // if payment not sufficient -> cancel order event and update the amount in DB
        System.out.println("// sid inside processPayment : " + orderEvent);
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
            return Mono.fromSupplier(() -> this.paymentService.newOrderEvent(orderEvent));
        } else {
            return Mono.fromRunnable(() -> this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
