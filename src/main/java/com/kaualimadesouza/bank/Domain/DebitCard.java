package com.kaualimadesouza.bank.Domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "debit_card")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DebitCard{
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_valid")
    private LocalDate valid;

    private String cvv;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "debit_password", nullable = false, length = 4)
    private String debitPassword;

    public DebitCard(String cardNumber, LocalDate valid, String cvv, User user, String debitPassword) {
        this.cardNumber = cardNumber;
        this.valid = valid;
        this.cvv = cvv;
        this.user = user;
        this.debitPassword = debitPassword;
    }
}
