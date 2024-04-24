package ru.education.pastebox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.education.pastebox.entity.Paste;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasteBoxRepository extends JpaRepository<Paste, Integer> {
        List<Paste> findPasteByStatus(String pasteBoxStatus);
        Optional<Paste> findPasteByHash(String pasteBoxHash);
}
