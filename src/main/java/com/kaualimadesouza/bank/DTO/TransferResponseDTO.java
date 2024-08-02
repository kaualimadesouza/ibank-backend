package com.kaualimadesouza.bank.DTO;

import com.kaualimadesouza.bank.Domain.PayMethodEnum;
import com.kaualimadesouza.bank.Domain.Transfer;
import com.kaualimadesouza.bank.Domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransferResponseDTO(UUID id, User sender, User receiver, Double amount, PayMethodEnum type, LocalDateTime transfer_date) {
    public TransferResponseDTO(Transfer transfer) {
        this(transfer.getId(), transfer.getSender(), transfer.getReceiver(), transfer.getValueTransfer(), transfer.getType(), transfer.getDate());
    }

}
