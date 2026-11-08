package org.transactionfeeprocessor.service;

import org.transactionfeeprocessor.model.dto.PaymentCreatedEvent;

public interface PaymentService {

    void processPaymentCreated(PaymentCreatedEvent event);

}
