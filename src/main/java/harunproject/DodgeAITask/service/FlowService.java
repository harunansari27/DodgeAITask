package harunproject.DodgeAITask.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import harunproject.DodgeAITask.entity.Delivery;
import harunproject.DodgeAITask.entity.Invoice;
import harunproject.DodgeAITask.entity.Orders;
import harunproject.DodgeAITask.entity.Payment;
import harunproject.DodgeAITask.repository.DeliveryRepository;
import harunproject.DodgeAITask.repository.InvoiceRepository;
import harunproject.DodgeAITask.repository.OrderRepository;
import harunproject.DodgeAITask.repository.PaymentRepository;

@Service
public class FlowService {

    private final InvoiceRepository invoiceRepo;
    private final DeliveryRepository deliveryRepo;
    private final OrderRepository orderRepo;
    private final PaymentRepository paymentRepo;

    public FlowService(InvoiceRepository invoiceRepo,
                       DeliveryRepository deliveryRepo,
                       OrderRepository orderRepo,
                       PaymentRepository paymentRepo) {
        this.invoiceRepo = invoiceRepo;
        this.deliveryRepo = deliveryRepo;
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
    }

    public Map<String, Object> traceFlow(String invoiceNumber) {

        Map<String, Object> result = new LinkedHashMap<>();

        // 1. Invoice
        Invoice invoice = invoiceRepo.findByInvoiceNumber(invoiceNumber);
        if (invoice == null) {
            result.put("error", "Invoice not found");
            return result;
        }

        result.put("invoice", invoice);

        // 2. Delivery
        Delivery delivery = deliveryRepo.findById(invoice.getDeliveryId()).orElse(null);
        result.put("delivery", delivery);

        // 3. Order
        Orders order = null;
        if (delivery != null) {
            order = orderRepo.findById(delivery.getOrderId()).orElse(null);
        }
        result.put("order", order);

        // 4. Payment
        Payment payment = paymentRepo.findByInvoiceId(invoice.getId());
        result.put("payment", payment);

        return result;
    }
}
