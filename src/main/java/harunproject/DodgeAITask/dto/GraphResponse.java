package harunproject.DodgeAITask.dto;

import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphResponse {
    private List<Node> nodes;
    private List<Edge> edges;
}