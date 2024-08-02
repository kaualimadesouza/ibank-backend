package com.kaualimadesouza.bank.Repository;

import com.kaualimadesouza.bank.Domain.Transfer;
import com.kaualimadesouza.bank.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TranferRepository extends JpaRepository<Transfer, UUID> {
    List<Transfer> findAllBySender(User sender);
    List<Transfer> findAllByReceiver(User receiver);

    @Query("SELECT m FROM Transfer m WHERE EXTRACT(YEAR FROM m.date) = :year AND EXTRACT(MONTH FROM m.date) = :month")
    List<Transfer> findAllByDate(@Param("year") int year, @Param("month") int month);
}
