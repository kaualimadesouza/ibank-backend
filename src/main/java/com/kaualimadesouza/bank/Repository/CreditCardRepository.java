package com.kaualimadesouza.bank.Repository;

import com.kaualimadesouza.bank.Domain.CreditCard;
import com.kaualimadesouza.bank.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    public CreditCard findByUser(User user);
}
