package com.kaualimadesouza.bank.Domain;

import com.kaualimadesouza.bank.DTO.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    public User(UserRequestDTO request) {
        this.first_name = request.first_name();
        this.last_name = request.last_name();
        this.amount = request.amount();
        this.cpf = request.cpf();
        this.email = request.email();
        this.password = request.password();
    }
}
