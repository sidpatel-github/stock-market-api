package com.example.stockpaymentservice.event.impl;

import com.example.stockpaymentservice.dto.OrderRequestDto;
import com.example.stockpaymentservice.event.Event;
import com.example.stockpaymentservice.status.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
@ToString
public class OrderEvent implements Event {
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }
}
