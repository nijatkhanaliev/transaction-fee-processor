package org.transactionfeeprocessor.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.transactionfeeprocessor.dao.entity.PaymentRules;
import org.transactionfeeprocessor.model.enums.Currency;
import org.transactionfeeprocessor.model.enums.PaymentType;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PaymentRulesRepository extends JpaRepository<PaymentRules, Long> {

    @Query("""
        select r
        from PaymentRules r
        where r.currency = :currency
        and r.paymentType = :paymentType
        and :amount between r.minAmount and r.maxAmount
    """)
    Optional<PaymentRules> findPaymentRule(
            @Param("currency") Currency currency,
            @Param("paymentType") PaymentType paymentType,
            @Param("amount") BigDecimal amount
            );

}
