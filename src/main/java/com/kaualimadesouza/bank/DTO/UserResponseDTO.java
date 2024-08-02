package com.kaualimadesouza.bank.DTO;

import com.kaualimadesouza.bank.Domain.User;

import java.util.UUID;

public record UserResponseDTO(UUID id, String first_name, String last_name, Double amount, String cpf, String email, String password) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getFirst_name(), user.getLast_name(), user.getAmount(), user.getCpf(), user.getEmail(), user.getPassword());
    }
}
