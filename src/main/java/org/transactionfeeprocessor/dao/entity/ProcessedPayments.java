package org.transactionfeeprocessor.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedPayments {
    @Id
    private String transactionId;
    private LocalDateTime createdAt;
    private long paymentId;
}
