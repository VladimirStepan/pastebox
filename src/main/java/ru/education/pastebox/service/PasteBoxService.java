package ru.education.pastebox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.pastebox.entity.Paste;
import ru.education.pastebox.repositories.PasteBoxRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PasteBoxService {
    //Логика работы с хэшом и временем
    //Реализация методов добавления, получения пасты
    private final PasteBoxRepository pasteBoxRepository;

    @Autowired
    public PasteBoxService(PasteBoxRepository pasteBoxRepository) {
        this.pasteBoxRepository = pasteBoxRepository;
    }

    public List<Paste> getAllPasteBoxesPublic(String status) {
        return pasteBoxRepository.findPasteByStatus(status);
    }
}
