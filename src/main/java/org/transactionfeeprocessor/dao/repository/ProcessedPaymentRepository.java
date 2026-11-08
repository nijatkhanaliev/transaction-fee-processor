package org.transactionfeeprocessor.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.transactionfeeprocessor.dao.entity.ProcessedPayments;

@Repository
public interface ProcessedPaymentRepository extends JpaRepository<ProcessedPayments,Long> {

    boolean existsByTransactionId(String transactionId);

}
