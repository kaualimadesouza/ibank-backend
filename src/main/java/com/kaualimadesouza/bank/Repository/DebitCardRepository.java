package com.kaualimadesouza.bank.Repository;

import com.kaualimadesouza.bank.Domain.DebitCard;
import com.kaualimadesouza.bank.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DebitCardRepository extends JpaRepository<DebitCard, UUID> {
    public DebitCard findByUser(User user);
}
