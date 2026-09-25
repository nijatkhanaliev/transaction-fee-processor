package org.transactionfeeprocessor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.transactionfeeprocessor.model.enums.Currency;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreatedEvent {
    private String transactionId;
    private long userId;
    private BigDecimal amount;
    private Currency currency;
    private

}
