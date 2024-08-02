package com.kaualimadesouza.bank.DTO;

public record UserRequestDTO(String first_name, String last_name, Double amount, String cpf, String email, String password) {
}
