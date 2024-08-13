package ru.education.pastebox.entity;

import lombok.Getter;
import lombok.Setter;
import ru.education.pastebox.dto.PasteDTO;

@Getter
@Setter
public class PasteRegistrationResponse {
    private PasteDTO response;
    private String message;

    public PasteRegistrationResponse(PasteDTO response, String message) {
        this.response = response;
        this.message = message;
    }
}
