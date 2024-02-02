package com.example.stockorderservice.entity;

import com.example.stockorderservice.status.OrderStatus;
import com.example.stockorderservice.status.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "purchase_order")
public class PurchaseOrder {
    @Id
    private String id;
    private String userId;
    private String stockSymbol;
    private Integer amount;
    private Integer quantity;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
}
