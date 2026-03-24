package harunproject.DodgeAITask.service;

import org.springframework.stereotype.Service;

import harunproject.DodgeAITask.dto.Node;
import harunproject.DodgeAITask.entity.Delivery;
import harunproject.DodgeAITask.entity.Invoice;
import harunproject.DodgeAITask.entity.Orders;
import harunproject.DodgeAITask.entity.Payment;
import harunproject.DodgeAITask.dto.Edge;
import harunproject.DodgeAITask.dto.GraphResponse;

import java.util.*;

@Service
public class GraphService {

    private final FlowService flowService;

    public GraphService(FlowService flowService) {
        this.flowService = flowService;
    }

    public GraphResponse getGraph(String invoiceNumber) {

        Map<String, Object> flow = flowService.traceFlow(invoiceNumber);

        List<Node> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        Orders order = (Orders) flow.get("order");
        Delivery delivery = (Delivery) flow.get("delivery");
        Invoice invoice = (Invoice) flow.get("invoice");
        Payment payment = (Payment) flow.get("payment");

        if (order != null)
            nodes.add(new Node("1", "Order: " + order.getOrderNumber()));

        if (delivery != null)
            nodes.add(new Node("2", "Delivery: " + delivery.getDeliveryNumber()));

        if (invoice != null)
            nodes.add(new Node("3", "Invoice: " + invoice.getInvoiceNumber()));

        if (payment != null)
            nodes.add(new Node("4", "Payment: " + payment.getPaymentNumber()));

        // edges
        edges.add(new Edge("1", "2"));
        edges.add(new Edge("2", "3"));
        edges.add(new Edge("3", "4"));

        return new GraphResponse(nodes, edges);
    }
}