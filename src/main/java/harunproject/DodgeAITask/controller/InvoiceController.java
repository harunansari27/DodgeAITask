package harunproject.DodgeAITask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import harunproject.DodgeAITask.entity.Invoice;
import harunproject.DodgeAITask.service.InvoiceService;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoice")
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @GetMapping("/invoice/{number}")
    public Invoice getInvoice(@PathVariable String number) {
        return invoiceService.getInvoice(number);
    }
}