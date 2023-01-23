package com.scaler.lld.bookmyshow.model.payment;

import com.scaler.lld.bookmyshow.model.show.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID referenceId;

    private double amount;

    @Enumerated(value = EnumType.ORDINAL)
    private PaymentMode paymentMode;

    @Enumerated(value = EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
