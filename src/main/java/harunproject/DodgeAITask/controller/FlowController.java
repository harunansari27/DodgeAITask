package harunproject.DodgeAITask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import harunproject.DodgeAITask.service.FlowService;

@RestController
@RequestMapping("/api")
public class FlowController {

    private final FlowService flowService;

    public FlowController(FlowService flowService) {
        this.flowService = flowService;
    }

    @GetMapping("/flow/{invoiceNumber}")
    public Object getFlow(@PathVariable String invoiceNumber) {
        return flowService.traceFlow(invoiceNumber);
    }
}
