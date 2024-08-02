package com.kaualimadesouza.bank.Controller;

import com.kaualimadesouza.bank.DTO.TransferDateDTO;
import com.kaualimadesouza.bank.DTO.TransferRequestDTO;
import com.kaualimadesouza.bank.DTO.TransferResponseDTO;
import com.kaualimadesouza.bank.Service.TransferService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService tranferService;

    @PostMapping()
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDTO transferBody) throws Exception {
        String response = tranferService.transfer(transferBody);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> getAllTranfers() throws Exception {
        List<TransferResponseDTO> transfers = tranferService.getTranfers();
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> getTransferById(@PathVariable UUID id) throws Exception {
        TransferResponseDTO transfer = tranferService.getTransferById(id);
        return new ResponseEntity<>(transfer, HttpStatus.OK);
    }


    @GetMapping("/sender/{userId}")
    public ResponseEntity<List<TransferResponseDTO>> getAllTranfersSender(@PathVariable UUID userId) throws Exception {
        List<TransferResponseDTO> transfers = tranferService.getTranfersSender(userId);
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }

    @GetMapping("/receiver/{userId}")
    public ResponseEntity<List<TransferResponseDTO>> getAllTranfersReceiver(@PathVariable UUID userId) throws Exception {
        List<TransferResponseDTO> transfers = tranferService.getTranfersReceiver(userId);
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<TransferResponseDTO>> getAllTransfersByDate(@RequestBody TransferDateDTO transferDateDTO) throws Exception {
        List<TransferResponseDTO> transfers = tranferService.getTranfersByDate(transferDateDTO);
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAllTransfers() {
        tranferService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
