package com.kaualimadesouza.bank.Service;

import com.kaualimadesouza.bank.DTO.CardPasswordDTO;
import com.kaualimadesouza.bank.DTO.UserRequestDTO;
import com.kaualimadesouza.bank.DTO.UserResponseDTO;
import com.kaualimadesouza.bank.Domain.CreditCard;
import com.kaualimadesouza.bank.Domain.DebitCard;
import com.kaualimadesouza.bank.Domain.User;
import com.kaualimadesouza.bank.Exception.*;
import com.kaualimadesouza.bank.Repository.CreditCardRepository;
import com.kaualimadesouza.bank.Repository.DebitCardRepository;
import com.kaualimadesouza.bank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private DebitCardRepository debitCardRepository;



    public UserResponseDTO createNewUser(UserRequestDTO request) throws Exception {
        User newUser = userRepository.findByCpf(request.cpf());

        if(newUser != null) {
            throw new UserAlreadyExistsException();
        }

        if(request.first_name().isEmpty() || request.last_name().isEmpty()) {
            throw new UserNullPointException();
        }

        if (!validateCPF(transformCPF(request.cpf()))) {
            throw new UserInvalidCpfException();
        }

        if (!validateSintaxEmail(request.email())) {
            throw new UserInvalidEmailException();
        }

        User user = new User(request);
        userRepository.save(user);

        return new UserResponseDTO(user);
    }


    public List<UserResponseDTO> listAllUsers() throws Exception {
        List<User> rawUsers = userRepository.findAll();
        if (rawUsers.isEmpty()) {
            throw new UserNotFoundException();
        }
        return rawUsers.stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO getUserById(UUID id) throws Exception {
        Optional<User> rawUser = userRepository.findById(id);
        if (rawUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        return new UserResponseDTO(rawUser.get());
    }

    public UserResponseDTO getUserByCPF(String cpf) throws Exception {
        User rawUser = userRepository.findByCpf(cpf);
        if (rawUser == null) {
            throw new UserNotFoundException();
        }

        return new UserResponseDTO(rawUser);
    }

    public UserResponseDTO getUserByEmail(String email) throws Exception {
        User rawUser = userRepository.findByEmail(email);
        if (rawUser == null) {
            throw new UserNotFoundException();
        }

        return new UserResponseDTO(rawUser);
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO) throws Exception {
        Optional<User> rawUser = userRepository.findById(id);

        if (rawUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        rawUser.get().setFirst_name(userRequestDTO.first_name());
        rawUser.get().setLast_name(userRequestDTO.last_name());
        rawUser.get().setAmount(userRequestDTO.amount());
        rawUser.get().setCpf(userRequestDTO.cpf());
        rawUser.get().setEmail(userRequestDTO.email());

        User updatedUser = rawUser.get();
        userRepository.save(rawUser.get());
        return new UserResponseDTO(updatedUser);

    }

    public String activeCreditCard(UUID id, CardPasswordDTO cardPasswordDTO) throws Exception {
        if(cardPasswordDTO.password().isEmpty()) {
            throw new UserNullPointException();
        }

        Optional<User> rawUser = userRepository.findById(id);

        if (rawUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        String cardNumber = generateCardNumber();
        String cvv = generateCardCVV();

        CreditCard credit = new CreditCard(cardNumber, LocalDate.now().plusYears(15), cvv, rawUser.get(),BigDecimal.valueOf(200), BigDecimal.valueOf(200), "0", cardPasswordDTO.password());
        creditCardRepository.save(credit);
        return "Created";
    }

    public String activeDebitCredit(UUID id, CardPasswordDTO cardPasswordDTO) throws Exception {
        if(cardPasswordDTO.password().isEmpty()) {
            throw new UserNullPointException();
        }

        Optional<User> rawUser = userRepository.findById(id);

        if (rawUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        String cardNumber = generateCardNumber();
        String cvv = generateCardCVV();

        DebitCard debit = new DebitCard(cardNumber, LocalDate.now().plusYears(15), cvv, rawUser.get(), cardPasswordDTO.password());
        debitCardRepository.save(debit);
        return "Created";
    }

    public CreditCard getCreditCard(UUID id) throws Exception {
        Optional<User> rawUser = userRepository.findById(id);

        if (rawUser.isEmpty()) {
            throw new UserNotFoundException("User Not found.");
        }

        CreditCard card = creditCardRepository.findByUser(rawUser.get());
        if(card == null) {
            throw new UserNotFoundException("You don't have credit card");
        }

        return card;
    }

    public DebitCard getDebitCard(UUID id) throws Exception {
        Optional<User> rawUser = userRepository.findById(id);

        if (rawUser.isEmpty()) {
            throw new UserNotFoundException("User Not found.");
        }

        DebitCard card = debitCardRepository.findByUser(rawUser.get());
        if(card == null) {
            throw new UserNotFoundException("You don't have debit card");
        }

        return card;
    }

    public String deleteUser(UUID id) throws Exception {
        Optional<User> rawUser = userRepository.findById(id);

        if (rawUser.isPresent()) {
            userRepository.delete(rawUser.get());
            return "Deleted";
        }
        throw new UserNotFoundException("User Not found.");
    }


    public boolean validateCPF(String CPF) {
        if (CPF.length() != 11 || CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") ||
                CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") || CPF.equals("01234567890"))
            return false;

        // PRIMEIRO DIGITO VERIFICADOR
        int soma = 0;

        for (int i = 10; i > 1; i--) {
            soma += i * Integer.parseInt(String.valueOf(CPF.charAt(10 - i)));
        }
        if ((soma % 11 != 0 || soma % 11 != 1) && Integer.parseInt(String.valueOf(CPF.charAt(9))) == 0) {
            return false;
        }
        if (Integer.parseInt(String.valueOf(CPF.charAt(9))) != 11 - (soma % 11)) {
            return false;
        }

        // SEGUNDO DIGITO VERIFICADOR
        soma = 0;
        for (int i = 11; i > 1; i--) {
            soma += i * Integer.parseInt(String.valueOf(CPF.charAt(11 - i)));
        }
        if ((soma % 11 != 0 || soma % 11 != 1) && Integer.parseInt(String.valueOf(CPF.charAt(10))) == 0) {
            return false;
        }
        if (Integer.parseInt(String.valueOf(CPF.charAt(10))) != 11 - (soma % 11)) {
            return false;
        }

        return true;
    }

    public boolean validateSintaxEmail(String email) {
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email.matches(EMAIL_REGEX)) {
            return true;
        }
        return false;
    }

    public String transformCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 13; i++) {
            cardNumber.append(String.valueOf(random.nextInt(10)));
        }
        return cardNumber.toString();
    }

    public String generateCardCVV() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            cardNumber.append(String.valueOf(random.nextInt(10)));
        }
        return cardNumber.toString();
    }

}
