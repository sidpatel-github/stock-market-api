package com.example.stockpaymentservice.service;

import com.example.stockpaymentservice.dto.OrderRequestDto;
import com.example.stockpaymentservice.dto.PaymentRequestDto;
import com.example.stockpaymentservice.entity.UserBalance;
import com.example.stockpaymentservice.entity.UserTransaction;
import com.example.stockpaymentservice.event.impl.OrderEvent;
import com.example.stockpaymentservice.event.impl.PaymentEvent;
import com.example.stockpaymentservice.repository.UserBalanceRepository;
import com.example.stockpaymentservice.repository.UserTransactionRepository;
import com.example.stockpaymentservice.status.PaymentStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDB() {
        // pre-loading the wallet for users
        userBalanceRepository.saveAll(Stream.of(new UserBalance("sidpatel@gmail.com", 5000),
                new UserBalance("sidpatel2@gmail.com", 3000)
        ).collect(Collectors.toList()));
    }


    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getId(),
                orderRequestDto.getUserId(), orderRequestDto.getAmount());

        return userBalanceRepository.findById(orderRequestDto.getUserId())
                .filter(ub -> ub.getPrice() > orderRequestDto.getAmount())
                .map(ub -> {
                    ub.setPrice(ub.getPrice() - orderRequestDto.getAmount());
                    userBalanceRepository.save(ub);
                    userTransactionRepository.save(new UserTransaction(orderRequestDto.getId(), orderRequestDto.getUserId(), orderRequestDto.getAmount()));
                    return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
                }).orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));
    }


    public void cancelOrderEvent(OrderEvent orderEvent) {

        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getId())
                .ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                    userTransactionRepository.findById(ut.getUserId())
                            .ifPresent(ub -> ub.setAmount(ub.getAmount() + ut.getAmount()));
                });
    }
}
