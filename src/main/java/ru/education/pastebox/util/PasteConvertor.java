package ru.education.pastebox.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.education.pastebox.dto.PasteDTO;
import ru.education.pastebox.entity.Paste;

@Component
@RequiredArgsConstructor
public class PasteConvertor {
    private final ModelMapper modelMapper;

    public PasteDTO convertToPasteDTO(Paste paste) {
        return modelMapper.map(paste, PasteDTO.class);
    }

    public Paste convertToPaste(PasteDTO pasteDTO) {
        return modelMapper.map(pasteDTO, Paste.class);
    }
}
