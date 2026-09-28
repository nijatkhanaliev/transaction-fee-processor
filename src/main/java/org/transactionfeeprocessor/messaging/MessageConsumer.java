package org.transactionfeeprocessor.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.transactionfeeprocessor.model.dto.PaymentCreatedEvent;
import org.transactionfeeprocessor.service.PaymentService;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final PaymentService paymentService;

    @RabbitListener(queues = "payment.created.queue")
    public void consumePaymentCreated(PaymentCreatedEvent event){
        log.info("Payment Created event consumed:{}", event);
        paymentService.processPaymentCreated(event);
    }

}
