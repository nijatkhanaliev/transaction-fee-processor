package org.transactionfeeprocessor.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.transactionfeeprocessor.dao.entity.PaymentRules;
import org.transactionfeeprocessor.dao.entity.ProcessedPayments;
import org.transactionfeeprocessor.dao.repository.PaymentRulesRepository;
import org.transactionfeeprocessor.dao.repository.ProcessedPaymentRepository;
import org.transactionfeeprocessor.exception.RuleNotFoundException;
import org.transactionfeeprocessor.model.constant.ErrorCodes;
import org.transactionfeeprocessor.model.constant.ErrorMessages;
import org.transactionfeeprocessor.model.dto.PaymentCreatedEvent;
import org.transactionfeeprocessor.model.enums.Currency;
import org.transactionfeeprocessor.model.enums.PaymentType;
import org.transactionfeeprocessor.service.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRulesRepository paymentRulesRepository;
    private final ProcessedPaymentRepository processedPaymentRepository;

    @Override
    public void processPaymentCreated(PaymentCreatedEvent event) {
        log.info("Processing payment created event started TransactionId:{}", event.getTransactionId());
        String transactionId = event.getTransactionId();
        BigDecimal amount = event.getAmount();
        long paymentId = event.getPaymentId();
        Currency currency = event.getCurrency();
        PaymentType paymentType = event.getPaymentType();

        boolean result = checkTransactionId(transactionId);
        if(result){
            return;
        }

       try {
           createProcessedPayment(transactionId, paymentId);
       } catch (DataIntegrityViolationException ex) {
           log.warn("Transaction already being processed: {}", transactionId);
           return;
       }

       processFee(amount, currency, paymentType);
    }


    private boolean checkTransactionId(String transactionId){
        boolean exists  = processedPaymentRepository.existsByTransactionId(transactionId);

        if(exists){
            log.warn("Transaction already processed:{}", transactionId);
            return true;
        }

        return false;
    }

    private void createProcessedPayment(String transactionId, long paymentId){
        ProcessedPayments processedPayments = ProcessedPayments.builder()
                .transactionId(transactionId)
                .createdAt(LocalDateTime.now())
                .paymentId(paymentId)
                .build();

        processedPaymentRepository.save(processedPayments);
    }

    private void processFee(BigDecimal amount,Currency currency, PaymentType paymentType ){
       Optional<PaymentRules> paymentRule = paymentRulesRepository.findPaymentRule(currency, paymentType, amount);
       if(paymentRule.isPresent()){
           PaymentRules paymentRules = paymentRule.get();
           BigDecimal fee = paymentRules.getFee();
           final BigDecimal totalAmount = amount.add(fee);
           // CREATING PROCESSED PAYMENT DTO and publish it to processed.payment.queue

       }else{
           throw new RuleNotFoundException(ErrorCodes.RULE_NOT_FOUND_CODE, ErrorMessages.RULE_NOT_FOUND_MESSAGE);
       }


    }

}
