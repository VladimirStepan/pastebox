package ru.education.pastebox.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.education.pastebox.dto.PasteBoxResponse;
import ru.education.pastebox.dto.PasteDTO;
import ru.education.pastebox.entity.Paste;
import ru.education.pastebox.service.PasteBoxService;
import ru.education.pastebox.util.PasteConvertor;
import ru.education.pastebox.util.Status;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PasteBoxController {
    private final PasteBoxService pasteBoxService;
    private final PasteConvertor pasteConvertor;

    @Autowired
    public PasteBoxController(PasteBoxService pasteBoxService, PasteConvertor pasteConvertor) {
        this.pasteBoxService = pasteBoxService;
        this.pasteConvertor = pasteConvertor;
    }

    @GetMapping()
    public PasteBoxResponse getPublicPasteList(){
        return new PasteBoxResponse(pasteBoxService.getAllPasteBoxesPublic(Status.PUBLIC.getStatus()).stream().map(pasteConvertor::convertToPasteDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{hash}")
    public String getByHash(@PathVariable String hash){
        return hash;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addPaste(@Valid @RequestBody PasteDTO pasteDTO){
        Paste paste = pasteConvertor.convertToPaste(pasteDTO);

        pasteBoxService.registration(paste);

    return ResponseEntity.ok(HttpStatus.OK);
    }

}
