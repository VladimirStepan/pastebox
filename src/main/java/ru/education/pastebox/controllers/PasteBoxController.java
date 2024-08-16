package ru.education.pastebox.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.education.pastebox.dto.PasteBoxResponse;
import ru.education.pastebox.dto.PasteDTO;
import ru.education.pastebox.entity.Paste;
import ru.education.pastebox.entity.PasteRegistrationResponse;
import ru.education.pastebox.service.PasteBoxService;
import ru.education.pastebox.util.PasteConvertor;
import ru.education.pastebox.util.Status;
import ru.education.pastebox.validation.PasteBoxErrorResponse;
import ru.education.pastebox.validation.PasteBoxException;
import ru.education.pastebox.validation.PasteBoxValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.education.pastebox.validation.ErrorUtil.returnsClientErrorMessage;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
@RequiredArgsConstructor
public class PasteBoxController {
    private final PasteBoxService pasteBoxService;
    private final PasteConvertor pasteConvertor;
    private final PasteBoxValidator pasteBoxValidator;

    @GetMapping()
    public PasteBoxResponse getPublicPasteList() {
        return new PasteBoxResponse(pasteBoxService.getAllPasteBoxesPublic(Status.PUBLIC.getStatus()).stream().map(pasteConvertor::convertToPasteDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{hash}")
    public ResponseEntity<PasteDTO> getByHash(@PathVariable String hash) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Optional<Paste> paste = pasteBoxService.getPasteByHash(hash);
        if (paste.isPresent() && paste.get().getCreatedAt().until(localDateTime, ChronoUnit.SECONDS) < paste.get().getLifeTime()) {
            return ResponseEntity.ok(pasteConvertor.convertToPasteDTO(paste.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> addPaste(@Valid @RequestBody PasteDTO pasteDTO, BindingResult bindingResult) {
        Paste paste = pasteConvertor.convertToPaste(pasteDTO);

        pasteBoxValidator.validate(paste, bindingResult);

        if (bindingResult.hasErrors())
            returnsClientErrorMessage(bindingResult);

        pasteBoxService.registration(paste);

        String message = "/my-awesome-pastebin.tld/" + paste.getHash();
        PasteRegistrationResponse pasteRegistrationResponse = new PasteRegistrationResponse(pasteDTO, message);
        return new ResponseEntity<>(pasteRegistrationResponse, HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<PasteBoxErrorResponse> handleException(PasteBoxException pasteBoxException) {
        PasteBoxErrorResponse errorResponse = new PasteBoxErrorResponse(
                pasteBoxException.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
