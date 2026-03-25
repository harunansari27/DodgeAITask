package harunproject.DodgeAITask.controller;

import org.springframework.web.bind.annotation.*;
import harunproject.DodgeAITask.dto.GraphResponse;
import harunproject.DodgeAITask.service.GraphService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/graph")
    public GraphResponse getGraph() {
        return graphService.buildGraph();
    }


    @GetMapping("/graph/{invoiceId}")
    public GraphResponse getGraphByInvoice(@PathVariable String invoiceId) {
    return graphService.buildGraphForInvoice(invoiceId);
}

}