package harunproject.DodgeAITask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import harunproject.DodgeAITask.dto.GraphResponse;
import harunproject.DodgeAITask.service.GraphService;

@RestController
@RequestMapping("/api")
public class GraphController {

    @Autowired
    private GraphService graphService;

   @GetMapping("/graph/{invoiceNumber}")
    public GraphResponse getGraph(@PathVariable String invoiceNumber) {
    return graphService.getGraph(invoiceNumber);
}
}