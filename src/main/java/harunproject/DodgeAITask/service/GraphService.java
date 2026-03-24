package harunproject.DodgeAITask.service;

import org.springframework.stereotype.Service;

import harunproject.DodgeAITask.dto.Node;
import harunproject.DodgeAITask.dto.Edge;
import harunproject.DodgeAITask.dto.GraphResponse;

import java.util.*;

@Service
public class GraphService {

    public GraphResponse getGraph() {

        List<Node> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        // Nodes
        nodes.add(new Node("1", "Customer"));
        nodes.add(new Node("2", "Order"));
        nodes.add(new Node("3", "Delivery"));
        nodes.add(new Node("4", "Invoice"));
        nodes.add(new Node("5", "Payment"));

        // Edges
        edges.add(new Edge("1", "2"));
        edges.add(new Edge("2", "3"));
        edges.add(new Edge("3", "4"));
        edges.add(new Edge("4", "5"));

        return new GraphResponse(nodes, edges);
    }
}