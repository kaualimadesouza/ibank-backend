package com.kaualimadesouza.bank.Domain;


import com.kaualimadesouza.bank.DTO.TransferRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "transfers")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    /*

    CREATE TABLE transfers (
        id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
        date_transfer TIMESTAMP NOT NULL,
        type VARCHAR(100) NOT NULL,
        value_transfer NUMERIC(10,2) NOT NULL,
        id_sender UUID NOT NULL,
        FOREIGN KEY (id_sender) REFERENCES users(id),
        id_receiver UUID NOT NULL,
        FOREIGN KEY (id_receiver) REFERENCES users(id)
    );

     */
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "date_transfer", nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private PayMethodEnum type;

    @Column(name = "value_transfer", nullable = false)
    private Double valueTransfer;

    @ManyToOne
    @JoinColumn(name = "id_sender", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "id_receiver", nullable = false)
    private User receiver;


    public Transfer(TransferRequestDTO requestDTO, User senderTransfer, User receiverTransfer) {
        this.date = LocalDateTime.now();
        this.type = requestDTO.type();
        this.valueTransfer = requestDTO.amount();
        this.sender = senderTransfer;
        this.receiver = receiverTransfer;
    }
}
