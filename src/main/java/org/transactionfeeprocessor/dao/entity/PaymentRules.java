package org.transactionfeeprocessor.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.transactionfeeprocessor.model.enums.Currency;
import org.transactionfeeprocessor.model.enums.PaymentType;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Currency currency;
    private PaymentType paymentType;

    @Column(nullable = false)
    private BigDecimal minAmount;

    @Column(nullable = false)
    private BigDecimal maxAmount;

    @Column(nullable = false)
    private BigDecimal fee;
}
