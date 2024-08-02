package com.kaualimadesouza.bank.Repository;

import com.kaualimadesouza.bank.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByCpf(String cpf);
    User findByEmail(String email);
}
