package ru.education.pastebox.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasteBoxErrorResponse {
    private String message;
    private long timestamp;

    public PasteBoxErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
