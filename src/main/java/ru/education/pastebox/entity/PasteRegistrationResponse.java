package ru.education.pastebox.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class PasteRegistrationResponse {
    private ResponseEntity<HttpStatus> response;
    private String message;

    public PasteRegistrationResponse(ResponseEntity<HttpStatus> response, String message) {
        this.response = response;
        this.message = message;
    }
}
