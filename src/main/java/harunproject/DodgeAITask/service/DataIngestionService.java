package harunproject.DodgeAITask.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DataIngestionService {

    private final JsonLoaderService loader;

    public DataIngestionService(JsonLoaderService loader) {
        this.loader = loader;
    }

    // 🔥 Individual loaders
    public List<Map<String, Object>> loadInvoices() {
        return loader.loadFolderFromResources("billing_document_headers");
    }

    public List<Map<String, Object>> loadDeliveries() {
        return loader.loadFolderFromResources("outbound_delivery_headers");
    }

    public List<Map<String, Object>> loadOrders() {
        return loader.loadFolderFromResources("sales_order_headers");
    }

    public List<Map<String, Object>> loadPayments() {
        return loader.loadFolderFromResources("payments_accounts_receivable");
    }

    // 🔥 IMPORTANT: Core loader (fix for crash 💀)
    public Map<String, List<Map<String, Object>>> loadCoreData() {

        Map<String, List<Map<String, Object>>> data = new HashMap<>();

        data.put("orders", loadOrders());
        data.put("delivery", loadDeliveries());
        data.put("invoice", loadInvoices());
        data.put("payment", loadPayments());

        System.out.println("🔥 Orders: " + data.get("orders").size());
        System.out.println("🔥 Delivery: " + data.get("delivery").size());
        System.out.println("🔥 Invoice: " + data.get("invoice").size());
        System.out.println("🔥 Payment: " + data.get("payment").size());

        return data;
    }
}