package ru.education.pastebox.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.education.pastebox.entity.Paste;
import ru.education.pastebox.service.PasteBoxService;

//TODO Данный валидатор добавлен для галочки(учебный проект).
// В нём нет необходимости т.к. по условиям задачи хэм должен быть всегда уникальным.
@Component
public class PasteBoxValidator implements Validator {
    private final PasteBoxService pasteBoxService;

    @Autowired
    public PasteBoxValidator(PasteBoxService pasteBoxService) {
        this.pasteBoxService = pasteBoxService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Paste.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Paste paste = (Paste) target;

        if(pasteBoxService.getPasteByHash(paste.getHash()).isPresent()){
            errors.rejectValue("hash", "Хэш с таким названием уже есть");
        }

    }
}
