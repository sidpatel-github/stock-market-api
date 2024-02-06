package com.example.stockorderservice.config;

import com.example.stockorderservice.dto.OrderRequestDto;
import com.example.stockorderservice.entity.PurchaseOrder;
import com.example.stockorderservice.repository.OrderRepository;
import com.example.stockorderservice.service.OrderStatusPublisher;
import com.example.stockorderservice.status.OrderStatus;
import com.example.stockorderservice.status.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Configuration
public class OrderStatusUpdateHandler {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderStatusPublisher publisher;

    public void updateOrder(String id, Consumer<PurchaseOrder> consumer) {
        repository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        repository.save(purchaseOrder);

        if (!isPaymentComplete) {
            publisher.publishOrderEvent(convertEntityToDto(purchaseOrder), orderStatus);
        }
    }

    public OrderRequestDto convertEntityToDto(PurchaseOrder purchaseOrder) {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setId(purchaseOrder.getId());
        orderRequestDto.setUserId(purchaseOrder.getUserId());
        orderRequestDto.setAmount(purchaseOrder.getAmount());
        orderRequestDto.setQuantity(purchaseOrder.getQuantity());
        orderRequestDto.setStockSymbol(purchaseOrder.getStockSymbol());
        return orderRequestDto;
    }
}
