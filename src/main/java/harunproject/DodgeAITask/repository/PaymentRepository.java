package harunproject.DodgeAITask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import harunproject.DodgeAITask.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByInvoiceId(Long invoiceId);
}