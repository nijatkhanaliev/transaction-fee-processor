package org.transactionfeeprocessor.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

    public void publishPaymentProcessed(){
        log.info("Publishing payment processed event");
    }

}
