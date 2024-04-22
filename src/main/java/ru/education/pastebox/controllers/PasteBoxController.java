package ru.education.pastebox.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.education.pastebox.dto.PasteDTO;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PasteBoxController {

    @GetMapping("/")
    public Collection<String> getPublicPasteList(){
        return Collections.emptyList();
    }

    @GetMapping("/{hash}")
    public String getByHash(@PathVariable String hash){
        return hash;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addPaste(@Valid @RequestBody PasteDTO pasteDTO){

    return ResponseEntity.ok(HttpStatus.OK);
    }

}
