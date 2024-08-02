package com.kaualimadesouza.bank.Service;

import com.kaualimadesouza.bank.DTO.TransferDateDTO;
import com.kaualimadesouza.bank.DTO.TransferRequestDTO;
import com.kaualimadesouza.bank.DTO.TransferResponseDTO;
import com.kaualimadesouza.bank.Domain.*;
import com.kaualimadesouza.bank.Exception.TransferNoHaveLimitException;
import com.kaualimadesouza.bank.Exception.UserNotFoundException;
import com.kaualimadesouza.bank.Exception.UserNullPointException;
import com.kaualimadesouza.bank.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransferService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TranferRepository tranferRepository;

    @Autowired
    private CreditCardRepository creditRepository;

    @Autowired
    private DebitCardRepository debitRepository;

    public String transfer(TransferRequestDTO requestDTO) throws Exception {
        Optional<User> sender = userRepository.findById(requestDTO.id_sender());
        Optional<User> receiver = userRepository.findById(requestDTO.id_receiver());

        if (!(sender.isPresent() && receiver.isPresent())) {
            throw new UserNotFoundException("User Not Found.");
        }

        if(requestDTO.type().toString().isEmpty()) {
            throw new UserNullPointException("Insert a payment method");
        }

        if (requestDTO.type().equals(PayMethodEnum.PIX) || requestDTO.type().equals(PayMethodEnum.DEBIT)) {
            if (requestDTO.type().equals(PayMethodEnum.DEBIT)) {
                DebitCard debitCard = debitRepository.findByUser(sender.get());
                if (debitCard == null) {
                    throw new UserNullPointException("You don't have debit card.");
                }
            }

            if (sender.get().getAmount().compareTo(requestDTO.amount()) < 0) {
                throw new TransferNoHaveLimitException("Insufficient funds.");
            }

            sender.get().setAmount(sender.get().getAmount() - requestDTO.amount());
            receiver.get().setAmount(receiver.get().getAmount() + requestDTO.amount());

            userRepository.save(sender.get());
            userRepository.save(receiver.get());
            tranferRepository.save(new Transfer(requestDTO, sender.get(), receiver.get()));
            return "Transaction Successful.";

        } else if (requestDTO.type().equals(PayMethodEnum.CREDIT)) {
            CreditCard creditCard = creditRepository.findByUser(sender.get());
            if (creditCard == null) {
                throw new UserNullPointException("You don't have credit card.");
            }
            if (Double.parseDouble(String.valueOf(creditCard.getRemainLimit())) > requestDTO.amount()) {
                creditCard.setRemainLimit(BigDecimal.valueOf(Double.parseDouble(String.valueOf(creditCard.getRemainLimit())) - requestDTO.amount()));
                receiver.get().setAmount(receiver.get().getAmount() + requestDTO.amount());
                userRepository.save(receiver.get());

                return "Transaction Successful.";
            }
        }

        return "";
    }

    public List<TransferResponseDTO> getTranfers() throws Exception {
        List<TransferResponseDTO> transfers = tranferRepository.findAll().stream().map(TransferResponseDTO::new).toList();
        if(transfers.isEmpty()) {
            throw new UserNullPointException();
        }
        return transfers;
    }

    public TransferResponseDTO getTransferById(UUID id) throws Exception {
        Optional<Transfer> transfer = tranferRepository.findById(id);
        if(transfer.isEmpty()) {
            throw new UserNullPointException();
        }
        return new TransferResponseDTO(transfer.get());
    }

    public List<TransferResponseDTO> getTranfersSender(UUID senderId) throws Exception {
        Optional<User> sender = userRepository.findById(senderId);
        if(sender.isEmpty()) {
            throw new UserNullPointException();
        }
        return tranferRepository.findAllBySender(sender.get()).stream().map(TransferResponseDTO::new).toList();
    }

    public List<TransferResponseDTO> getTranfersReceiver(UUID senderId) throws Exception {
        Optional<User> sender = userRepository.findById(senderId);
        if(sender.isEmpty()) {
            throw new UserNullPointException();
        }
        return tranferRepository.findAllByReceiver(sender.get()).stream().map(TransferResponseDTO::new).toList();
    }

    public List<TransferResponseDTO> getTranfersByDate(TransferDateDTO transferDateDTO) throws Exception {
        List<TransferResponseDTO> transfers = tranferRepository.findAllByDate(transferDateDTO.year(), transferDateDTO.month()).stream().map(TransferResponseDTO::new).toList();
        if(transfers.isEmpty()) {
            throw new UserNullPointException();
        }
        return transfers;
    }

    public void deleteAll() {
        tranferRepository.deleteAll();
    }
}
