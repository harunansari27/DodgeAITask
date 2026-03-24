package harunproject.DodgeAITask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import harunproject.DodgeAITask.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findByInvoiceNumber(String invoiceNumber);
}