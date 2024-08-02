package com.kaualimadesouza.bank.Domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "credit_card")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard{

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

    @Column(name = "card_limit", nullable = false, precision = 10, scale = 2)
    private BigDecimal cardLimit;

    @Column(name = "remain_limit", nullable = false, precision = 10, scale = 2)
    private BigDecimal remainLimit;

    @Column(name = "taxa_juros", nullable = false)
    private String taxaJuros;

    @Column(name = "credit_password", nullable = false, length = 6)
    private String creditPassword;

    public CreditCard(String cardNumber, LocalDate valid, String cvv, User user, BigDecimal cardLimit, BigDecimal remainLimit, String taxaJuros, String creditPassword) {
        this.cardNumber = cardNumber;
        this.valid = valid;
        this.cvv = cvv;
        this.user = user;
        this.cardLimit = cardLimit;
        this.remainLimit = remainLimit;
        this.taxaJuros = taxaJuros;
        this.creditPassword = creditPassword;
    }

}
