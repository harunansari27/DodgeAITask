package harunproject.DodgeAITask.controller;

import org.springframework.web.bind.annotation.*;

import harunproject.DodgeAITask.service.ChatService;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public Object chat(@RequestBody String query) {
        return chatService.processQuery(query);
    }
}