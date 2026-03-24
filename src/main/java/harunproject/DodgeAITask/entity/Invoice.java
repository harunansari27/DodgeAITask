package harunproject.DodgeAITask.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private String customerName;
    private Double amount;
}