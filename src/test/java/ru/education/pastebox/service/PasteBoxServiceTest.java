package ru.education.pastebox.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.education.pastebox.entity.Paste;
import ru.education.pastebox.repositories.PasteBoxRepository;
import ru.education.pastebox.util.Status;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PasteBoxServiceTest {
    @Autowired
    PasteBoxService pasteBoxService;
    @MockBean
    PasteBoxRepository pasteBoxRepository;

    @BeforeEach
    void setUp() {
        pasteBoxService = new PasteBoxService(pasteBoxRepository);
    }

    @Test
    void getAllPasteBoxesPublic() {
        Paste pasteResult = new Paste();
        pasteResult.setHash("random");
        pasteResult.setStatus("public");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        when(pasteBoxRepository.findPasteByStatus(Status.PUBLIC.getStatus(), pageable)).thenReturn(Collections.singletonList(pasteResult));

        List<Paste> resultList = pasteBoxService.getAllPasteBoxesPublic(Status.PUBLIC.getStatus());

        assertEquals(1, resultList.size());
        assertEquals(pasteResult, resultList.get(0));
    }

    @Test
    void getAllPasteBoxesPublicIndexException() {
        Paste pasteResult = new Paste();
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        when(pasteBoxRepository.findPasteByStatus(Status.UNLISTED.getStatus(), pageable)).thenReturn(Collections.singletonList(pasteResult));

        List<Paste> resultList = pasteBoxService.getAllPasteBoxesPublic(Status.PUBLIC.getStatus());

        assertThrows(IndexOutOfBoundsException.class, () -> resultList.get(0));
    }

    @Test
    void getPasteByHash() {
        Paste pasteResult = new Paste();
        pasteResult.setHash("2");

        when(pasteBoxRepository.findPasteByHash(pasteResult.getHash())).thenReturn(Optional.of(pasteResult));

        Optional<Paste> result = pasteBoxService.getPasteByHash(pasteResult.getHash());
        assertTrue(result.isPresent());
        assertEquals("2", result.get().getHash());
        assertEquals(pasteResult, result.get());

    }

    @Test
    void getPasteByHashInvalid() {
        Paste pasteResult = new Paste();
        Paste pasteExpected = new Paste();
        pasteExpected.setHash("3");
        pasteResult.setHash("2");
        when(pasteBoxRepository.findPasteByHash(pasteResult.getHash())).thenReturn(Optional.of(pasteResult));
        when(pasteBoxRepository.findPasteByHash(pasteExpected.getHash())).thenReturn(Optional.of(pasteExpected));

        Optional<Paste> expected = pasteBoxService.getPasteByHash(pasteExpected.getHash());
        Optional<Paste> result = pasteBoxService.getPasteByHash(pasteResult.getHash());

        assertNotEquals(expected.get().getHash(), result.get().getHash());
    }

    @Test
    void getPasteByHashNoSuchElementException() {
        Paste paste = new Paste();
        paste.setHash("2");
        when(pasteBoxRepository.findPasteByHash("2")).thenReturn(Optional.of(paste));

        assertThrows(NoSuchElementException.class, () -> pasteBoxService.getPasteByHash("3").get());
    }
}