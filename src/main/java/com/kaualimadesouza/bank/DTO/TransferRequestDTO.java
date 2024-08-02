package com.kaualimadesouza.bank.DTO;

import com.kaualimadesouza.bank.Domain.PayMethodEnum;

import java.util.UUID;

public record TransferRequestDTO(UUID id_sender, UUID id_receiver, Double amount, PayMethodEnum type) {
}
