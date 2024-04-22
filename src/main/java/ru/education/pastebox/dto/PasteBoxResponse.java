package ru.education.pastebox.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PasteBoxResponse {
    private List<PasteDTO> pasteDTOList;

    public PasteBoxResponse(List<PasteDTO> pasteDTOList) {
        this.pasteDTOList = pasteDTOList;
    }
}
