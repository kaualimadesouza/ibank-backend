package com.kaualimadesouza.bank.Controller;

import com.kaualimadesouza.bank.DTO.CardPasswordDTO;
import com.kaualimadesouza.bank.DTO.UserRequestDTO;
import com.kaualimadesouza.bank.DTO.UserResponseDTO;
import com.kaualimadesouza.bank.Domain.CreditCard;
import com.kaualimadesouza.bank.Domain.DebitCard;
import com.kaualimadesouza.bank.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO response) throws Exception {
        UserResponseDTO user = userService.createNewUser(response);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() throws Exception {
        List<UserResponseDTO> users = userService.listAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUsers(@PathVariable UUID id) throws Exception {
        UserResponseDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO bodyToUpdate) throws Exception {
        UserResponseDTO user = userService.updateUser(id, bodyToUpdate);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/cards/credit/active")
    public ResponseEntity<String> activeCreditCard(@PathVariable UUID id, @RequestBody CardPasswordDTO password) throws Exception {
        String response = userService.activeCreditCard(id, password);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/cards/credit")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable UUID id) throws Exception {
        CreditCard creditCard = userService.getCreditCard(id);
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }

    @PostMapping("/{id}/cards/debit/active")
    public ResponseEntity<String> activeDebitCard(@PathVariable UUID id, @RequestBody CardPasswordDTO password) throws Exception {
        String response = userService.activeDebitCredit(id, password);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/cards/debit")
    public ResponseEntity<DebitCard> getDebitCard(@PathVariable UUID id) throws Exception {
        DebitCard debitCard = userService.getDebitCard(id);
        return new ResponseEntity<>(debitCard, HttpStatus.OK);
    }


    @GetMapping("/{cpf}/cpf")
    public ResponseEntity<UserResponseDTO> getUserByCPF(@PathVariable String cpf) throws Exception {
        UserResponseDTO user = userService.getUserByCPF(cpf);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{email}/email")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) throws Exception {
        UserResponseDTO user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
