package com.kaualimadesouza.bank.Infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class RestErrorMessage {
    private HttpStatus status;
    private String message;
}
