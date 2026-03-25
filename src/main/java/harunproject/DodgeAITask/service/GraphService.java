package harunproject.DodgeAITask.service;

import org.springframework.stereotype.Service;
import harunproject.DodgeAITask.dto.*;

import java.util.*;

@Service
public class GraphService {

    private final DataIngestionService dataService;

    public GraphService(DataIngestionService dataService) {
        this.dataService = dataService;
    }

   public GraphResponse buildGraph() {

    List<Map<String, Object>> invoices = dataService.loadInvoices();

    List<Node> nodes = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    Set<String> nodeSet = new HashSet<>();

    for (Map<String, Object> inv : invoices) {

        String invoiceId = String.valueOf(inv.get("billingDocument"));
        String paymentId = String.valueOf(inv.get("accountingDocument"));
        String customerId = String.valueOf(inv.get("soldToParty"));

        if (invoiceId == null) continue;

        String invoiceNode = "INV_" + invoiceId;
        String paymentNode = "PAY_" + paymentId;
        String customerNode = "CUST_" + customerId;

        // 🔥 Nodes
        if (nodeSet.add(customerNode))
            nodes.add(new Node(customerNode, "Customer: " + customerId));

        if (nodeSet.add(invoiceNode))
            nodes.add(new Node(invoiceNode, "Invoice: " + invoiceId));

        if (paymentId != null && !paymentId.equals("null")) {
            if (nodeSet.add(paymentNode))
                nodes.add(new Node(paymentNode, "Payment: " + paymentId));
        }

        // 🔥 Edges (REAL RELATION)
        edges.add(new Edge(customerNode, invoiceNode));

        if (paymentId != null && !paymentId.equals("null")) {
            edges.add(new Edge(invoiceNode, paymentNode));
        }
    }

    return new GraphResponse(nodes, edges);
}

public GraphResponse buildGraphForInvoice(String invoiceId) {

    List<Map<String, Object>> invoices = dataService.loadInvoices();

    List<Node> nodes = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    for (Map<String, Object> inv : invoices) {

        String id = String.valueOf(inv.get("billingDocument"));
        if (!invoiceId.equals(id)) continue;

        String customer = String.valueOf(inv.get("soldToParty"));
        String payment = String.valueOf(inv.get("accountingDocument"));

        String custNode = "CUST_" + customer;
        String invNode = "INV_" + id;
        String payNode = "PAY_" + payment;

        nodes.add(new Node(custNode, "Customer: " + customer));
        nodes.add(new Node(invNode, "Invoice: " + id));

        if (payment != null && !payment.equals("null")) {
            nodes.add(new Node(payNode, "Payment: " + payment));
        }

        edges.add(new Edge(custNode, invNode));

        if (payment != null && !payment.equals("null")) {
            edges.add(new Edge(invNode, payNode));
        }
    }

    return new GraphResponse(nodes, edges);
}

}