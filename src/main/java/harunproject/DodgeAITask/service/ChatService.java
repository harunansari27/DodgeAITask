package harunproject.DodgeAITask.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.*;

@Service
public class ChatService {

    private final DataIngestionService dataService;

    public ChatService(DataIngestionService dataService) {
        this.dataService = dataService;
    }

    public Object processQuery(String query) {

    if (query == null || query.trim().isEmpty()) {
        return "Empty query";
    }

    // 🔥 Extract invoice number
    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d{6,}");
    java.util.regex.Matcher matcher = pattern.matcher(query);

    if (matcher.find()) {

        String invoiceNumber = matcher.group();

        List<Map<String, Object>> invoices = dataService.loadInvoices();

        for (Map<String, Object> inv : invoices) {

            String id = String.valueOf(inv.get("billingDocument"));

            if (invoiceNumber.equals(id)) {

                Map<String, Object> result = new HashMap<>();

                result.put("invoice", id);
                result.put("customer", inv.get("soldToParty"));
                result.put("amount", inv.get("totalNetAmount"));
                result.put("payment", inv.get("accountingDocument"));

                return result;
            }
        }

        return "❌ Invoice not found";
    }

    return "This system is designed to answer dataset-related queries only.";
}
}